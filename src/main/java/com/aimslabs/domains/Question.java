package com.aimslabs.domains;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by sayemkcn on 11/9/16.
 */
@Entity
public class Question extends BaseEntity {
    private String name;
    private int questionId;
    @Column(length = 10000000)
    @Basic(fetch = FetchType.LAZY, optional = true)
    private byte[] file;
    private String positiveText;
    private String negativeText;
    private boolean autismDitectedForPositiveText;
    private boolean critical;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getPositiveText() {
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }

    public boolean isAutismDitectedForPositiveText() {
        return autismDitectedForPositiveText;
    }

    public void setAutismDitectedForPositiveText(boolean autismDitectedForPositiveText) {
        this.autismDitectedForPositiveText = autismDitectedForPositiveText;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }
}
