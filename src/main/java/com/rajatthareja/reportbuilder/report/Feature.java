package com.rajatthareja.reportbuilder.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {
    private String id;
    private String description;
    private String keyword;
    private String name;
    private String line;
    private String uri;
    private List<Scenario> scenarios;
    private List<Tag> tags;

    public Duration getDuration() {
        Duration duration = Duration.ofSeconds(0);
        if (scenarios != null) {
            for (Scenario scenario : scenarios) {
                duration = duration.plus(scenario.getDuration());
            }
        }
        return duration;
    }

    public String getStatus() {
        return scenarios.stream().anyMatch(s -> s.getStatus().equals("failed")) ? "broken" :
                (scenarios.stream().allMatch(s -> s.getStatus().equals("passed")) ? "working" : "incomplete");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty("elements")
    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}