package com.truonggiang.core.utils;

import com.truonggiang.core.dto.ExerciseDTO;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.persistence.entity.ExerciseEntity;
import com.truonggiang.core.persistence.entity.RoleEntity;

public class ExerciseBeanUtil {
    //Entity -> DTO
    public static ExerciseDTO entityToDTO (ExerciseEntity entity) {
        ExerciseDTO dto = new ExerciseDTO();

        dto.setExerciseId(entity.getExerciseId());
        dto.setName(entity.getName());
        dto.setCreateDate(entity.getCreateDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setType(entity.getType());

        return dto;
    }

    //DTO -> Entity
    public static ExerciseEntity dtoToEntity (ExerciseDTO dto) {
        ExerciseEntity entity = new ExerciseEntity();

        entity.setExerciseId(dto.getExerciseId());
        entity.setName(dto.getName());
        entity.setCreateDate(dto.getCreateDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setType(dto.getType());

        return entity;
    }
}
