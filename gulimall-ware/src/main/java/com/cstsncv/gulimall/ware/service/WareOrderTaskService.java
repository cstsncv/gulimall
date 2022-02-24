package com.cstsncv.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cstsncv.common.utils.PageUtils;
import com.cstsncv.gulimall.ware.entity.WareOrderTaskEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author csts
 * @email csts@gmail.com
 * @date 2022-02-24 16:01:23
 */
public interface WareOrderTaskService extends IService<WareOrderTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

