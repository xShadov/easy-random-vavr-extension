/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.xshadov.easyrandom.vavr.factory;

import io.github.xshadov.easyrandom.vavr.VavrListRandomizer;
import lombok.Value;
import org.jeasy.random.api.Randomizer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Value
class ListRandomizerFactory implements CommonRandomizerFactory {
	private VavrRandomizerFactory factory;

	public Randomizer<?> of(final Type genericType) {
		return listRandomizer((ParameterizedType) genericType);
	}

	private <V> Randomizer<?> listRandomizer(final ParameterizedType genericType) {
		final Type valueType = genericType.getActualTypeArguments()[0];

		return VavrListRandomizer.<V>builder()
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.valueRandomizer(valueRandomizer(valueType))
				.build();
	}
}
