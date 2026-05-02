package io.allitov.buber.trip.configuration.cache;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.DefaultTyping;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

/**
 * Конфигурация кэша на основе Redis.
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
@RequiredArgsConstructor
class RedisConfiguration {

    private final CacheProperties cacheProperties;

    @Bean
    RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory, JsonMapper jsonMapper) {
        JsonMapper redisMapper = jsonMapper
                .rebuild()
                .activateDefaultTypingAsProperty(
                        BasicPolymorphicTypeValidator.builder()
                                .allowIfSubType("io.allitov.buber.trip.core.model.")
                                .build(),
                        DefaultTyping.NON_FINAL_AND_RECORDS,
                        "@class")
                .build();

        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(cacheProperties.defaultTtl())
                .disableCachingNullValues()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJacksonJsonRedisSerializer(redisMapper)));

        Map<String, RedisCacheConfiguration> cacheConfig = new HashMap<>();
        cacheProperties.config().forEach((cacheName, ttl) -> cacheConfig.put(cacheName, defaultConfig.entryTtl(ttl)));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfig)
                .build();
    }
}
