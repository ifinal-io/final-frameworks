package org.finalframework.monitor.builder;

import org.finalframework.core.Assert;
import org.finalframework.monitor.annotation.MonitorAlert;
import org.finalframework.monitor.operation.AlertOperation;
import org.finalframework.spring.aop.OperationAnnotationBuilder;
import org.finalframework.spring.aop.annotation.OperationAttribute;

import java.lang.reflect.Method;

/**
 * @author likly
 * @version 1.0
 * @date 2019-07-10 16:56
 * @since 1.0
 */
public class AlertOperationAnnotationBuilder implements OperationAnnotationBuilder<MonitorAlert, AlertOperation> {

    public AlertOperation build(Method method, MonitorAlert ann) {
        final String name = Assert.isBlank(ann.name()) ? method.getDeclaringClass().getSimpleName() + "#" + method.getName() : ann.name();
        final AlertOperation.Builder builder = AlertOperation.builder()
                .name(name)
                .key(ann.key())
                .message(ann.message())
                .operator(ann.operator())
                .target(ann.target())
                .condition(ann.condition())
                .level(ann.level())
                .point(ann.point())
                .handler(ann.handler())
                .executor(ann.executor());

        final OperationAttribute[] attributes = ann.attributes();

        if (Assert.nonEmpty(attributes)) {
            for (OperationAttribute attribute : attributes) {
                builder.addAttribute(attribute.name(), attribute.value());
            }
        }

        return builder.build();
    }
}