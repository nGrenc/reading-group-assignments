package at.jit.readinggroup;

public class CommandingOfficerSpock {

    private static final String officialGreeting = "live long and prosper";

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            name = "Stranger";
        }
        return String.format("%s, %s!", name, officialGreeting);
    }
}
