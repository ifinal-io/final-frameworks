package org.ifinal.finalframework.annotation.sharding;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
class ShardingStrategyTest {

    @Test
    void shardingStrategy() {
        Set<InlineShardingStrategy> allMergedAnnotations2 = AnnotatedElementUtils
                .findMergedRepeatableAnnotations(ShardingEntity.class, InlineShardingStrategy.class);
        Assertions.assertFalse(allMergedAnnotations2.isEmpty());
    }

}