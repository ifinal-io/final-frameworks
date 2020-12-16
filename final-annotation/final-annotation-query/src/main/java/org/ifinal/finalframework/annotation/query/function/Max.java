package org.ifinal.finalframework.annotation.query.function;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jdk.nashorn.internal.objects.annotations.Function;

/**
 * Max.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Function(value = Max.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Max {

    String[] value() default "MAX(${column})";

}