package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsForm {
    //goodsName,goodsCategory,goodsIce,goodsAmount,goodsPrice
    @NotEmpty(message = "상품명은 필수항목입니다.")
    private String goodsName;

    @NotEmpty(message = "항목은 필수항목입니다.")
    private String goodsCategory;

    @NotEmpty(message = "아이스/핫 여부는 필수항목입니다.")
    private Boolean goodsIce;

    @NotEmpty(message = "재고는 필수항목입니다.")
    private Integer goodsAmount;

    @NotEmpty(message = "단가는 필수항목입니다.")
    private Integer goodsPrice;
}
