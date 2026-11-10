package Competition;

import Robots.ResistanceEvaluable;

import java.util.List;

public class ResistanceReportPrinter {
    public void printReport(List<ResistanceEvaluable> robots) {
        System.out.println("=== Resilience Test Reports ===");
        for (ResistanceEvaluable robot : robots) {
            if (!robot.getResistanceReport().evaluated())
                continue;

            System.out.println(robot.getResistanceReport().reason());
            System.out.println();
        }
    }
}