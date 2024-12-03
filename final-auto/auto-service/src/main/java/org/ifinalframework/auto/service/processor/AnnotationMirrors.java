package org.ifinalframework.auto.service.processor;

import java.util.Map;
import java.util.stream.Collectors;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;

/**
 * AnnotationMirrors.
 *
 * @author likly
 * @version 1.0.0
 * @see AnnotationMirror
 * @since 1.0.0
 */
public final class AnnotationMirrors {

    private AnnotationMirrors() {

    }

    /**
     * return the annotation values.
     *
     * @param mirror annotation mirror.
     * @return annotation mirror values.
     */
    public static Map<String, AnnotationValue> getAnnotationValues(final AnnotationMirror mirror) {

        return mirror.getElementValues()
            .entrySet()
            .stream()
            .collect(Collectors.toMap(entry -> entry.getKey().getSimpleName().toString(), Map.Entry::getValue));
    }

}

