package Robots.Land;

import Robots.ResistanceEvaluable;
import Robots.ResistanceReport;
import Robots.Robot;

import java.math.BigDecimal;
import java.util.Objects;

public class LandRobot extends Robot implements ResistanceEvaluable {
    private final BigDecimal maxVelocity;
    private final Traction tractionType;
    private ResistanceReport resistanceReport;

    public LandRobot(String name, String manufacturer, int yearManufactur, int yearRegistration, BigDecimal maxVelocity, Traction tractionType) {
        super(name, manufacturer, yearManufactur, yearRegistration);
        this.maxVelocity = maxVelocity;
        this.tractionType = tractionType;
        resistanceReport = new ResistanceReport(false, false, "");
    }

    public BigDecimal getMaxVelocity() {
        return maxVelocity;
    }

    public Traction getTractionType() {
        return tractionType;
    }

    @Override
    public String getTechnicalDescription() {
        return "%s, fabricado por %s en %s, usa tracción %s y alcanza hasta %s km/h."
                .formatted(this.getName(), this.getManufacturer(), this.getYearManufactur(), this.tractionType, this.maxVelocity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LandRobot landRobot = (LandRobot) o;
        return Objects.equals(maxVelocity, landRobot.maxVelocity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxVelocity);
    }

    @Override
    public ResistanceReport getResistanceReport() {
        return resistanceReport;
    }

    @Override
    public void setResistanceReport(ResistanceReport report) {
        resistanceReport = report;
    }
}
