package Robots;

import java.util.List;

public class Utility {

    public static List<ResistanceEvaluable> getEvaluableRobots(List<Robot> robots) {
        return robots
                .stream()
                .filter(ResistanceEvaluable.class::isInstance)
                .map(ResistanceEvaluable.class::cast)
                .toList();
    }
}
