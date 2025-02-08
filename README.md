# 🌟 OscarL: Your Ultimate Productivity Companion
"Your mind is for having ideas, not holding them." – David Allen

Meet OscarL, the next-generation task manager that takes the mental load off your shoulders! 🧠✅
Say goodbye to forgetting deadlines and hello to effortless organization!

🚀 Why Choose OscarL?
🖥️ Text-based for maximum efficiency
📚 Easy to learn – get started in minutes!
⚡ Blazing fast – complete tasks at the speed of light!
🛠️ How to Get Started?
Download OscarL now! ⬇️
Double-click to launch 🚀
Start adding your tasks 📝
Watch OscarL handle your life like a pro! 🎩✨

🏆 Key Features
✅ Smart task management – no more chaos!
⏳ Deadline tracking (Coming soon ⏳)
🔔 Reminders to keep you on track (Coming soon 🔔)

👨‍💻 Developers, Rejoice!
OscarL is built for everyone, but if you're a Java enthusiast, you can even tweak it to your liking! Here’s the main method to get you started:
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
📝 To-Do List for the Future
 Implement core task management
 Add support for recurring tasks 🔄
 Integrate with Google Calendar 📆
 Mobile app version 📱 (Maybe? 🤔)


## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/oscarl.oscarl.java` file, right-click it, and choose `Run oscarl.oscarl.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
