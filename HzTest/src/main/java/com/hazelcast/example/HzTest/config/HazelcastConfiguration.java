package com.hazelcast.example.HzTest.config;


import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

import java.io.IOException;

@Configuration
@EnableHazelcastRepositories(basePackages = "com.hazelcast.example.HzTest.repository")
public class HazelcastConfiguration {

    @Bean
    public Config hazelcastConfig() {
        Config config = new Config().setInstanceName("hazelcast-instance").addMapConfig(
                new MapConfig().setName("ProductMap")
                        .setMapStoreConfig(
                                new MapStoreConfig().setEnabled(true).setInitialLoadMode(MapStoreConfig.InitialLoadMode.EAGER)
                                        .setClassName("com.hazelcast.example.HzTest.config.ProductMapLoader")
                        ).setStatisticsEnabled(true));
        return config;
    }

//    @Bean
//    public CacheManager cacheManager() {
//        return new HazelcastCacheManager(hazelcastInstance());
//    }
//
    @Bean
    public HazelcastInstance hazelcastInstance() {
        //return HazelcastClient.newHazelcastClient();
         return Hazelcast.newHazelcastInstance(hazelcastConfig());
    }
}
