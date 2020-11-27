package org.ifinal.finalframework.annotation;

import org.ifinal.finalframework.annotation.data.Creator;
import org.ifinal.finalframework.annotation.data.LastModifier;
import org.ifinal.finalframework.annotation.data.Transient;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * The interface of {@code user} which impl the interface of {@link IEntity} in the system.
 *
 * @author likly
 * @version 1.0.0
 * @see Creator
 * @see LastModifier
 * @since 1.0.0
 */
@Transient
public interface IUser<ID extends Serializable> extends IEntity<ID> {
    /**
     * return user name.
     *
     * @return user name.
     */
    @Nullable
    String getName();

    /**
     * set user name.
     *
     * @param name user name.
     */
    void setName(@Nullable String name);
}