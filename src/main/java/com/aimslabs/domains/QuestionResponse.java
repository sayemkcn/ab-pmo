package com.aimslabs.domains;

import javax.persistence.Entity;

/**
 * Created by sayemkcn on 11/23/16.
 */
@Entity
public class QuestionResponse extends BaseEntity{

    private int questionId;

    private boolean userResponse;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isUserResponse() {
        return userResponse;
    }

    public void setUserResponse(boolean userResponse) {
        this.userResponse = userResponse;
    }

    @Override
    public String toString() {
        return "QuestionResponse{" +
                "questionId=" + questionId +
                ", userResponse=" + userResponse +
                '}';
    }
}
