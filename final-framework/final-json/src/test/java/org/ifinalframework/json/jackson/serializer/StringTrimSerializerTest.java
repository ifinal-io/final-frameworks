/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ifinalframework.json.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * StringTrimSerializerTest.
 *
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
class StringTrimSerializerTest {

    @Mock
    private JsonGenerator generator;

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = " haha ")
    void serialize(String value) {

        logger.info("value={}", value);

        assertDoesNotThrow(() -> {

            StringTrimSerializer serializer = new StringTrimSerializer();

            assertEquals(String.class, serializer.handledType());

            serializer.serialize(value, generator, null);

            Assumptions.assumingThat(Objects.isNull(value), () -> {
                verify(generator, times(1)).writeNull();
            });

            Assumptions.assumingThat(Objects.nonNull(value), () -> {
                ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
                verify(generator).writeString(argumentCaptor.capture());
                assertEquals(value.trim(), argumentCaptor.getValue());
            });

        });

    }

}