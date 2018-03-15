package com.rajatthareja.reportbuilder;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Report Builder Test
 */
public class ReportBuilderTest {

    /**
     * Report Builder Test
     */
    @Test
    public void reportBuilderTest() throws Exception {
        try {
            String report = getClass().getResource("/com/rajatthareja/reportbuilder/report.html").getPath();
            String javaCucumberJson = report.replace("report.html", "cucumberJson/java");
            String rubyCucumberJson = report.replace("report.html", "cucumberJson/ruby");

            ReportBuilder reportBuilder = new ReportBuilder();
            reportBuilder.setReportTitle("Sample Test Report");
            reportBuilder.setAdditionalInfo("Info", "Some Info");
            reportBuilder.build(new File(javaCucumberJson), new File(rubyCucumberJson));

            Assert.assertEquals(new String(Files.readAllBytes(Paths.get(report))),
                    new String(Files.readAllBytes(Paths.get("report.html"))));
        } finally {
            Files.deleteIfExists(Paths.get("report.html"));
        }
    }
}
