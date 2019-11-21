package com.myperson.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/12/20 0020.
 */
public class Menu implements Serializable {
    private Integer id;
    private String title;
    private String roleName;
    private List<PageLink> pageLinks;
    public Menu() {
    }

    public Menu(Integer id, String title, String roleName, List<PageLink> pageLinks) {
        this.id = id;
        this.title = title;
        this.roleName = roleName;
        this.pageLinks = pageLinks;
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

    public List<PageLink> getPageLinks() {
        return pageLinks;
    }

    public void setPageLinks(List<PageLink> pageLinks) {
        this.pageLinks = pageLinks;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", roleName='" + roleName + '\'' +
                ", pageLinks=" + pageLinks +
                '}';
    }
}
