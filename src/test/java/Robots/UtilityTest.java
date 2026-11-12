package Robots;

import Robots.Aero.AeroRobot;
import Robots.Aquatic.AquaticRobot;
import Robots.Aquatic.Propulsion;
import Robots.Land.LandRobot;
import Robots.Land.Traction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void getEvaluableRobots() {
        List<Robot> robotList = new ArrayList<>();
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

        List<ResistanceEvaluable> evaluableList = Utility.getEvaluableRobots(robotList);
        assertThat(evaluableList.size()).isEqualTo(4);
    }
}