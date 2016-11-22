package com.aimslabs.domains;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questionList;

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

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
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
                ", questionList=" + questionList +
                ", parent=" + parent +
                '}';
    }
}
