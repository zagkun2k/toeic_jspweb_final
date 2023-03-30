package com.truonggiang.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ExaminationDTO implements Serializable {
    private Integer examinationId;
    private String name;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private List<ExaminationQuestionDTO> examinationQuestionDTOS;
    private List<ResultDTO> resultDTOS;

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<ExaminationQuestionDTO> getExaminationQuestionDTOS() {
        return examinationQuestionDTOS;
    }

    public void setExaminationQuestionDTOS(List<ExaminationQuestionDTO> examinationQuestionDTOS) {
        this.examinationQuestionDTOS = examinationQuestionDTOS;
    }

    public List<ResultDTO> getResultDTOS() {
        return resultDTOS;
    }

    public void setResultDTOS(List<ResultDTO> resultDTOS) {
        this.resultDTOS = resultDTOS;
    }
}
