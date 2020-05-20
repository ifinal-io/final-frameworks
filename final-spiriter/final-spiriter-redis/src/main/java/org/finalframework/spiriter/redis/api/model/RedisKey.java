package org.finalframework.spiriter.redis.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.connection.DataType;

import java.util.concurrent.TimeUnit;

/**
 * @author likly
 * @version 1.0
 * @date 2020-05-18 18:38:34
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisKey {
    private Object key;
    private DataType type;
    private Long ttl;
    private TimeUnit timeUnit;
}
