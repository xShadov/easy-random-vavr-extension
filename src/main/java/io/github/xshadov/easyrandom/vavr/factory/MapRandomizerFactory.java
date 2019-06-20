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

import io.github.xshadov.easyrandom.vavr.VavrMapRandomizer;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.LinkedHashMap;
import io.vavr.collection.Map;
import lombok.Value;
import org.jeasy.random.api.Randomizer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collector;

@Value
class MapRandomizerFactory implements CommonRandomizerFactory {
	private VavrRandomizerFactory factory;

	public Randomizer<?> of(final Class<?> fieldType, final Type genericType) {
		if (LinkedHashMap.class.equals(fieldType))
			return mapRandomizer((ParameterizedType) genericType, LinkedHashMap.collector());

		return mapRandomizer((ParameterizedType) genericType, HashMap.collector());
	}

	private <K, V> Randomizer<?> mapRandomizer(final ParameterizedType genericType, final Collector<Tuple2<K, V>, ArrayList<Tuple2<K, V>>, ? extends Map<K, V>> collector) {
		final Type keyType = genericType.getActualTypeArguments()[0];
		final Type valueType = genericType.getActualTypeArguments()[1];

		return VavrMapRandomizer.<K, V>builder()
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.keyRandomizer(valueRandomizer(keyType))
				.valueRandomizer(valueRandomizer(valueType))
				.collector(collector)
				.build();
	}
}
