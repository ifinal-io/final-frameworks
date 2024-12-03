package org.ifinalframework.auto.spring.factory.processor;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import org.ifinalframework.auto.spring.factory.annotation.SpringApplicationListener;

/**
 * MyListener.
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringApplicationListener
class MyListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(final ApplicationStartedEvent event) {

    }

}
