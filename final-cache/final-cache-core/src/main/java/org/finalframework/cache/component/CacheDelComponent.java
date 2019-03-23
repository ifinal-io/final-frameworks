package org.finalframework.cache.component;


import org.finalframework.cache.annotation.CacheDel;
import org.finalframework.cache.builder.CacheDelAnnotationBuilder;
import org.finalframework.cache.handler.CacheDelInvocationHandler;
import org.finalframework.cache.invocation.CacheDelInvocation;
import org.finalframework.cache.operation.CacheDelOperation;

/**
 * @author likly
 * @version 1.0
 * @date 2019-03-23 00:18:41
 * @since 1.0
 */
public class CacheDelComponent extends AbsCacheComponent<CacheDel, CacheDelOperation,
        CacheDelAnnotationBuilder, CacheDelInvocation, CacheDelInvocationHandler> {

    public CacheDelComponent() {
        super(CacheDel.class, new CacheDelAnnotationBuilder(), new CacheDelInvocation(), new CacheDelInvocationHandler());
    }
}