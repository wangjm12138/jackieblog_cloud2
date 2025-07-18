package com.jackie.blog.article.service;

import com.jackie.blog.article.dao.MenuServiceMapper;
import com.jackie.blog.article.dto.MenuDTO;
import com.jackie.blog.article.dto.MenuTreeDTO;
import com.jackie.blog.article.entity.convertor.MenuConvertor;
import com.jackie.blog.article.vo.MenuTreeVo;
import com.jackie.blog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @version 1.0.0
 * @Author: Jackie Wang
 * @WechatID: star373629168
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.com
 * @Date: 2024/12/15 11:08
 */
@Service
public class MenuService {

    @Autowired
    private MenuServiceMapper menuServiceMapper;


    private List<MenuTreeDTO> rootMenuTree(List<MenuDTO> records) {
        System.out.println(records);
        List<MenuTreeDTO> ret = records.stream().collect(Collectors.groupingBy(MenuDTO::getMenuId)).entrySet().stream()
                .map(menuEntry -> {
                    MenuTreeDTO menuTreeDTO = new MenuTreeDTO();
                    menuTreeDTO.setMenuId(menuEntry.getKey());
                    menuTreeDTO.setMenuName(menuEntry.getValue().get(0).getMenuName());
                    menuTreeDTO.setMenuIcon(menuEntry.getValue().get(0).getMenuIcon());

                    //二级菜单
                    menuTreeDTO.setChildren(
                            menuEntry.getValue().stream().collect(Collectors.groupingBy(MenuDTO::getCategoryId))
                                    .entrySet().stream()
                                    .map(categoryEntry -> {
                                        MenuTreeDTO cateTreeDTO = new MenuTreeDTO();
                                        cateTreeDTO.setMenuId(categoryEntry.getKey());
                                        cateTreeDTO.setMenuName(categoryEntry.getValue().get(0).getCategoryName());
                                        cateTreeDTO.setMenuIcon(categoryEntry.getValue().get(0).getCategoryIcon());
                                        //三级菜单
                                        cateTreeDTO.setChildren(
                                                categoryEntry.getValue().stream()
                                                        .filter(item -> item.getCategoryDetailsId() != null)
                                                        .map(item -> {
                                                            MenuTreeDTO detailTreeDTO = new MenuTreeDTO();
                                                            detailTreeDTO.setMenuId(item.getCategoryDetailsId());
                                                            detailTreeDTO.setMenuName(item.getCategoryDetailsName());
                                                            detailTreeDTO.setMenuIcon(item.getCategoryDetailsIcon());
                                                            return detailTreeDTO;
                                                        }).collect(Collectors.toList())
                                        );
                                        return cateTreeDTO;
                                    }).collect(Collectors.toList())
                    );
                    return menuTreeDTO;
                }).collect(Collectors.toList());
        System.out.println(ret);
        return ret;
    }

    public Result<List<MenuTreeVo>> listMenu() {
        List<MenuDTO> records = menuServiceMapper.listMenu();
        List<MenuTreeDTO>  menuTreeDTOList = rootMenuTree(records);
        return Result.success(MenuConvertor.INSTANCE.mapToVo(menuTreeDTOList));
    }

    public Result<List<MenuTreeVo>>  listAllCategoryByMenuId(Integer id) {
        List<MenuDTO> records = menuServiceMapper.listAllCategoryByMenuId(id);
        List<MenuTreeDTO>  menuTreeDTOList = rootMenuTree(records);
        return Result.success(MenuConvertor.INSTANCE.mapToVo(menuTreeDTOList));
    }
}
