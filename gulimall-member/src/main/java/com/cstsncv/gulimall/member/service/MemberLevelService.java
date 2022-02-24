package com.cstsncv.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cstsncv.common.utils.PageUtils;
import com.cstsncv.gulimall.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * »áÔ±µÈ¼¶
 *
 * @author csts
 * @email csts@gmail.com
 * @date 2022-02-24 15:13:31
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

