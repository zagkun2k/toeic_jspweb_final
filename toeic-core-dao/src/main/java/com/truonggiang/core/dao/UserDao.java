package com.truonggiang.core.dao;

import com.truonggiang.core.data.dao.GenericDao;
//import com.truonggiang.core.persistence.entity.RoleEntity;
import com.truonggiang.core.persistence.entity.UserEntity;

import java.util.List;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    Object[] checkLogin(String name, String password);

    List<UserEntity> findUserByName (List<String> names);
}
