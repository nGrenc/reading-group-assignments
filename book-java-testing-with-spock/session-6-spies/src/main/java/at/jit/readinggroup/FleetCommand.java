package at.jit.readinggroup;

import java.util.ArrayList;
import java.util.List;

public class FleetCommand {

    private List<Starship> starshipsInCommand = new ArrayList<>();

    public void addShip(Starship starship) {
        starshipsInCommand.add(starship);
    }

    public String notifyAboutDestruction(Starship starship) {
        if (! starshipsInCommand.remove(starship)) {
            throw new RuntimeException("Starship " + starship.getName() + "is not part of our fleet");
        }
        return "Farewell " + starship.getName();
    }

    public String notifyAboutRequiredSupport(Starship starship) {
        return "Support dispatched for " + starship.getName();
    }
}
