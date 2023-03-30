package com.truonggiang.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ExerciseDTO implements Serializable {
    private Integer exerciseId;
    private String name;
    private Timestamp createDate;
    private Timestamp modifiedDate;
    private String type;
    private List<ExerciseQuestionDTO> exerciseQuestionDTOS;

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ExerciseQuestionDTO> getExerciseQuestionDTOS() {
        return exerciseQuestionDTOS;
    }

    public void setExerciseQuestionDTOS(List<ExerciseQuestionDTO> exerciseQuestionDTOS) {
        this.exerciseQuestionDTOS = exerciseQuestionDTOS;
    }
}
