package org.ifinal.finalframework.annotation.monitor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.ifinal.finalframework.origin.IEnum;
import org.ifinal.finalframework.origin.lang.Transient;
import org.springframework.lang.NonNull;

/**
 * 监控级别
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Transient
public enum MonitorLevel implements IEnum<Integer> {
    TRACE(1, "TRACE"),
    DEBUG(2, "DEBUG"),
    INFO(3, "INFO"),
    WARN(4, "WARN"),
    ERROR(5, "ERROR");

    private static final Map<Integer, MonitorLevel> cache = Arrays.stream(MonitorLevel.values())
        .collect(Collectors.toMap(MonitorLevel::getCode, Function.identity()));

    private final Integer code;

    private final String desc;

    MonitorLevel(final Integer code, final String desc) {

        this.code = code;
        this.desc = desc;
    }

    @JsonCreator
    public static MonitorLevel valueOf(final Integer code) {

        return cache.get(code);
    }

    @Override
    @NonNull
    @JsonValue
    public Integer getCode() {
        return code;
    }

    @Override
    @NonNull
    public String getDesc() {

        return desc;
    }
}