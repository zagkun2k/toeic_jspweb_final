package com.truonggiang.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ExaminationQuestionDTO implements Serializable {
    private Integer examinationQuestionId;
    private String image;
    private String audio;
    private String question;
    private String paragraph;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private ExaminationDTO examinationDTO;
    private String type;
    private Integer number;
    private String answerUser;


    public Integer getExaminationQuestionId() {
        return examinationQuestionId;
    }

    public void setExaminationQuestionId(Integer examinationQuestionId) {
        this.examinationQuestionId = examinationQuestionId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ExaminationDTO getExaminationDTO() {
        return examinationDTO;
    }

    public void setExaminationDTO(ExaminationDTO examinationDTO) {
        this.examinationDTO = examinationDTO;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
    }
}
