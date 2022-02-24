package com.cstsncv.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cstsncv.common.utils.PageUtils;
import com.cstsncv.gulimall.coupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * ÃëÉ±»î¶¯ÉÌÆ·¹ØÁª
 *
 * @author csts
 * @email csts@gmail.com
 * @date 2022-02-24 14:42:51
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

