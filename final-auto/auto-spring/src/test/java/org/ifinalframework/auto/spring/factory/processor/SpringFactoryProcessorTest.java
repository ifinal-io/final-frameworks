package org.ifinalframework.auto.spring.factory.processor;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.context.ApplicationListener;
import org.springframework.core.io.support.SpringFactoriesLoader;

import org.junit.jupiter.api.Test;

/**
 * SpringFactoryProcessorTest.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
class SpringFactoryProcessorTest {

    @Test
    void test() {
        boolean contains = SpringFactoriesLoader.loadFactoryNames(ApplicationListener.class, null)
            .contains(MyListener.class.getName());
        assertTrue(contains);
    }

}
