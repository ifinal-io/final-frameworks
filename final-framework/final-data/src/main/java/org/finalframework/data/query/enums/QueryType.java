package org.finalframework.data.query.enums;

import org.finalframework.annotation.data.Transient;

/**
 * @author likly
 * @version 1.0
 * @date 2019-02-12 20:38:00
 * @since 1.0
 */
@Transient
public enum QueryType {
    SELECT("select"),
    SELECT_ONE("select"),
    SELECT_COUNT("select");
    private final String tag;

    QueryType(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}