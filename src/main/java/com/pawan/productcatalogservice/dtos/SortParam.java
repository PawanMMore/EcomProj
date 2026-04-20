package com.pawan.productcatalogservice.dtos;

public class SortParam {
    private String paramName;
    private String order;

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getParamName() {
        return paramName;
    }

    public String getOrder() {
        return order;
    }
}
