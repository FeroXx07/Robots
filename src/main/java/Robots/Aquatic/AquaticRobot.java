package Robots.Aquatic;

import Robots.Robot;

import java.math.BigDecimal;
import java.util.Objects;

public class AquaticRobot extends Robot {
    private final BigDecimal maxDepth;
    private final Propulsion propulsionType;

    public AquaticRobot(String name, String manufacturer, int yearManufactur, int yearRegistration, BigDecimal maxProfundityCapacity, Propulsion propulsionType) {
        super(name, manufacturer, yearManufactur, yearRegistration);
        this.maxDepth = maxProfundityCapacity;
        this.propulsionType = propulsionType;
    }

    public BigDecimal getMaxDepth() {
        return maxDepth;
    }

    public Propulsion getPropulsionType() {
        return propulsionType;
    }

    @Override
    public String getTechnicalDescription() {
        return "%s alcanza una profundidad de %s m con propulsión tipo %s. Fabricado por %s en %s."
                .formatted(this.getName(), this.maxDepth, this.propulsionType, this.getManufacturer(), this.getYearManufactur());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AquaticRobot that = (AquaticRobot) o;
        return Objects.equals(maxDepth, that.maxDepth) && propulsionType == that.propulsionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxDepth, propulsionType);
    }
}
