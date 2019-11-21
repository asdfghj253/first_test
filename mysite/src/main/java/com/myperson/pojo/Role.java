package com.myperson.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/9/26 0026.
 */
public class Role implements Serializable {
    private Integer roleID;
    private String roleName;
    private Integer roleEnable=1;

    public Role() {
    }

    public Role(Integer roleID, String roleName, Integer roleEnable) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.roleEnable = roleEnable;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleEnable() {
        return roleEnable;
    }

    public void setRoleEnable(Integer roleEnable) {
        this.roleEnable = roleEnable;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                ", roleEnable=" + roleEnable +
                '}';
    }
}
