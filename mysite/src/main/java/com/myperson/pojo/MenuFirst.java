package com.myperson.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/10/18 0018.
 */
public class MenuFirst implements Serializable {
    private Integer id;
    private String title;
    private String roleName;
    private List<MenuSecond> menuSeconds;

    public MenuFirst() {
    }

    public MenuFirst(Integer id, String title, String roleName, List<MenuSecond> menuSeconds) {
        this.id = id;
        this.title = title;
        this.roleName = roleName;
        this.menuSeconds = menuSeconds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<MenuSecond> getMenuSeconds() {
        return menuSeconds;
    }

    public void setMenuSeconds(List<MenuSecond> menuSeconds) {
        this.menuSeconds = menuSeconds;
    }

    @Override
    public String toString() {
        return "MenuFirst{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", roleName='" + roleName + '\'' +
                ", menuSeconds=" + menuSeconds +
                '}';
    }
}
