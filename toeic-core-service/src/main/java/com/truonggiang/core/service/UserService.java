package com.truonggiang.core.service;

import com.truonggiang.core.dto.CheckLoginDTO;
import com.truonggiang.core.dto.ImportUserDTO;
import com.truonggiang.core.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {

    CheckLoginDTO checkLogin(String name, String password);
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById (Integer userId);
    void saveUser (UserDTO userDTO);
    UserDTO updateUser (UserDTO userDTO);
    void validatedImportUser (List<ImportUserDTO> importUserDTOS);

    void saveImportUser (List<ImportUserDTO> importUserDTOS);

    Integer deleteUser(List<Integer> ids);
}
