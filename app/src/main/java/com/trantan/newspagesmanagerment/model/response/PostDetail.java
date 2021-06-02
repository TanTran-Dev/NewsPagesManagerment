package com.trantan.newspagesmanagerment.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.trantan.newspagesmanagerment.constants.ContentType;

public class PostDetail {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("postID")
    @Expose
    private String postID;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("contentType")
    @Expose
    private ContentType contentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
