package com.trantan.newspagesmanagerment.model;

import com.trantan.newspagesmanagerment.model.response.Post;

import java.util.List;

public class TopicModel {
    private String title;
    private List<Post> posts;

    public TopicModel() {
    }

    public TopicModel(String title, List<Post> posts) {
        this.title = title;
        this.posts = posts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
