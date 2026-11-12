package Competition;

import Robots.Aero.AeroRobot;
import Robots.Aquatic.AquaticRobot;
import Robots.Aquatic.Propulsion;
import Robots.Land.LandRobot;
import Robots.Land.Traction;
import Repository.RobotRepository;
import Robots.ResistanceEvaluable;
import Robots.ResistanceReport;
import Robots.Robot;
import Robots.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResistanceCompetitionTest {

    private ResistanceCompetition resistanceCompetition;
    private RobotRepository robotRepository;

    @BeforeEach
    void setUp() {
        resistanceCompetition = new ResistanceCompetition(Traction.TRACTION_CATERPILLAR, BigDecimal.valueOf(60));
        seedRobotRepository();
    }

    private void seedRobotRepository() {
        robotRepository = new RobotRepository();

        robotRepository.addRobot(new AeroRobot("SkyDancer", "AeroDynamics", 2023, 2026,
                BigDecimal.valueOf(3000), BigDecimal.valueOf(90)));
        robotRepository.addRobot(new LandRobot("TankBot", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(40), Traction.TRACTION_CATERPILLAR));
        robotRepository.addRobot(new AquaticRobot("AquaRunner", "AquaTech", 2021, 2026,
                BigDecimal.valueOf(500), Propulsion.PROPULSION_PROPELLER));

        robotRepository.addRobot(new AeroRobot("SkyX-1", "AeroDynamics", 2023, 2026,
                BigDecimal.valueOf(1200), BigDecimal.valueOf(75)));
        robotRepository.addRobot(new AeroRobot("FlyBot-3:", "AeroDynamics", 2023, 2026,
                BigDecimal.valueOf(800), BigDecimal.valueOf(30)));

        robotRepository.addRobot(new LandRobot("T-1000:", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(40), Traction.TRACTION_CATERPILLAR));
        robotRepository.addRobot(new LandRobot("T-700:", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(90), Traction.TRACTION_TYRE));
    }

    @Test
    void shouldChangeReportStateRobots(){
        List<Robot> list = robotRepository.getAll();

        List<ResistanceEvaluable> listEvaluableBefore = Utility.getEvaluableRobots(list);
        for (ResistanceEvaluable re : listEvaluableBefore) {
            assertThat(re.getResistanceReport().evaluated()).isFalse();
        }

        resistanceCompetition.evaluateRobots(list);

        List<ResistanceEvaluable> listEvaluableAfter = Utility.getEvaluableRobots(list);
        for (ResistanceEvaluable re : listEvaluableAfter) {
            assertThat(re.getResistanceReport().evaluated()).isTrue();
        }
    }

    @Test
    void shouldEvaluatePassingLandRobot() {
        LandRobot landRobot = new LandRobot("TankBot", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(90), Traction.TRACTION_CATERPILLAR);

        assertThat(landRobot.getResistanceReport().evaluated()).isFalse();
        resistanceCompetition.evaluateLandRobot(landRobot);
        assertThat(landRobot.getResistanceReport().evaluated()).isTrue();
        assertThat(landRobot.getResistanceReport().suitable()).isTrue();
    }

    @Test
    void shouldEvaluateFailingLandRobot() {
        LandRobot landRobot = new LandRobot("TankBot", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(30), Traction.TRACTION_TYRE);

        assertThat(landRobot.getResistanceReport().evaluated()).isFalse();
        resistanceCompetition.evaluateLandRobot(landRobot);
        assertThat(landRobot.getResistanceReport().evaluated()).isTrue();
        assertThat(landRobot.getResistanceReport().suitable()).isFalse();
    }

    @Test
    void shouldEvaluatePassingAeroRobot() {
        AeroRobot aeroRobot = new AeroRobot("SkyDancer", "AeroDynamics", 2023, 2026,
                BigDecimal.valueOf(3000), BigDecimal.valueOf(90));

        assertThat(aeroRobot.getResistanceReport().evaluated()).isFalse();
        resistanceCompetition.evaluateAeroRobot(aeroRobot);
        assertThat(aeroRobot.getResistanceReport().evaluated()).isTrue();
        assertThat(aeroRobot.getResistanceReport().suitable()).isTrue();
    }

    @Test
    void shouldEvaluateFailingAeroRobot() {
        AeroRobot aeroRobot = new AeroRobot("SkyDancer", "AeroDynamics", 2023, 2026,
                BigDecimal.valueOf(3000), BigDecimal.valueOf(20));

        assertThat(aeroRobot.getResistanceReport().evaluated()).isFalse();
        resistanceCompetition.evaluateAeroRobot(aeroRobot);
        assertThat(aeroRobot.getResistanceReport().evaluated()).isTrue();
        assertThat(aeroRobot.getResistanceReport().suitable()).isFalse();
    }

    @Test
    void shouldEvaluateMultipleCompetitionSameRobots() {
        AeroRobot aeroRobot = new AeroRobot("SkyDancer", "AeroDynamics", 2023, 2026,
                BigDecimal.valueOf(3000), BigDecimal.valueOf(20));

        ResistanceCompetition easyCompetition = new ResistanceCompetition(Traction.TRACTION_CATERPILLAR, BigDecimal.valueOf(10));
        easyCompetition.evaluateAeroRobot(aeroRobot);
        assertThat(aeroRobot.getResistanceReport().evaluated()).isTrue();
        assertThat(aeroRobot.getResistanceReport().suitable()).isTrue();

        ResistanceCompetition hardCompetition = new ResistanceCompetition(Traction.TRACTION_CATERPILLAR, BigDecimal.valueOf(100));
        hardCompetition.evaluateAeroRobot(aeroRobot);
        assertThat(aeroRobot.getResistanceReport().suitable()).isFalse();
    }

}