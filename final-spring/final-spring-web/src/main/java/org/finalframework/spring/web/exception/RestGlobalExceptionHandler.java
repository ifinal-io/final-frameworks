package org.finalframework.spring.web.exception;

import org.finalframework.data.exception.IException;
import org.finalframework.spring.util.BeanUtils;
import org.finalframework.spring.web.exception.annotation.RestExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author likly
 * @version 1.0
 * @date 2018-09-29 15:49
 * @since 1.0
 */
public class RestGlobalExceptionHandler<T> implements GlobalExceptionHandler<T>, ApplicationContextAware {

    private final Logger logger;

    public RestGlobalExceptionHandler(String logger) {
        this.logger = LoggerFactory.getLogger(logger);
    }

    private ApplicationContext applicationContext;
    private final List<ExceptionHandlerBean<T>> exceptionHandlerBeans = new CopyOnWriteArrayList<>();
    private ExceptionHandler<T> unCatchExceptionHandler;

    @PostConstruct
    public void init() {
        BeanUtils.findAllBeansAnnotatedBy(applicationContext, RestExceptionHandler.class)
                .map(it -> {
                    if (!(it instanceof ExceptionHandler)) {
                        throw new IllegalStateException("the exception handler must implements ExceptionHandler!");
                    }
                    return (ExceptionHandler) it;
                }).forEach(this::registerExceptionHandler);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void registerExceptionHandler(ExceptionHandler<T> handler) {
        Order order = handler.getClass().getAnnotation(Order.class);
        this.exceptionHandlerBeans.add(new ExceptionHandlerBean<>(order == null ? 0 : order.value(), handler));
        Collections.sort(exceptionHandlerBeans);
    }

    @Override
    public void setUnCatchExceptionHandler(ExceptionHandler<T> handler) {
        this.unCatchExceptionHandler = handler;
    }

    @Override
    @ResponseBody
    public T handle(Throwable throwable) throws Throwable {

        if (throwable instanceof IException) {
            final IException e = (IException) throwable;
            logger.warn("==> exception: code={},message={},toast={}", e.getCode(), e.getMessage(), e.getToast());
        } else {
            logger.error("==> ", throwable);
        }

        for (ExceptionHandlerBean<T> item : exceptionHandlerBeans) {
            if (item.supports(throwable)) {
                return item.handle(throwable);
            }
        }

        if (unCatchExceptionHandler != null && unCatchExceptionHandler.supports(throwable)) {
            return unCatchExceptionHandler.handle(throwable);
        }

        logger.error("==> UnCatchException", throwable);
        throw throwable;
    }
}