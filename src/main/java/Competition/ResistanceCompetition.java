package Competition;

import Robots.Aero.AeroRobot;
import Robots.Land.LandRobot;
import Robots.Land.Traction;
import Robots.ResistanceEvaluable;
import Robots.ResistanceReport;
import Robots.Robot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResistanceCompetition {

    private final Traction requiredTractionType;
    private final BigDecimal requiredAutonomy;

    public ResistanceCompetition(Traction requiredTractionType, BigDecimal requiredAutonomy) {
        this.requiredTractionType = requiredTractionType;
        this.requiredAutonomy = requiredAutonomy;
    }

    public List<ResistanceEvaluable> evaluateRobots(List<Robot> robots) {
        List<ResistanceEvaluable> evaluableList = getEvaluableRobots(robots);

        for (ResistanceEvaluable ev : evaluableList) {
            if (ev instanceof LandRobot) evaluateLandRobot((LandRobot) ev);
            if (ev instanceof AeroRobot) evaluateAeroRobot((AeroRobot) ev);
        }

        return evaluableList;
    }

    public void evaluateLandRobot(LandRobot robot) {
        boolean suitable = false;
        StringBuilder sb = new StringBuilder(robot.getName());

        sb.append(":").append(" tracción ORUGAS ");
        if (robot.getTractionType() == requiredTractionType) {
            sb.append("confirmada.");
            sb.append(" Apto para la competición.");
            suitable = true;
        }
        else {
            sb.append("no confirmada.");
            sb.append(" No cumple los requisitos.");
        }

        robot.setResistanceReport(new ResistanceReport(true, suitable, sb.toString()));
    }

    public void evaluateAeroRobot(AeroRobot robot) {
        boolean suitable = false;
        StringBuilder sb = new StringBuilder(robot.getName());

        sb.append(":").append(" autonomía de ").append(robot.getAutonomy()).append(" minutos.");
        if (robot.getAutonomy().compareTo(requiredAutonomy) >= 0) {
            sb.append(" Apto para la competición.");
            suitable = true;
        }
        else {
            sb.append(" No cumple los requisitos.");
        }

        robot.setResistanceReport(new ResistanceReport(true, suitable, sb.toString()));
    }

    private List<ResistanceEvaluable> getEvaluableRobots(List<Robot> robots) {
        return robots
                .stream()
                .filter(ResistanceEvaluable.class::isInstance)
                .map(ResistanceEvaluable.class::cast)
                .toList();
    }
}
