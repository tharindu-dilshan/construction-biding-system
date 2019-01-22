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
public class Post {
    private int id;
    private User user;
    private String title;
    private String description;
    private Double budget;
    private Date time;
    private String email;
    private String contactNo;
    private InputStream attachment;
    private String attachmentName;
    private Short status;

    public Post() {
    }

    public Post(int id) {
        this.id = id;
    }

    public Post(int id, User user, String title, String description, Double budget, Date time, String email, String contactNo, InputStream attachment, String attachmentName, Short status) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.time = time;
        this.email = email;
        this.contactNo = contactNo;
        this.attachment = attachment;
        this.attachmentName = attachmentName;
        this.status = status;
    }

    public Post(User user, String title, String description, Double budget, Date time, String email, String contactNo, InputStream attachment, String attachmentName, Short status) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.time = time;
        this.email = email;
        this.contactNo = contactNo;
        this.attachment = attachment;
        this.attachmentName = attachmentName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getBudget() {
        return budget;
    }

    public Date getTime() {
        return time;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public InputStream getAttachment() {
        return attachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setAttachment(InputStream attachment) {
        this.attachment = attachment;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", user=" + user + ", title=" + title + ", description=" + description + ", budget=" + budget + ", time=" + time + ", email=" + email + ", contactNo=" + contactNo + ", attachment=" + attachment + ", attachmentName=" + attachmentName + ", status=" + status + '}';
    }
    
}
