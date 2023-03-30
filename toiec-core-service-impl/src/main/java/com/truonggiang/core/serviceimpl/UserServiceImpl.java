package com.truonggiang.core.serviceimpl;

import com.truonggiang.core.dao.UserDao;
import com.truonggiang.core.daoimpl.UserDaoImpl;
import com.truonggiang.core.dto.CheckLoginDTO;
import com.truonggiang.core.dto.ImportUserDTO;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.dto.UserDTO;
import com.truonggiang.core.persistence.entity.RoleEntity;
import com.truonggiang.core.persistence.entity.UserEntity;
import com.truonggiang.core.service.UserService;
import com.truonggiang.core.utils.SingletonDaoUtil;
import com.truonggiang.core.utils.UserBeanUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimestampFunction;

import java.sql.Timestamp;
import java.util.*;

public class UserServiceImpl implements UserService {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
    @Override
    public CheckLoginDTO checkLogin(String name, String password) {
        CheckLoginDTO checkLoginDTO = new CheckLoginDTO();
        if (name != null && password != null) {
            Object[] objects = SingletonDaoUtil.getUserDaoImpl().checkLogin(name, password);
            checkLoginDTO.setUserExist((Boolean) objects[0]);
            if (checkLoginDTO.isUserExist()) {
                checkLoginDTO.setRoleName(objects[1].toString());
            }
        }
        return checkLoginDTO;
    }

    @Override
    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoImpl().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity item : (List<UserEntity>) objects[1]) {
            UserDTO dto = UserBeanUtil.entityToDTO(item);
            userDTOS.add(dto);
        }
        objects[1] = userDTOS;
        return objects;
    }

    @Override
    public UserDTO findById(Integer userId) {
        UserEntity entity = SingletonDaoUtil.getUserDaoImpl().findById(userId);
        UserDTO dto = UserBeanUtil.entityToDTO(entity);
        return dto;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        userDTO.setCreatedDate(createdDate);
        UserEntity entity = UserBeanUtil.dtoToEntity(userDTO);
        SingletonDaoUtil.getUserDaoImpl().save(entity);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity entity = UserBeanUtil.dtoToEntity(userDTO);
        entity = SingletonDaoUtil.getUserDaoImpl().update(entity);
        userDTO = UserBeanUtil.entityToDTO(entity);
        return userDTO;
    }

    @Override
    public void validatedImportUser(List<ImportUserDTO> importUserDTOS) {
        List<String> names = new ArrayList<>();
        List<String> roles = new ArrayList<>();

        for (ImportUserDTO item : importUserDTOS) {
            if (item.isValid()) {
                names.add(item.getUserName());
                if (!roles.contains(item.getRoleName())) {
                    roles.add(item.getRoleName());
                }
            }
        }

        Map<String, UserEntity> userEntityMap = new HashMap<>();
        Map<String, RoleEntity> roleEntityMap = new HashMap<>();

        if (names.size() > 0) {
            List<UserEntity> userEntityList = SingletonDaoUtil.getUserDaoImpl().findUserByName(names);
            for (UserEntity item : userEntityList) {
                userEntityMap.put(item.getName().toUpperCase(), item);
            }
        }

        if(roles.size() > 0) {
            List<RoleEntity> roleEntityList = SingletonDaoUtil.getRoleDaoImpl().findRoleByName(roles);
            for (RoleEntity item : roleEntityList) {
                roleEntityMap.put(item.getName().toUpperCase(), item);
            }
        }

        for (ImportUserDTO item : importUserDTOS) {
            String messageResponse = item.getError();
            if (item.isValid()) {
                if (userEntityMap.containsKey(item.getUserName().toUpperCase())) {
                    messageResponse += "<br/>";
                    messageResponse += resourceBundle.getString("label.user.message.valid.user.alreadyExist");
                    item.setValid(false);
                }
            }
            if (item.isValid()) {
                if (!roleEntityMap.containsKey(item.getRoleName().toUpperCase())) {
                    messageResponse += "<br/>";
                    messageResponse += resourceBundle.getString("label.user.message.valid.user.notSameRoleDatabase");
                    item.setValid(false);
                }
            }
            if (StringUtils.isNotBlank(messageResponse)) {
                messageResponse.substring(5);
                item.setError(messageResponse);
            }
        }
    }

    @Override
    public void saveImportUser(List<ImportUserDTO> importUserDTOS) {
        for (ImportUserDTO item : importUserDTOS) {
            if (item.isValid()) {
                UserEntity entity = new UserEntity();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                RoleEntity roleEntity = SingletonDaoUtil.getRoleDaoImpl().findEniqueValue("name", item.getRoleName());

                entity.setName(item.getUserName());
                entity.setPassword(item.getPassword());
                entity.setFullName(item.getFullName());
                entity.setCreatedDate(timestamp);
                entity.setRole(roleEntity);

                SingletonDaoUtil.getUserDaoImpl().save(entity);
            }
        }
    }

    @Override
    public Integer deleteUser(List<Integer> ids) {
        Integer result = SingletonDaoUtil.getUserDaoImpl().delete(ids);
        return result;
    }
}
