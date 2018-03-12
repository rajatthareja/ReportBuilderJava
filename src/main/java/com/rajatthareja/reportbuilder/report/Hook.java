package com.rajatthareja.reportbuilder.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hook {
    private Result result;
    private List<Embedding> embeddings;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Embedding> getEmbeddings() {
        return embeddings;
    }

    public void setEmbeddings(List<Embedding> embeddings) {
        this.embeddings = embeddings;
    }
}