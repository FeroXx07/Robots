package Repository;

import Robots.Aero.AeroRobot;
import Robots.Aquatic.AquaticRobot;
import Robots.Aquatic.Propulsion;
import Robots.Land.LandRobot;
import Robots.Land.Traction;
import Robots.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RobotRepositoryTest {

    private RobotRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RobotRepository();
    }

    @Test
    void shouldBeEmptyAtInit() {
        RobotRepository newRepo = new RobotRepository();
        assertThat(newRepo.getAll()).isNotNull();
        assertThat(newRepo.getAll()).isEmpty();
    }

    @Test
    void addRobot() {
        assertThat(repository.getAll().size()).isEqualTo(0);
        repository.addRobot(new LandRobot("T-1000:", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(40), Traction.TRACTION_CATERPILLAR));
        assertThat(repository.getAll().size()).isEqualTo(1);
    }

    @Test
    void shouldGetLandRobotsMaxVelocity() {
        repository.addRobot(new LandRobot("TankBot", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(40), Traction.TRACTION_CATERPILLAR));

        repository.addRobot(new LandRobot("T-1000:", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(50), Traction.TRACTION_CATERPILLAR));
        repository.addRobot(new LandRobot("T-700:", "RoboCorp", 2022, 2026,
                BigDecimal.valueOf(90), Traction.TRACTION_TYRE));

        List<LandRobot> filtered = repository.getLandRobotsMaxVelocity(BigDecimal.valueOf(50));
        assertThat(filtered.size()).isEqualTo(2);
    }
}