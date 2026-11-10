import Robots.Land.LandRobot;
import Robots.Robot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RobotRepository {

    private final List<Robot> robots;

    public RobotRepository() {
        robots = new ArrayList<>();
    }

    public void addRobot(Robot toAdd) {
        robots.add(toAdd);
    }

    public List<Robot> getAll() {
        return Collections
                .unmodifiableList(robots);
    }

    public List<LandRobot> getLandRobotsMaxVelocity(BigDecimal maxVelocity) {
        return getRobotsByType(LandRobot.class)
                .stream()
                .filter(landRobot -> landRobot.getMaxVelocity().compareTo(maxVelocity) > 0)
                .toList();
    }

    private <T extends Robot> List<T> getRobotsByType(Class<T> tClass) {
        return robots
                .stream()
                .filter(tClass::isInstance)
                .map(tClass::cast).toList();
    }
}
