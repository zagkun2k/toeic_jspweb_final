package com.truonggiang.core.utils;

import com.truonggiang.core.dto.ResultDTO;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.persistence.entity.ResultEntity;
import com.truonggiang.core.persistence.entity.RoleEntity;

public class ResultBeanUtil {
    //Entity -> DTO
    public static ResultDTO entityToDTO (ResultEntity entity) {
        ResultDTO dto = new ResultDTO();

        dto.setResultId(entity.getResultId());
        dto.setListenScore(entity.getListenScore());
        dto.setReadingScore(entity.getReadingScore());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    //DTO -> Entity
    public static ResultEntity dtoToEntity (ResultDTO dto) {
        ResultEntity entity = new ResultEntity();

        entity.setResultId(dto.getResultId());
        entity.setListenScore(dto.getListenScore());
        entity.setReadingScore(dto.getReadingScore());
        entity.setCreatedDate(dto.getCreatedDate());

        return entity;
    }
}
