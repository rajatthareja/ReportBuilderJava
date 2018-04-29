package com.rajatthareja.reportbuilder;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajatthareja.reportbuilder.report.Example;
import com.rajatthareja.reportbuilder.report.Feature;
import com.rajatthareja.reportbuilder.report.Row;
import com.rajatthareja.reportbuilder.report.Scenario;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Process the JSON reports and build the consolidated list of features
 */
public class Report {

    private List<Feature> features = new ArrayList<>();
    private List<String> errors = new ArrayList<>();
    private Duration duration = Duration.ofNanos(0);

    /**
     * Build consolidated list of features from given cucumber json reports
     *
     * @param cucumberJsonReports list of json files or directory containing json files or json file urls or json strings or json objects
     */
    public Report(Object ... cucumberJsonReports) {

        List<Object> jsonReports = new ArrayList<>();
        jsonReports.addAll(Arrays.asList(cucumberJsonReports));
        List<Object> reportDirs = jsonReports.stream().filter(r -> r instanceof File && ((File) r).isDirectory()).collect(Collectors.toList());

        jsonReports.removeAll(reportDirs);
        reportDirs.forEach(reportDir -> {
            try {
                jsonReports.addAll(Files.walk(Paths.get(((File) reportDir).getPath())).filter(p -> p.toString().endsWith(".json")).map(p -> new File(p.toString())).collect(Collectors.toList()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ObjectMapper mapper = new ObjectMapper();
        List<Feature[]> builds = new ArrayList<>();

        for (Object jsonReport : jsonReports) {
            try {
                if (jsonReport instanceof File) {
                    try {
                        builds.add(mapper.readValue((File) jsonReport, Feature[].class));
                    } catch (Exception e) {
                        builds.add(mapper.readValue(decode(new String(Files.readAllBytes(((File) jsonReport).toPath()))), Feature[].class));
                    }
                } else if (jsonReport instanceof URL) {
                    try {
                        builds.add(mapper.readValue((URL) jsonReport, Feature[].class));
                    } catch (Exception e) {
                        builds.add(mapper.readValue(decode(readUrl((URL) jsonReport)), Feature[].class));
                    }
                } else if (jsonReport instanceof String) {
                    try {
                        builds.add(mapper.readValue((String) jsonReport, Feature[].class));
                    } catch (Exception e) {
                        builds.add(mapper.readValue(decode((String) jsonReport), Feature[].class));
                    }
                } else if (jsonReport instanceof JSONObject) {
                    try {
                        builds.add(mapper.readValue(jsonReport.toString(), Feature[].class));
                    } catch (Exception e) {
                        builds.add(mapper.readValue(decode(jsonReport.toString()), Feature[].class));
                    }
                } else {
                        throw new Exception("Invalid jsonReport!");
                    }
            } catch (Exception e) {
                System.out.println("Error:: While mapping jsonReport");
                e.printStackTrace();
            }
        }
        process(builds);
    }

    /**
     * Process the list of array of features to generate list of features and errors
     *
     * @param builds list of array of features
     */
    private void process(List<Feature[]> builds) {
        for (Feature[] build : builds) {
            features.addAll(Arrays.asList(build));
        }

        Map<String, Feature> featuresMap = new HashMap<>();
        for (Feature feature : features) {
            if (featuresMap.get(feature.getId()) == null)
                featuresMap.put(feature.getId(), feature);
            else
                featuresMap.get(feature.getId()).getScenarios().addAll(feature.getScenarios());
        }

        features = new ArrayList<>(featuresMap.values());
        for (Feature feature : features) {
            Map<String, Scenario> scenariosMap = new HashMap<>();
            List<Row> examples = new ArrayList<>();
            for (Scenario scenario : feature.getScenarios()) {
                if (scenario.getExamples() != null) {
                    for (Example e : scenario.getExamples()) {
                        examples.addAll(e.getRows());
                    }
                    continue;
                }
                if ((scenariosMap.get(scenario.getId()) == null || scenario.getStatus().equals("passed"))) {
                    if (scenario.getKeyword().equals("Scenario Outline")) {
                        examples.stream().filter(e ->  e.getId().equals(scenario.getId())).findFirst()
                                .ifPresent(row -> scenario.setName(scenario.getName() + " " + row.getCells()));
                    }
                    String error = scenario.getError();
                    if (error != null && !errors.contains(error)) {
                        errors.add(error);
                    }
                    scenariosMap.put(scenario.getId(), scenario);
                }
            }
            feature.setScenarios(new ArrayList<>((scenariosMap.values())));
            duration = duration.plus(feature.getDuration());
        }
    }

    /**
     * Try to decode the given string to "UTF-8"
     *
     * @param json string for decoding
     *
     * @return decode string if successful else original string
     */
    private String decode(String json) {
        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPLACE);
        decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
        ByteBuffer data = ByteBuffer.wrap(json.getBytes());
        try {
            return decoder.decode(data).toString();
        } catch (CharacterCodingException e) {
            System.out.println("Error:: While decoding JSON report");
            e.printStackTrace();
            return json;
        }
    }

    /**
     * Read the file from the given url and return the contents
     *
     * @param url of the file
     *
     * @return contents of the file
     */
    private String readUrl(URL url) {
        StringBuilder content = new StringBuilder();
        try {
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error:: While reading JSON report from URL");
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * Returns list of feature for building report
     *
     * @return List of feature
     */
    public List<Feature> getFeatures() {
        return features;
    }

    /**
     * Returns list of error for building report
     *
     * @return List of errors
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Returns total execution duration
     *
     * @return Duration of execution
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Returns total execution duration string
     *
     * @return Duration of execution as string
     */
    public String getDurationString(Duration duration) {
        return duration.withNanos(0).toString().substring(2);
    }
}