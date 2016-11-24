package com.aimslabs.domains;

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
    private float age;
    private Date birthDate;
    private boolean appResult;
    private String doctorNote;
    private boolean doctorResult;

    @ElementCollection
    private List<QuestionResponse> responseList;

    @ManyToOne(cascade = CascadeType.ALL)
    private Parent parent;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", appResult=" + appResult +
                ", doctorNote='" + doctorNote + '\'' +
                ", doctorResult=" + doctorResult +
                ", responseList=" + responseList +
                ", parent=" + parent +
                '}';
    }
}

