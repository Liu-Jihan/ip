import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public Event(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from); // Parse start datetime
        this.toDateTime = parseEndTime(to, this.fromDateTime); // Parse end time
    }

    /**
     * Parses a date-time string like "Aug 6th 2pm".
     */
    private LocalDateTime parseDateTime(String dateTimeStr) {
        // Remove ordinal suffixes (st, nd, rd, th)
        dateTimeStr = dateTimeStr.replaceAll("(\\d+)(st|nd|rd|th)", "$1");

        // Regex pattern to extract Month, Day, and Time (if exists)
        String dateTimePattern = "([A-Za-z]+)\\s(\\d{1,2})\\s*(\\d{1,2}(am|pm)?)?";
        Pattern pattern = Pattern.compile(dateTimePattern);
        Matcher matcher = pattern.matcher(dateTimeStr);

        if (matcher.find()) {
            String month = matcher.group(1); // "Aug"
            String day = matcher.group(2);   // "6"
            String time = matcher.group(3);  // "2pm" (if exists)

            // Convert to a proper date-time string format
            String formattedDateTime = day + " " + month + " " + LocalDateTime.now().getYear(); // Assume current year
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");

            try {
                LocalDateTime dateTime = LocalDateTime.parse(formattedDateTime + " 00:00", DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
                if (time != null) {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("ha");
                    dateTime = dateTime.withHour(parseTimeTo24Hour(time));
                }
                return dateTime;
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing date: " + dateTimeStr);
            }
        }
        return null;
    }

    /**
     * Parses an end time like "4pm" and assumes the same date as `fromDateTime`.
     */
    private LocalDateTime parseEndTime(String endTimeStr, LocalDateTime fromDateTime) {
        if (fromDateTime == null) return null; // Cannot parse end time without start time
        if (endTimeStr.matches("\\d{1,2}(am|pm)")) { // Only time is given
            return fromDateTime.withHour(parseTimeTo24Hour(endTimeStr));
        }
        return fromDateTime; // If invalid, return start date
    }

    /**
     * Converts "2pm" to 14 (24-hour format).
     */
    private int parseTimeTo24Hour(String timeStr) {
        int hour = Integer.parseInt(timeStr.replaceAll("[^0-9]", ""));
        if (timeStr.contains("pm") && hour != 12) hour += 12; // Convert PM times
        if (timeStr.contains("am") && hour == 12) hour = 0; // Handle 12am edge case
        return hour;
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mma");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                (fromDateTime != null ? fromDateTime.format(dateTimeFormatter) : "Invalid DateTime") + " | " +
                (toDateTime != null ? toDateTime.format(dateTimeFormatter) : "Invalid DateTime");
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E]" + super.toString() + " (from: " +
                (fromDateTime != null ? fromDateTime.format(displayFormat) : "Invalid DateTime") +
                " to: " +
                (toDateTime != null ? toDateTime.format(displayFormat) : "Invalid DateTime") + ")";
    }
}
