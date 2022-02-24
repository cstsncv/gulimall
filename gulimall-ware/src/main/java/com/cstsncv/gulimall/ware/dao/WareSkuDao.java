package com.cstsncv.gulimall.ware.dao;

import com.cstsncv.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author csts
 * @email csts@gmail.com
 * @date 2022-02-24 16:01:23
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
