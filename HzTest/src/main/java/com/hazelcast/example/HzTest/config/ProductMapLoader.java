package com.hazelcast.example.HzTest.config;

import com.hazelcast.example.HzTest.entity.ProductMap;
import com.hazelcast.example.HzTest.repository.ProductMapRepository;
import com.hazelcast.core.MapLoader;
import com.hazelcast.spring.context.SpringAware;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Log4j2
@Component
@SpringAware
public class ProductMapLoader implements MapLoader<Integer, ProductMap>, ApplicationContextAware {

    private static ProductMapRepository productMapRepository;

    @Override
    public synchronized ProductMap load(Integer integer) {
        System.out.println("Load::" + integer);
        return productMapRepository.findById(integer).get();
    }

    @Override
    public synchronized Map<Integer, ProductMap> loadAll(Collection<Integer> collection) {
        Map<Integer, ProductMap> result = new HashMap<>();
        for (Integer key : collection) {
            ProductMap productMap = this.load(key);
            if (productMap != null) {
                result.put(key, productMap);
            }
        }
        return result;
    }

    @Override
    public synchronized Iterable<Integer> loadAllKeys() {
        System.out.println("load all keys " + productMapRepository);
        return productMapRepository.findAllProdMapKeys();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        productMapRepository = applicationContext.getBean(ProductMapRepository.class);
    }
}
