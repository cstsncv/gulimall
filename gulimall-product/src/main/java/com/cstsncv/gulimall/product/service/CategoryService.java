package com.cstsncv.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cstsncv.common.utils.PageUtils;
import com.cstsncv.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * ÉÌÆ·Èý¼¶·ÖÀà
 *
 * @author csts
 * @email csts@gmail.com
 * @date 2022-02-23 15:26:44
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();
}

