package com.truonggiang.core.utils;

import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.persistence.entity.RoleEntity;

public class RoleBeanUtil {
    //Entity -> DTO
    public static RoleDTO entityToDTO (RoleEntity entity) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setName(entity.getName());
        return dto;
    }

    //DTO -> Entity
    public static RoleEntity dtoToEntity (RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(dto.getRoleId());
        entity.setName(dto.getName());
        return entity;
    }
}
