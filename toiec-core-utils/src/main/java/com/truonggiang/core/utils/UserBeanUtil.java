package com.truonggiang.core.utils;

import com.truonggiang.core.dto.UserDTO;
import com.truonggiang.core.persistence.entity.UserEntity;

public class UserBeanUtil {

    //Entity -> DTO
    public static UserDTO entityToDTO (UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        dto.setFullName(entity.getFullName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setRoleDTO(RoleBeanUtil.entityToDTO(entity.getRole()));

        return dto;
    }

    //DTO -> Entity
    public static UserEntity dtoToEntity (UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUserId(dto.getUserId());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setRole(RoleBeanUtil.dtoToEntity(dto.getRoleDTO()));

        return entity;
    }
}
