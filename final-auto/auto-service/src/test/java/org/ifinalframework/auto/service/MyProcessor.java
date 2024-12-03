package org.ifinalframework.auto.service;

import org.ifinalframework.auto.service.annotation.AutoProcessor;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * MyProcessor.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@AutoProcessor
public class MyProcessor extends AbstractProcessor {

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        return false;
    }

}
