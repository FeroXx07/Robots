package Robots.Aero;

import Robots.ResistanceEvaluable;
import Robots.ResistanceReport;
import Robots.Robot;

import java.math.BigDecimal;
import java.util.Objects;

public class AeroRobot extends Robot implements ResistanceEvaluable {
    private final BigDecimal maxAltitude;
    private final BigDecimal autonomy;
    private ResistanceReport resistanceReport;

    public AeroRobot(String name, String manufacturer, int yearManufactur, int yearRegistration, BigDecimal maxAltitude, BigDecimal autonomy) {
        super(name, manufacturer, yearManufactur, yearRegistration);
        this.maxAltitude = maxAltitude;
        this.autonomy = autonomy;
        resistanceReport = new ResistanceReport(false, false, "");
    }

    public BigDecimal getMaxAltitude() {
        return maxAltitude;
    }

    public BigDecimal getAutonomy() {
        return autonomy;
    }

    @Override
    public String getTechnicalDescription() {
        return "%s vuela hasta %s m durante %s minutos. Fabricado en %d por %s."
                .formatted(this.getName(), this.maxAltitude, this.autonomy, this.getYearManufactur(), this.getManufacturer());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AeroRobot aeroRobot = (AeroRobot) o;
        return Objects.equals(maxAltitude, aeroRobot.maxAltitude) && Objects.equals(autonomy, aeroRobot.autonomy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxAltitude, autonomy);
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
