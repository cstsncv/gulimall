package com.cstsncv.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * skuÏúÊÛÊôÐÔ&Öµ
 * 
 * @author csts
 * @email csts@gmail.com
 * @date 2022-02-23 15:26:43
 */
@Data
@TableName("pms_sku_sale_attr_value")
public class SkuSaleAttrValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * sku_id
	 */
	private Long skuId;
	/**
	 * attr_id
	 */
	private Long attrId;
	/**
	 * ÏúÊÛÊôÐÔÃû
	 */
	private String attrName;
	/**
	 * ÏúÊÛÊôÐÔÖµ
	 */
	private String attrValue;
	/**
	 * Ë³Ðò
	 */
	private Integer attrSort;

}
