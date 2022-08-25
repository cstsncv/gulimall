package com.cstsncv.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cstsncv.common.utils.PageUtils;
import com.cstsncv.common.utils.Query;

import com.cstsncv.gulimall.product.dao.CategoryDao;
import com.cstsncv.gulimall.product.entity.CategoryEntity;
import com.cstsncv.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1. 查询所有分类
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        // 2. 组装成父子树形结构
        // 2.1 找到所有一级分类 parentCid==0
        List<CategoryEntity> level1Menus = categoryEntities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0).map((menu) -> {
                    menu.setChildren(getChildrens(menu, categoryEntities));
                    return menu;}).sorted((menu1, menu2) -> {
                        return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        // TODO 检查当前删除的菜单是否在其他地方引用
        baseMapper.deleteBatchIds(asList);
    }

    // 递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {

        List<CategoryEntity> children = all.stream().filter(categoryEntity -> categoryEntity.getParentCid() == root.getCatId()).map(
                (categoryEntity) -> {
                    // 找到子菜单
                    categoryEntity.setChildren(getChildrens(categoryEntity, all));
                    return categoryEntity;
                }
        ).sorted((menu1, menu2) -> {
            // 菜单排序
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());
        return children;
    }

}