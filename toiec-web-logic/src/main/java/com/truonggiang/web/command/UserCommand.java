package com.truonggiang.web.command;

import com.truonggiang.core.dto.ImportUserDTO;
import com.truonggiang.core.dto.RoleDTO;
import com.truonggiang.core.dto.UserDTO;
import com.truonggiang.core.web.command.AbstractCommand;

import java.util.ArrayList;
import java.util.List;

public class UserCommand extends AbstractCommand<UserDTO> {
    public UserCommand () {
        this.pojo = new UserDTO();
    }
    private String confirmPassword;
    private List<RoleDTO> roles;
    private Integer roleId;
    private List<ImportUserDTO> importUserDTOS;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<ImportUserDTO> getImportUserDTOS() {
        return importUserDTOS;
    }

    public void setImportUserDTOS(List<ImportUserDTO> importUserDTOS) {
        this.importUserDTOS = importUserDTOS;
    }
}
