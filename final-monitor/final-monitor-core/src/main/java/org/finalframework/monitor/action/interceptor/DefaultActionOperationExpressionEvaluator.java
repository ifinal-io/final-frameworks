package org.finalframework.monitor.action.interceptor;


import org.finalframework.monitor.action.ActionOperationExpressionEvaluator;
import org.finalframework.spring.aop.interceptor.BaseOperationExpressionEvaluator;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likly
 * @version 1.0
 * @date 2019-03-28 11:31:54
 * @since 1.0
 */
public class DefaultActionOperationExpressionEvaluator extends BaseOperationExpressionEvaluator implements ActionOperationExpressionEvaluator {
    private final Map<ExpressionKey, Expression> operatorCache = new ConcurrentHashMap<>(64);
    private final Map<ExpressionKey, Expression> targetCache = new ConcurrentHashMap<>(64);
    private final Map<ExpressionKey, Expression> attributeCache = new ConcurrentHashMap<>(64);

    @Override
    public Object operator(String operatorExpression, AnnotatedElementKey methodKey, EvaluationContext evaluationContext) {
        return getExpression(this.operatorCache, methodKey, operatorExpression).getValue(evaluationContext);
    }

    @Override
    public Object target(String targetExpression, AnnotatedElementKey methodKey, EvaluationContext evaluationContext) {
        return getExpression(this.targetCache, methodKey, targetExpression).getValue(evaluationContext);
    }

    @Override
    public Object attribute(String attributeExpression, AnnotatedElementKey methodKey, EvaluationContext evaluationContext) {
        return getExpression(this.attributeCache, methodKey, attributeExpression).getValue(evaluationContext);
    }


    @Override
    public void clear() {
        super.clear();
        operatorCache.clear();
        targetCache.clear();
        attributeCache.clear();
    }
}