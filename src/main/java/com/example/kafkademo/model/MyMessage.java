package com.example.kafkademo.model;

public class MyMessage {
    private String id;
    private String content;

    public MyMessage() {} // Required for JSON deserialization

    public MyMessage(String id, String content) {
        this.id = id;
        this.content = content;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return "MyMessage{id='" + id + "', content='" + content + "'}";
    }
}