package com.myperson.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/20 0020.
 */
public class PageLink implements Serializable {
    private Integer id;
    private String pageTitle;
    private String linkAddr;
    private String roleMsg;
    private String icon;

    public PageLink() {
    }


    public PageLink(Integer id, String pageTitle, String linkAddr, String roleMsg, String icon) {
        this.id = id;
        this.pageTitle = pageTitle;
        this.linkAddr = linkAddr;
        this.roleMsg = roleMsg;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getLinkAddr() {
        return linkAddr;
    }

    public void setLinkAddr(String linkAddr) {
        this.linkAddr = linkAddr;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getRoleMsg() {
        return roleMsg;
    }

    public void setRoleMsg(String roleMsg) {
        this.roleMsg = roleMsg;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "PageLink{" +
                "id=" + id +
                ", pageTitle='" + pageTitle + '\'' +
                ", linkAddr='" + linkAddr + '\'' +
                ", roleMsg='" + roleMsg + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
