package at.jit.readinggroup;

import java.util.Random;

public class Starship {

    // @Autowired
    private FleetCommand fleetCommand;

    private String starshipName;

    public Starship(String starshipName) {
        this.starshipName = starshipName;
    }

    public String getName() {
        return starshipName;
    }

    public boolean registerWithFleetCommand() {
        fleetCommand.addShip(this);
        return true;
    }

    public String emergencyProcedure() {
        boolean successfulEvacuation = evacuateStaff();
        String messageForCommander;
        if (successfulEvacuation) {
            messageForCommander = fleetCommand.notifyAboutDestruction(this);
            selfDestruct();
        } else {
            messageForCommander = fleetCommand.notifyAboutRequiredSupport(this);
        }
        return messageForCommander;
    }

    boolean evacuateStaff() {
        return new Random().nextBoolean();
    }

    void selfDestruct() {
        System.exit(1);
    }
}
