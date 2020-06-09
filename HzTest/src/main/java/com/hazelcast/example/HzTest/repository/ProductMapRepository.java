package com.hazelcast.example.HzTest.repository;

import com.hazelcast.example.HzTest.entity.ProductMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapRepository extends CrudRepository<ProductMap, Integer> {

    @Query("select prod.id from ProductMap prod")
    Iterable<Integer> findAllProdMapKeys();

    List<ProductMap> findByProductType(Integer productType);
}
