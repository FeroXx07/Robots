package Competition;

import Robots.Aero.AeroRobot;
import Robots.Aquatic.AquaticRobot;
import Robots.Aquatic.Propulsion;
import Robots.Land.LandRobot;
import Robots.Land.Traction;
import Robots.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResistanceReportPrinterTest {

    private List<Robot> robotList;
    private ResistanceCompetition resistanceCompetition;
    private ResistanceReportPrinter resistanceReportPrinter;

    @BeforeEach
    void setUp() {
        robotList = new ArrayList<>();
        resistanceCompetition = new ResistanceCompetition(Traction.TRACTION_CATERPILLAR, BigDecimal.valueOf(60));
        resistanceReportPrinter = new ResistanceReportPrinter();

        robotList.add(new AeroRobot("SkyDancer", "AeroDynamics", 2023, 2026,
                BigDecimal.valueOf(3000), BigDecimal.valueOf(90)));
        robotList.add(new LandRobot("TankBot", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(40), Traction.TRACTION_CATERPILLAR));
        robotList.add(new AquaticRobot("AquaRunner", "AquaTech", 2021, 2026,
                BigDecimal.valueOf(500), Propulsion.PROPULSION_PROPELLER));

        robotList.add(new AeroRobot("SkyX-1", "AeroDynamics", 2023, 2026,
                BigDecimal.valueOf(1200), BigDecimal.valueOf(75)));
        robotList.add(new AeroRobot("FlyBot-3:", "AeroDynamics", 2023, 2026,
                BigDecimal.valueOf(800), BigDecimal.valueOf(30)));

        robotList.add(new LandRobot("T-1000:", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(40), Traction.TRACTION_CATERPILLAR));
        robotList.add(new LandRobot("T-700:", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(90), Traction.TRACTION_TYRE));
    }

    @Test
    void shouldPrintReport() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));

            resistanceReportPrinter.printReport(resistanceCompetition.evaluateRobots(robotList));

            assertThat(output.toString()).containsSubsequence("Apto para la competición.");
            assertThat(output.toString()).containsSubsequence("No cumple los requisitos.");
        }
        finally {
            System.setOut(originalOut);
        }
    }
}