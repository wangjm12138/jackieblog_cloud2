package com.jackie.jackieblog.article.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuTreeDTO {

    private Integer menuId;

    private String menuName;

    private String menuIcon;

    private List<MenuTreeDTO> children;

}
