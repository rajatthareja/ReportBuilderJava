package com.rajatthareja.reportbuilder.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {
    private Result result = new Result();
    private String keyword;
    private String name;
    private String line;
    private List<Row> rows;
    private List<Embedding> embeddings;
    private List<Hook> after;
    private List<Hook> before;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public List<Embedding> getEmbeddings() {
        return embeddings;
    }

    public void setEmbeddings(List<Embedding> embeddings) {
        this.embeddings = embeddings;
    }

    public List<Hook> getAfter() {
        return after;
    }

    public void setAfter(List<Hook> after) {
        this.after = after;
    }

    public List<Hook> getBefore() {
        return before;
    }

    public void setBefore(List<Hook> before) {
        this.before = before;
    }
}