package com.hazelcast.example.HzTest.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@KeySpace("ProductMap")
@Data
public class ProductMapKV implements Serializable {

    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    private Integer Id;

    private String name;

    private Integer category;

    private Integer productType;
}
