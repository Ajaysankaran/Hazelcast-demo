package com.hazelcast.example.HzTest.repository;

import com.hazelcast.example.HzTest.entity.ProductMap;
import com.hazelcast.example.HzTest.entity.ProductMapKV;
import org.springframework.data.hazelcast.repository.HazelcastRepository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapKvRepo extends HazelcastRepository<ProductMap, Integer> {

    List<ProductMap> findByProductType(Integer productType);
}
