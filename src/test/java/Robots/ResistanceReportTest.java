package Robots;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResistanceReportTest {

    @Test
    void shouldInitialStateBeFalseOrEmpty(){
        ResistanceReport resistanceReport = new ResistanceReport(false, false, "");
        assertThat(resistanceReport.evaluated()).isFalse();
        assertThat(resistanceReport.suitable()).isFalse();
        assertThat(resistanceReport.reason()).isBlank();
    }
}