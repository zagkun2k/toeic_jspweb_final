package com.truonggiang.core.serviceimpl;

import com.truonggiang.core.common.utils.HibernateUtil;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.persistence.entity.RoleEntity;
import com.truonggiang.core.persistence.entity.UserEntity;
import com.truonggiang.core.service.RoleService;
import com.truonggiang.core.utils.RoleBeanUtil;
import com.truonggiang.core.utils.SingletonDaoUtil;
import org.hibernate.HibernateError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    @Override
    public List<RoleDTO> findAll() {
        List<RoleEntity> entities = SingletonDaoUtil.getRoleDaoImpl().findAll();
        List<RoleDTO> dtos = new ArrayList<>();
        for (RoleEntity item : entities) {
            RoleDTO dto = RoleBeanUtil.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }
}
