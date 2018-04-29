package com.rajatthareja.reportbuilder;

import freemarker.template.Configuration;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ReportBuilder builds HTML report from cucumber json reports
 *
 * <br>Builds report from given list of:
 * <ul>
 *  <li>json files</li>
 *  <li>directories containing json files</li>
 *  <li>json file urls</li>
 *  <li>json strings</li>
 *  <li>json objects</li>
 * </ul>
 */
public class ReportBuilder {

    private String reportTitle, reportDirectory, reportFileName, reportColor;
    private Map<String, String> additionalInfo;
    private boolean voiceControl;

    /**
     * Get output report directory
     *
     * @return output report directory
     */
    public String getReportDirectory() {
        return reportDirectory;
    }

    /**
     * Set output report directory
     *
     * @param reportDirectory output report directory
     */
    public void setReportDirectory(String reportDirectory) {
        this.reportDirectory = reportDirectory;
    }

    /**
     * Get output report file name without extension
     *
     * @return output report file name without extension
     */
    public String getReportFileName() {
        return reportFileName;
    }

    /**
     * Set output report file name without extension
     *
     * @param reportFileName output report file name without extension
     */
    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    /**
     * Get output report title
     *
     * @return output report title
     */
    public String getReportTitle() {
        return reportTitle;
    }

    /**
     * Set output report title
     *
     * @param reportTitle output report title
     */
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    /**
     * Get additional info set by setAdditionalInfo
     *
     * @return additional info set by setAdditionalInfo
     */
    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Set additional info to be added in output report
     *
     * @param  additionalInfo additional info to be added in output report
     */
    public void setAdditionalInfo(Map<String, String> additionalInfo) {
        this.additionalInfo.putAll(additionalInfo);
    }

    /**
     * Get additional info value set by setAdditionalInfo for the given key
     *
     * @param key additional info key
     *
     * @return additional info value
     */
    public String getAdditionalInfo(String key) {
        return additionalInfo.get(key);
    }

    /**
     * Set additional info to be added in output report
     *
     * @param key additional info key
     * @param value additional info key
     */
    public void setAdditionalInfo(String key, String value) {
        additionalInfo.put(key, value);
    }

    /**
     * Clear additional info
     */
    public void clearAdditionalInfo() {
        additionalInfo.clear();
    }

    /**
     * Ger Report Color
     *
     * @return Report color
     */
    public String getReportColor() {
        return reportColor;
    }

    /**
     * Set Report color
     *
     * @param reportColor Report Color
     */
    public void setReportColor(String reportColor) {
        this.reportColor = reportColor;
    }

    /**
     * Set Report color
     *
     * @param reportColor Report Color
     */
    public void setReportColor(Color reportColor) {
        this.reportColor = reportColor.toString();
    }

    /**
     * Enable voice control for report
     */
    public void enableVoiceControl() {
        voiceControl = true;
    }

    /**
     * Disable voice control
     */
    public void disableVoiceControl() {
        voiceControl = false;
    }

    /**
     * Creates report builder object for customizing and building report
     */
    public ReportBuilder() {
        reportDirectory = "";
        reportFileName = "report";
        reportTitle = "Test Report";
        reportColor = Color.BROWN.toString();
        additionalInfo = new HashMap<>();
        voiceControl = false;
    }

    /**
     * Build consolidated html report from given cucumber json reports
     *
     * @param cucumberJsonReports list of json files or directory containing json files or json file urls or json strings or json objects
     */
    public void build(List<Object> cucumberJsonReports) {
        build(cucumberJsonReports.toArray());
    }

        /**
         * Build consolidated html report from given cucumber json reports
         *
         * @param cucumberJsonReports list of json files or directory containing json files or json file urls or json strings or json objects
         */
    public void build(Object ... cucumberJsonReports) {

        Report report = new Report(cucumberJsonReports);

        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        config.setClassForTemplateLoading(ReportBuilder.class, "template");

        Map<String, Object> data = new HashMap<>();
        data.put("reportTitle", reportTitle);
        data.put("reportColor", reportColor);
        data.put("additionalInfo", additionalInfo);
        data.put("voiceControl", voiceControl);
        data.put("report", report);

        new File(reportDirectory).mkdirs();

        try (PrintWriter out = new PrintWriter(reportDirectory + reportFileName + ".html")) {
            config.getTemplate("report.ftl").process(data, out);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to build report!");
        }
    }
}
