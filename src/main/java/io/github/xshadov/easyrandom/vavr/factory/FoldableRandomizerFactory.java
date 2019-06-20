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

import io.github.xshadov.easyrandom.vavr.*;
import io.vavr.collection.*;
import lombok.Value;
import org.jeasy.random.api.Randomizer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Value
class FoldableRandomizerFactory implements CommonRandomizerFactory {
	private VavrRandomizerFactory factory;

	public Randomizer<?> of(final Class<?> fieldType, final Type genericType) {
		final Type valueType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

		if (IndexedSeq.class.equals(fieldType))
			return arrayRandomizer(valueType);

		if (Array.class.equals(fieldType))
			return arrayRandomizer(valueType);

		if (Vector.class.equals(fieldType))
			return vectorRandomizer(valueType);

		if (Tree.class.equals(fieldType))
			return treeRandomizer(valueType);

		if (Queue.class.equals(fieldType))
			return queueRandomizer(valueType);

		return streamRandomizer(valueType);
	}

	private <V> Randomizer<?> arrayRandomizer(final Type valueType) {
		return VavrArrayRandomizer.<V>builder()
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.valueRandomizer(valueRandomizer(valueType))
				.build();
	}

	private <V> Randomizer<?> vectorRandomizer(final Type valueType) {
		return VavrVectorRandomizer.<V>builder()
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.valueRandomizer(valueRandomizer(valueType))
				.build();
	}

	private <V> Randomizer<?> treeRandomizer(final Type valueType) {
		return VavrTreeRandomizer.<V>builder()
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.valueRandomizer(valueRandomizer(valueType))
				.build();
	}

	private <V> Randomizer<?> queueRandomizer(final Type valueType) {
		return VavrQueueRandomizer.<V>builder()
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.valueRandomizer(valueRandomizer(valueType))
				.build();
	}

	private <V> Randomizer<?> streamRandomizer(final Type valueType) {
		return VavrStreamRandomizer.<V>builder()
				.collectionSizeRange(factory.getParameters().getCollectionSizeRange())
				.valueRandomizer(valueRandomizer(valueType))
				.build();
	}
}
