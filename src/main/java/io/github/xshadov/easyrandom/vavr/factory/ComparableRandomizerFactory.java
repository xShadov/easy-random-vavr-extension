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

import io.github.xshadov.easyrandom.vavr.exception.GenericParameterNotComparableException;
import io.github.xshadov.easyrandom.vavr.randomizers.VavrRandomizers;
import lombok.Value;
import org.jeasy.random.api.Randomizer;
import org.jeasy.random.util.ReflectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Value
class ComparableRandomizerFactory implements CommonRandomizerFactory {
	private VavrRandomizerFactory factory;

	public Randomizer<?> of(final Class<?> fieldType, final Type genericType) {
		if (VavrTypes.isSet(fieldType)) {
			final Type type = ((ParameterizedType) genericType).getActualTypeArguments()[0];

			if (!implementsComparable(type))
				throw new GenericParameterNotComparableException(type);

			return VavrRandomizers.sortedSet(this.<Comparable>valueRandomizer(type), factory.getParameters().getCollectionSizeRange());
		}

		if (VavrTypes.isMap(fieldType)) {
			final Type keyType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
			final Type valueType = ((ParameterizedType) genericType).getActualTypeArguments()[1];

			if (!implementsComparable(keyType))
				throw new GenericParameterNotComparableException(keyType);

			return VavrRandomizers.sortedMap(this.<Comparable>valueRandomizer(keyType), valueRandomizer(valueType), factory.getParameters().getCollectionSizeRange());
		}

		if (VavrTypes.isMultimap(fieldType)) {
			final Type keyType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
			final Type valueType = ((ParameterizedType) genericType).getActualTypeArguments()[1];

			if (!implementsComparable(keyType))
				throw new GenericParameterNotComparableException(keyType);

			return VavrRandomizers.sortedMultimap(this.<Comparable>valueRandomizer(keyType), valueRandomizer(valueType), factory.getParameters().getCollectionSizeRange());
		}

		final Type type = ((ParameterizedType) genericType).getActualTypeArguments()[0];

		if (!implementsComparable(type))
			throw new GenericParameterNotComparableException(type);

		return VavrRandomizers.priorityQueue(this.<Comparable>valueRandomizer(type), factory.getParameters().getCollectionSizeRange());
	}

	private boolean implementsComparable(final Type type) {
		if (ReflectionUtils.isParameterizedType(type))
			return false;

		return Comparable.class.isAssignableFrom((Class<?>) type);
	}
}
