package com.truonggiang.core.persistence.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "exercise")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer exerciseId;
    @Column(name = "name")
    private String name;
    @Column(name = "createddate")
    private Timestamp createDate;
    @Column(name = "modifieddate")
    private Timestamp modifiedDate;
    @OneToMany(mappedBy = "exerciseEntity", fetch = FetchType.LAZY)
    private List<ExerciseQuestionEntity> exerciseQuestionEntityList;

    @Column(name = "type")
    private String type;

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

    public List<ExerciseQuestionEntity> getExerciseQuestionEntityList() {
        return exerciseQuestionEntityList;
    }

    public void setExerciseQuestionEntityList(List<ExerciseQuestionEntity> exerciseQuestionEntityList) {
        this.exerciseQuestionEntityList = exerciseQuestionEntityList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
