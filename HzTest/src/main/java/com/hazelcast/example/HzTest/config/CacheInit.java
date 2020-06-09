package com.hazelcast.example.HzTest.config;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.example.HzTest.entity.ProductMap;
import com.hazelcast.example.HzTest.repository.ProductMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CacheInit implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private ProductMapRepository productMapRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("Cache Init *******************************************");
        //IMap map = hazelcastInstance.getMap("ProductMap");
        List<ProductMap> productMapList = productMapRepository.findByProductType(1);
    }
}
