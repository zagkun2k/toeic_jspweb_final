package com.truonggiang.core.utils;

import com.truonggiang.core.dto.ListenGuidelineDTO;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.persistence.entity.ListenGuidelineEntity;
import com.truonggiang.core.persistence.entity.RoleEntity;

public class ListenGuidelineBeanUtil {

    // Entity -> DTO
    public static ListenGuidelineDTO entityToDTO (ListenGuidelineEntity entity) {
        ListenGuidelineDTO dto = new ListenGuidelineDTO();

        dto.setListenGuidelineId(entity.getListenGuidelineId());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());

        return dto;
    }

    //DTO -> Entity
    public static ListenGuidelineEntity dtoToEntity (ListenGuidelineDTO dto) {
        ListenGuidelineEntity entity = new ListenGuidelineEntity();

        entity.setListenGuidelineId(dto.getListenGuidelineId());
        entity.setTitle(dto.getTitle());
        entity.setImage(dto.getImage());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());

        return entity;
    }
}
