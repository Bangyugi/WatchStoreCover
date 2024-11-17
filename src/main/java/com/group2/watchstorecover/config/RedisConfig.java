package com.group2.watchstorecover.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.port}")
    private Integer redisPort;
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        // Gắn kết nối Redis từ JedisConnectionFactory
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        // Định nghĩa cách Redis sẽ serialize các key
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // Định nghĩa cách Redis sẽ serialize các key trong Hash
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // Định nghĩa cách Redis sẽ serialize các value
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // Định nghĩa cách Redis sẽ serialize các value trong Hash
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
