package com.truonggiang.core.utils;

import com.truonggiang.core.dto.ExaminationDTO;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.persistence.entity.ExaminationEntity;
import com.truonggiang.core.persistence.entity.RoleEntity;

public class ExaminationBeanUtil {
    //Entity -> DTO
    public static ExaminationDTO entityToDTO (ExaminationEntity entity) {
        ExaminationDTO dto = new ExaminationDTO();

        dto.setExaminationId(entity.getExaminationId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());

        return dto;
    }

    //DTO -> Entity
    public static ExaminationEntity dtoToEntity (ExaminationDTO dto) {
        ExaminationEntity entity = new ExaminationEntity();

        entity.setExaminationId(dto.getExaminationId());
        entity.setName(dto.getName());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());

        return entity;
    }
}
