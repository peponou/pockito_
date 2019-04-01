package com.urlshortener.pockito.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "URLs")
public class URLEntity {
    @Id
    private String id;
    private String originalUrl;
    private String shortId;
    private long seq;

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public URLEntity() {
    }

    public URLEntity (String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public URLEntity(String originalUrl, String shortId) {
        this.originalUrl = originalUrl;
        this.shortId = shortId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }
}
