package org.ifinal.finalframework.annotation.data;

import org.ifinal.finalframework.origin.IUser;
import org.ifinal.finalframework.origin.lang.Transient;

/**
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Transient
public class AbsUser implements IUser<Long> {

    @PrimaryKey
    private Long id;

    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {

        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {

        this.name = name;
    }

}
