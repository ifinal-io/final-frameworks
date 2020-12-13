package org.ifinal.finalframework.annotation.sharding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;

/**
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ShardingTable {

    /**
     * return the logic table name
     *
     * @return the logic table name
     * @see ShardingTableRuleConfiguration#logicTable
     */
    String[] logicTables() default "";

    /**
     * return table actual data nodes
     *
     * @return table actual data nodes
     * @see ShardingTableRuleConfiguration#actualDataNodes
     */
    String[] actualDataNodes();

    boolean binding() default false;

    boolean broadcast() default false;

}