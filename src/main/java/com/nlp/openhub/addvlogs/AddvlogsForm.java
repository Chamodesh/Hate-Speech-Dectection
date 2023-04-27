package com.nlp.openhub.addvlogs;

public class AddvlogsForm {
    String topic;
    String vlog;
    String category;
    String username;

    public AddvlogsForm() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getVlog() {
        return vlog;
    }

    public void setVlog(String vlog) {
        this.vlog = vlog;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
