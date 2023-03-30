package com.truonggiang.core.utils;

import com.truonggiang.core.dto.ExerciseQuestionDTO;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.persistence.entity.ExerciseQuestionEntity;
import com.truonggiang.core.persistence.entity.RoleEntity;

public class ExerciseQuestionBeanUtil {
    //Entity -> DTO
    public static ExerciseQuestionDTO entityToDTO (ExerciseQuestionEntity entity) {
        ExerciseQuestionDTO dto = new ExerciseQuestionDTO();

        dto.setExerciseQuestionId(entity.getExerciseQuestionId());
        dto.setImage(entity.getImage());
        dto.setAudio(entity.getAudio());
        dto.setQuestion(entity.getQuestion());
        dto.setOption1(entity.getOption1());
        dto.setOption2(entity.getOption2());
        dto.setOption3(entity.getOption3());
        dto.setOption4(entity.getOption4());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setCorrectAnswer(entity.getCorrectAnswer());

        return dto;
    }

    //DTO -> Entity
    public static ExerciseQuestionEntity dtoToEntity (ExerciseQuestionDTO dto) {
        ExerciseQuestionEntity entity = new ExerciseQuestionEntity();

        entity.setExerciseQuestionId(dto.getExerciseQuestionId());
        entity.setImage(dto.getImage());
        entity.setAudio(dto.getAudio());
        entity.setQuestion(dto.getQuestion());
        entity.setOption1(dto.getOption1());
        entity.setOption2(dto.getOption2());
        entity.setOption3(dto.getOption3());
        entity.setOption4(dto.getOption4());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setCorrectAnswer(dto.getCorrectAnswer());

        return entity;
    }
}
