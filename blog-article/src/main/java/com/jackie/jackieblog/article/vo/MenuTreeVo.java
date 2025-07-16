package com.jackie.jackieblog.article.vo;

import com.jackie.jackieblog.article.dto.MenuTreeDTO;
import lombok.Data;

import java.util.List;

/**
 * @version 1.0.0
 * @Author: Jackie Wang
 * @WechatID: star373629168
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.com
 * @Date: 2024/12/26 20:44
 */
@Data
public class MenuTreeVo {

    private Integer menuId;

    private String menuName;

    private String menuIcon;

    private List<MenuTreeDTO> children;


}
