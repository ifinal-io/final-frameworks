

package org.finalframework.spring.aop;

import org.finalframework.core.Asserts;
import org.springframework.core.MethodClassKey;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author likly
 * @version 1.0
 * @date 2019-03-26 10:00:25
 * @since 1.0
 */
public interface OperationSource {

    default boolean matches(Method method, @Nullable Class<?> targetClass) {
        return Asserts.nonEmpty(getOperations(method, targetClass));
    }

    @Nullable
    Collection<? extends Operation> getOperations(Method method, @Nullable Class<?> targetClass);

    @NonNull
    default Object getCacheKey(Method method, @Nullable Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }

}