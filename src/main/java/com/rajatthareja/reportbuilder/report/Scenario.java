package com.rajatthareja.reportbuilder.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Scenario {
    private List<Tag> tags;
    private List<Hook> after;
    private String id;
    private String description;
    private List<Hook> before;
    private String keyword;
    private String name;
    private String line;
    private List<Step> steps = new ArrayList<>();
    private String type;
    private List<Example> examples;

    public String getStatus() {
        return steps.stream().anyMatch(s -> s.getResult().getStatus().equals("failed")) ? "failed" :
                (steps.stream().allMatch(s -> s.getResult().getStatus().equals("passed")) ? "passed" : "skipped");
    }

    public String getError() {
        String error = null;
        for (Step step : steps) {
            if (step.getResult().getErrorMessage() != null) {
                error = step.getResult().getErrorMessage().split("\n")[0];
                break;
            }
        }
        return error;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Hook> getAfter() {
        return after;
    }

    public void setAfter(List<Hook> after) {
        this.after = after;
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

    public List<Hook> getBefore() {
        return before;
    }

    public void setBefore(List<Hook> before) {
        this.before = before;
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

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }
}