package pers.mc.peopleana.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author michael
 * @date 2018/04/14
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();

        // Set connection parameters.
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);
        connectionFactory.setTimeout(3000);

        return connectionFactory;
    }

    @Bean
    @Primary
    public RedisTemplate<String, String> redisTemplate() {
        // Initialization and basic settings.
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setEnableTransactionSupport(true);

        // Set key serializer.
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        template.setKeySerializer(keySerializer);

        // Set key serializer.
        StringRedisSerializer valueSerializer = new StringRedisSerializer();
        template.setValueSerializer(valueSerializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean("dbCacheTemplate")
    public RedisTemplate<String, String> dcCacheTemplate() {
        // Initialization and basic settings.
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        // Set key serializer.
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        template.setKeySerializer(keySerializer);

        //Set value serializer.
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        template.setValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager cacheManager(@Qualifier("dbCacheTemplate") RedisTemplate redisTemplate) {
        RedisCacheManager manager = new RedisCacheManager(redisTemplate);
        manager.setDefaultExpiration(0);
        manager.setTransactionAware(true);
        log.info("Cache Manager is ready.");
        return manager;
    }
}
