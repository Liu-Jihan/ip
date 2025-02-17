package places;

import java.util.ArrayList;

/**
 * Represents a list of places where tasks or events take place.
 */
public class Places {
    private ArrayList<String> places;

    /**
     * Constructs an empty list of places.
     */
    public Places() {
        this.places = new ArrayList<>();
    }

    /**
     * Adds a place to the list.
     *
     * @param place The name of the place.
     */
    public void addPlace(String place) {
        assert place != null && !place.isEmpty() : "Place name cannot be null or empty";
        places.add(place);
    }

    /**
     * Removes a place by index.
     *
     * @param index The index of the place to remove.
     * @return The removed place.
     */
    public String removePlace(int index) {
        assert index >= 0 && index < places.size() : "Index out of bounds for places list";
        return places.remove(index);
    }

    /**
     * Lists all places.
     *
     * @return A formatted string containing all places.
     */
    public String listPlaces() {
        if (places.isEmpty()) {
            return "No places available.";
        }
        StringBuilder result = new StringBuilder("Here are the places you have added:\n");
        for (int i = 0; i < places.size(); i++) {
            result.append((i + 1)).append(". ").append(places.get(i)).append("\n");
        }
        return result.toString().trim();
    }
}
