/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author HaShaN
 */
public class Proposal {
    private int id;
    private Post post;
    private User user;
    private String budget;
    private String duration;
    private InputStream attachment;
    private String attachmentName;
    private String description;
    private Date dateTime;

    public Proposal() {
    }

    public Proposal(int id, Post post, User user, String budget, String duration, InputStream attachment, String attachmentName, String description, Date dateTime) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.budget = budget;
        this.duration = duration;
        this.attachment = attachment;
        this.attachmentName = attachmentName;
        this.description = description;
        this.dateTime = dateTime;
    }

    public Proposal(Post post, User user, String budget, String duration, InputStream attachment, String attachmentName, String description, Date dateTime) {
        this.post = post;
        this.user = user;
        this.budget = budget;
        this.duration = duration;
        this.attachment = attachment;
        this.attachmentName = attachmentName;
        this.description = description;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public String getBudget() {
        return budget;
    }

    public String getDuration() {
        return duration;
    }

    public InputStream getAttachment() {
        return attachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }
    
    public String getDescription() {
        return description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setAttachment(InputStream attachment) {
        this.attachment = attachment;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Proposal{" + "id=" + id + ", post=" + post + ", user=" + user + ", budget=" + budget + ", duration=" + duration + ", attachment=" + attachment + ", attachmentName=" + attachmentName + ", description=" + description + ", dateTime=" + dateTime + '}';
    }
}
