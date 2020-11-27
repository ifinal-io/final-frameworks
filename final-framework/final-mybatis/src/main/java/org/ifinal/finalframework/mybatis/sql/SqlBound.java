package org.ifinal.finalframework.mybatis.sql;

import lombok.Data;
import org.apache.ibatis.type.TypeHandler;
import org.ifinal.finalframework.annotation.IEntity;
import org.ifinal.finalframework.annotation.IQuery;

import java.io.Serializable;
import java.util.List;

/**
 * @author likly
 * @version 1.0.0
 * @see org.apache.ibatis.mapping.BoundSql
 * @since 1.0.0
 */
@Data
public class SqlBound implements Serializable {
    private Class<? extends IEntity<?>> entity;
    private IQuery query;
    private String script;
    private String sql;
    private List<ParameterMapping> parameterMappings;

    @Data
    public static class ParameterMapping {
        private String property;
        private String expression;
        private Class<?> javaType;
        private Class<? extends TypeHandler<?>> typeHandler;
        private Object value;
    }

}