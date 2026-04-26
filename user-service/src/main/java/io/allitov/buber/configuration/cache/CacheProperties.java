package io.allitov.buber.configuration.cache;

import java.time.Duration;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Параметры для настройки TTL кэша.
 *
 * @param defaultTtl Время TTL по умолчанию.
 * @param config     Соответствие: имя кэша -> TTL.
 */
@ConfigurationProperties(prefix = "app.cache")
public record CacheProperties(Duration defaultTtl, Map<String, Duration> config) {}
