package com.aimslabs.domains;

import com.aimslabs.domains.pojo.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sayemkcn on 11/9/16.
 */
@Entity
public class Child extends BaseEntity {
    private String name;
    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    private boolean appResult;
    private String doctorNote;
    private boolean doctorResult;
    private boolean sentFromMobileApp;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionResponse> responseList;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    @JsonBackReference
    private Parent parent;

    public boolean isSentFromMobileApp() {
        return sentFromMobileApp;
    }

    public void setSentFromMobileApp(boolean sentFromMobileApp) {
        this.sentFromMobileApp = sentFromMobileApp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<QuestionResponse> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<QuestionResponse> responseList) {
        this.responseList = responseList;
    }

    public boolean isAppResult() {
        return appResult;
    }

    public void setAppResult(boolean appResult) {
        this.appResult = appResult;
    }

    public String getDoctorNote() {
        return doctorNote;
    }

    public void setDoctorNote(String doctorNote) {
        this.doctorNote = doctorNote;
    }

    public boolean isDoctorResult() {
        return doctorResult;
    }

    public void setDoctorResult(boolean doctorResult) {
        this.doctorResult = doctorResult;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }


}

