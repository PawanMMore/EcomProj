package com.pawan.productcatalogservice.models;

import java.util.Date;

public abstract class BaseModel {
    private long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private  State state;
}
