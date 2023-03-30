package com.truonggiang.core.utils;

import com.truonggiang.core.dto.ExaminationQuestionDTO;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.persistence.entity.ExaminationQuestionEntity;
import com.truonggiang.core.persistence.entity.RoleEntity;

public class ExaminationQuestionBeanUtil {
    //Entity -> DTO
    public static ExaminationQuestionDTO entityToDTO (ExaminationQuestionEntity entity) {
        ExaminationQuestionDTO dto = new ExaminationQuestionDTO();

        dto.setExaminationQuestionId(entity.getExaminationQuestionId());
        dto.setImage(entity.getImage());
        dto.setAudio(entity.getAudio());
        dto.setQuestion(entity.getQuestion());
        dto.setParagraph(entity.getParagraph());
        dto.setOption1(entity.getOption1());
        dto.setOption2(entity.getOption2());
        dto.setOption3(entity.getOption3());
        dto.setOption4(entity.getOption4());
        dto.setCorrectAnswer(entity.getCorrectAnswer());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setType(entity.getType());

        return dto;
    }

    //DTO -> Entity
    public static ExaminationQuestionEntity dtoToEntity (ExaminationQuestionDTO dto) {
        ExaminationQuestionEntity entity = new ExaminationQuestionEntity();

        entity.setExaminationQuestionId(dto.getExaminationQuestionId());
        entity.setImage(dto.getImage());
        entity.setAudio(dto.getAudio());
        entity.setQuestion(dto.getQuestion());
        entity.setParagraph(dto.getParagraph());
        entity.setOption1(dto.getOption1());
        entity.setOption2(dto.getOption2());
        entity.setOption3(dto.getOption3());
        entity.setOption4(dto.getOption4());
        entity.setCorrectAnswer(dto.getCorrectAnswer());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setType(dto.getType());

        return entity;
    }
}
