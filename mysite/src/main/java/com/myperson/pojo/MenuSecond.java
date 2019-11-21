package com.myperson.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/10/18 0018.
 */
public class MenuSecond implements Serializable {
      private Integer id;
      private String pageTitle;
      private String link;
      private String roleMsg;
      private String icon;

      public MenuSecond() {
      }

      public MenuSecond(Integer id, String pageTitle, String link, String roleMsg, String icon) {
            this.id = id;
            this.pageTitle = pageTitle;
            this.link = link;
            this.roleMsg = roleMsg;
            this.icon = icon;
      }

      public Integer getId() {
            return id;
      }

      public void setId(Integer id) {
            this.id = id;
      }

      public String getPageTitle() {
            return pageTitle;
      }

      public void setPageTitle(String pageTitle) {
            this.pageTitle = pageTitle;
      }

      public String getLink() {
            return link;
      }

      public void setLink(String link) {
            this.link = link;
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
            return "MenuSecond{" +
                    "id=" + id +
                    ", pageTitle='" + pageTitle + '\'' +
                    ", link='" + link + '\'' +
                    ", roleMsg='" + roleMsg + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
      }
}
