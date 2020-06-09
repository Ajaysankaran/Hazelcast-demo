package com.hazelcast.example.HzTest.entity;

import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.keyvalue.annotation.KeySpace;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@KeySpace("ProductMap")
@Table
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductMap implements Serializable {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;

    private Integer category;

    private Integer productType;
}
