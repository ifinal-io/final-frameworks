package org.ifinal.finalframework.data.query.criterion;

import org.ifinal.finalframework.util.Builder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CompareCriterionOperation extends CriterionOperation {

    static CompareCriterionOperationBuilder builder() {
        return AbsCompareCriterionOperation.builder();
    }

    @NonNull
    Object getTarget();

    @Override
    CompareOperation getOperation();

    @Nullable
    Object getValue();

    @Nullable
    Object getMin();

    @Nullable
    Object getMax();


    interface CompareCriterionOperationBuilder extends Builder<CompareCriterionOperation> {

        CompareCriterionOperationBuilder target(Object target);

        CompareCriterionOperationBuilder operation(CompareOperation operation);

        CompareCriterionOperationBuilder value(Object value);

        CompareCriterionOperationBuilder min(Object min);

        CompareCriterionOperationBuilder max(Object max);

    }


}