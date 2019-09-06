package org.finalframework.cache.handler;

import org.finalframework.cache.Cache;
import org.finalframework.cache.annotation.Cacheable;
import org.finalframework.cache.operation.CachePutOperation;
import org.finalframework.core.Assert;
import org.finalframework.json.Json;
import org.finalframework.spring.aop.OperationContext;
import org.finalframework.spring.aop.OperationHandler;
import org.finalframework.spring.aop.annotation.CutPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author likly
 * @version 1.0
 * @date 2018-11-23 21:15:34
 * @see Cacheable
 * @since 1.0
 */
public class CachePutOperationHandler extends AbsCacheOperationHandlerSupport implements OperationHandler<Cache, CachePutOperation> {

    @Override
    public Void before(Cache cache, OperationContext<CachePutOperation> context) {
        if (CutPoint.BEFORE == context.operation().point()) {
            invocation(cache, context, null, null);
        }
        return null;
    }

    @Override
    public void afterReturning(Cache cache, OperationContext<CachePutOperation> context, Object result) {
        if (CutPoint.AFTER_RETURNING == context.operation().point()) {
            invocation(cache, context, result, null);
        }
    }

    @Override
    public void afterThrowing(Cache cache, OperationContext<CachePutOperation> context, Throwable throwable) {
        if (CutPoint.AFTER_THROWING == context.operation().point()) {
            invocation(cache, context, null, throwable);
        }
    }

    @Override
    public void after(Cache cache, OperationContext<CachePutOperation> context, Object result, Throwable throwable) {
        if (CutPoint.AFTER == context.operation().point()) {
            invocation(cache, context, result, throwable);
        }
    }

    private void invocation(Cache cache, OperationContext<CachePutOperation> context, Object result, Throwable throwable) {
        final Logger logger = LoggerFactory.getLogger(context.target().getClass());
        final EvaluationContext evaluationContext = createEvaluationContext(context, result, throwable);
        final CachePutOperation operation = context.operation();
        if (!isConditionPassing(operation.condition(), context.metadata(), evaluationContext)) {
            return;
        }

        final Object key = generateKey(operation.key(), operation.delimiter(), context.metadata(), evaluationContext);
        final Object field = generateField(operation.field(), operation.delimiter(), context.metadata(), evaluationContext);
        Object cacheValue = result;

        if (Assert.nonBlank(operation.value())) {
            cacheValue = generateValue(operation.value(), context.metadata(), evaluationContext);
        }

        Long ttl;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        Object expired = generateExpire(operation.expire(), context.metadata(), evaluationContext);

        if (expired != null) {
            if (expired instanceof Date) {
                ttl = ((Date) expired).getTime() - System.currentTimeMillis();
            } else {
                throw new IllegalArgumentException("unSupport expire type: " + expired.getClass());
            }
        } else {
            ttl = operation.ttl();
            timeUnit = operation.timeUnit();
        }

        logger.info("==> cache set: key={},field={},ttl={},timeunit={}", key, field, ttl, timeUnit);
        logger.info("==> cache value: {}", Json.toJson(cacheValue));
        cache.set(key, field, cacheValue, ttl, timeUnit, context.view());
    }
}