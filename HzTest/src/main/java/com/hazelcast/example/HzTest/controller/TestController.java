package com.hazelcast.example.HzTest.controller;

import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.example.HzTest.entity.ProductMap;
import com.hazelcast.example.HzTest.entity.ProductMapKV;
import com.hazelcast.example.HzTest.repository.ProductMapKvRepo;
import com.hazelcast.example.HzTest.repository.ProductMapRepository;
import com.hazelcast.hibernate.HazelcastCacheRegionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import java.util.*;

@RestController
public class TestController {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    ProductMapRepository productMapRepository;

    @Autowired
    ProductMapKvRepo productMapKvRepo;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping(path = "test")
    public List<ProductMap> test() {
        List<ProductMap> productMapList = new ArrayList<>();
        productMapKvRepo.findAll().iterator().forEachRemaining(productMapList::add);
        List<ProductMap> productMapList2 = productMapKvRepo.findByProductType(1);
        return productMapList;
    }

    @GetMapping(path = "prodMap/{prodType}")
    public List<ProductMap> getProdMap(Integer prodType) {
        List<ProductMap> productMapList = new ArrayList<>();
        return productMapRepository.findByProductType(prodType);
    }

    @GetMapping(path = "load")
    public boolean load() {
        IMap map = hazelcastInstance.getMap("ProductMap");
        map.loadAll(true);
        return true;
    }

    @GetMapping(path = "cache")
    public Collection<DistributedObject> getCacheDetails() {
        return hazelcastInstance.getDistributedObjects();
    }

    @GetMapping(path = "cache/l2")
    public Collection<DistributedObject> getl2CacheDetails() {
        HazelcastCacheRegionFactory cacheRegionFactory = entityManagerFactory.getCache().unwrap(HazelcastCacheRegionFactory.class);
        return cacheRegionFactory.getHazelcastInstance().getDistributedObjects();
    }

    @GetMapping(path = "cache/{cacheName}")
    public Cache getCacheDetailsByName(@PathVariable String cacheName) {
        return cacheManager.getCache(cacheName);
    }
}
