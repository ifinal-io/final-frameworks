package org.finalframework.data.mapping;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.ibatis.type.TypeHandler;
import org.finalframework.annotation.data.*;
import org.finalframework.data.serializer.PropertyJsonSerializer;
import org.springframework.core.Ordered;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;


/**
 * @author likly
 * @version 1.0
 * @date 2018-10-17 10:52
 * @since 1.0
 */
@JsonSerialize(using = PropertyJsonSerializer.class)
public interface Property extends PersistentProperty<Property>, Ordered {


    /**
     * return {@code true} if the {@linkplain #getType()} is a {@linkplain Enum enum}, otherwise {@code true}.
     *
     * @return {@code true} if the {@linkplain #getType()} is a {@linkplain Enum enum}, otherwise {@code true}.
     */
    default boolean isEnum() {
        return getType().isEnum();
    }

    /**
     * return the column name of property mapped
     *
     * @return column name
     * @see Column#name()
     * @see Column#value()
     */
    String getColumn();

    /**
     * return the column writer
     *
     * @return the column writer
     * @see Column#writer()
     */
    String getWriter();

    /**
     * return the column reader
     *
     * @return column reader
     * @see Column#reader()
     */
    String getReader();

    /**
     * return {@code true} if this property annotated by {@link Default}.
     *
     * @return true if don't have {@link Transient} annotation and have {@link Default} annotation.
     * @see Default
     */
    boolean isDefault();

    /**
     * return {@code true} if this property annotated by {@link Final}.
     *
     * @return {@code true} if the {@link Property} annotated by {@link Final} annotation.
     * @see Final
     */
    boolean isFinal();

    /**
     * return {@code true} if this property annotated by {@link Reference}.
     *
     * @return {@code true} if this property annotated by {@link Reference}.
     * @see #isAssociation()
     */
    boolean isReference();

    /**
     * return {@code true} if this property is annotated by {@link Virtual}.
     *
     * @return {@code true} if the {@link Property} annotated by {@link Virtual} annotation.
     * @see Virtual
     */
    boolean isVirtual();

    /**
     * return {@code true} if this property is annotated by {@link Sharding}.
     *
     * @return {@code true} if this {@link Property} is annotated by {@link Sharding} annotation.
     * @see Sharding
     */
    boolean isSharding();

    /**
     * return {@code true} if this property is annotated by {@link ReadOnly}.
     *
     * @return {@code true} if this {@link Property} is annotated by {@link ReadOnly} annotation.
     * @see ReadOnly
     */
    boolean isReadOnly();


    /**
     * return {@code true} if this property is annotated by {@link WriteOnly}.
     *
     * @return {@code true} if this {@link Property} is annotated by {@link WriteOnly} annotation.
     * @see WriteOnly
     */
    boolean isWriteOnly();

    /**
     * return {@code true} if this property is a sql keyword.
     *
     * @return {@code true} if this property is a sql keyword.
     * @see Keyword
     */
    boolean isKeyword();

    default boolean isWriteable() {
        return !isTransient() && !isVirtual() && !isReadOnly() && !isDefault();
    }

    default boolean isModifiable() {
        return !isTransient() && !isVirtual() && !isReadOnly() && !isFinal();
    }

    ReferenceMode getReferenceMode();

    Set<String> getReferenceProperties();

    String getReferenceColumn(Property property);

    /**
     * return java type of this property.
     * <ul>
     *     <li>return {@link Map} if the property is {@linkplain #isMap() map};</li>
     *     <li>return {@link #getComponentType()} if this property is {@linkplain #isCollectionLike() collection};</li>
     *     <li>otherwise return {@link #getType()}.</li>
     * </ul>
     *
     * @return the property java type
     */
    @Nullable
    default Class<?> getJavaType() {
        if (isMap()) {
            return Map.class;
        } else if (isCollectionLike()) {
            return getComponentType();
        } else {
            return getType();
        }
    }

    @Nullable
    @SuppressWarnings("rawtypes")
    Class<? extends TypeHandler> getTypeHandler();

    default Object get(@NotNull Object target) {
        try {
            return getRequiredGetter().invoke(target);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    default void set(@NotNull Object target, Object value) {
        try {
            getRequiredSetter().invoke(target, value);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}