package com.test.util;

public class RestApiUtils {

    public Integer getGoodsRemains(String goodsId) {
        HttpMethods httpMethods = new HttpMethods();
        httpMethods.get(EndPoint.GET_GOODS_REMAINS_BY_ID + "/?conditions=goods_id IN (" + goodsId + ")",200).
                then();
        return 1;
    }

}
