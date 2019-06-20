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
import io.github.xshadov.easyrandom.vavr.VavrMultimapRandomizer;
import io.github.xshadov.easyrandom.vavr.VavrPriorityQueueRandomizer;
import io.github.xshadov.easyrandom.vavr.VavrSetRandomizer;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeMultimap;
import io.vavr.collection.TreeSet;
import lombok.Value;
import org.jeasy.random.api.Randomizer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Value
class ComparableRandomizerFactory implements CommonRandomizerFactory {
	private VavrRandomizerFactory factory;

	public Randomizer<?> of(final Class<?> fieldType, final Type genericType) {
		if (VavrTypes.isSet(fieldType))
			return sortedSetRandomizer((ParameterizedType) genericType);

		if (VavrTypes.isMap(fieldType))
			return sortedMapRandomizer((ParameterizedType) genericType);

		if (VavrTypes.isMultimap(fieldType))
			return sortedMultimapRandomizer((ParameterizedType) genericType);

		return priorityQueueRandomizer((ParameterizedType) genericType);
	}

	private <V extends Comparable<V>> Randomizer<?> priorityQueueRandomizer(final ParameterizedType genericType) {
		final Type type = genericType.getActualTypeArguments()[0];

		if (!isComparable(type))
			throw new IllegalArgumentException(type.getTypeName() + " does not implement Comparable");

		return VavrPriorityQueueRandomizer.<V>builder()
				.valueRandomizer(valueRandomizer(type))
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.build();
	}

	private <K extends Comparable<K>, V> Randomizer<?> sortedMultimapRandomizer(final ParameterizedType genericType) {
		final Type keyType = genericType.getActualTypeArguments()[0];
		final Type valueType = genericType.getActualTypeArguments()[1];

		if (!isComparable(keyType))
			throw new IllegalArgumentException(keyType.getTypeName() + " does not implement Comparable");

		return VavrMultimapRandomizer.<K, V>builder()
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.keyRandomizer(valueRandomizer(keyType))
				.valueRandomizer(valueRandomizer(valueType))
				.collector(TreeMultimap.withSeq().collector())
				.build();
	}

	private <K extends Comparable<K>, V> Randomizer<?> sortedMapRandomizer(final ParameterizedType genericType) {
		final Type keyType = genericType.getActualTypeArguments()[0];
		final Type valueType = genericType.getActualTypeArguments()[1];

		if (!isComparable(keyType))
			throw new IllegalArgumentException(keyType.getTypeName() + " does not implement Comparable");

		return VavrMapRandomizer.<K, V>builder()
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.keyRandomizer(valueRandomizer(keyType))
				.valueRandomizer(valueRandomizer(valueType))
				.collector(TreeMap.collector())
				.build();
	}

	private <V extends Comparable<V>> Randomizer<?> sortedSetRandomizer(final ParameterizedType genericType) {
		final Type type = genericType.getActualTypeArguments()[0];

		if (!isComparable(type))
			throw new IllegalArgumentException(type.getTypeName() + " does not implement Comparable");

		return VavrSetRandomizer.<V>builder()
				.valueRandomizer(valueRandomizer(type))
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.collector(TreeSet.collector())
				.build();
	}

	private boolean isComparable(final Type type) {
		return Comparable.class.isAssignableFrom((Class<?>) type);
	}
}
