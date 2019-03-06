package com.ilikly.finalframework.data.query.criterion.operation;

import com.ilikly.finalframework.data.query.QProperty;
import com.ilikly.finalframework.data.query.criterion.CriterionOperator;
import com.ilikly.finalframework.data.query.criterion.CriterionOperators;
import com.ilikly.finalframework.data.query.criterion.SingleCriterionOperation;

import java.util.Date;

/**
 * @author likly
 * @version 1.0
 * @date 2019-01-18 13:52:04
 * @since 1.0
 */
public class GreaterEqualThanCriterionOperation<T> extends AbsCriterionOperation<T> implements SingleCriterionOperation<T> {
    public static final GreaterEqualThanCriterionOperation INSTANCE = new GreaterEqualThanCriterionOperation();

    @Override
    public CriterionOperator operator() {
        return CriterionOperators.GREATER_EQUAL_THAN;
    }

    @Override
    public String format(QProperty property, CriterionOperator operator, T value) {
        final String column = getPropertyColumn(property);
        if (value instanceof String) {
            return String.format("%s >= '%s'", column, value);
        } else if (value instanceof Date) {
            return String.format("%s >= '%s'", column, format((Date) value));
        } else {
            return String.format("%s >= %s", column, value.toString());
        }
    }
}