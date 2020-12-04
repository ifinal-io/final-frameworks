package org.ifinal.finalframework.aop;

import org.ifinal.finalframework.context.expression.MethodMetadata;

import java.util.Map;

/**
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
public interface InvocationContext {

    MethodMetadata metadata();

    Object target();

    Object[] args();

    Class<?> view();

    Map<String, Object> attributes();

    void addAttribute(String name, Object value);

    <T> T getAttribute(String name);
}