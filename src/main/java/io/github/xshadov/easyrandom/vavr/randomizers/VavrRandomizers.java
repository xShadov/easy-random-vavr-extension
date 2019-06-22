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

package io.github.xshadov.easyrandom.vavr.randomizers;

import io.vavr.collection.*;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;

import java.util.function.Supplier;

public class VavrRandomizers {
	public static <V> Randomizer<?> array(final Supplier<V> valueRandomizer, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrArrayRandomizer.<V>builder()
				.collectionSizeRange(collectionSizeRange)
				.valueRandomizer(valueRandomizer)
				.build();
	}

	public static <V> Randomizer<?> vector(final Supplier<V> valueRandomizer, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrVectorRandomizer.<V>builder()
				.collectionSizeRange(collectionSizeRange)
				.valueRandomizer(valueRandomizer)
				.build();
	}

	public static <V> Randomizer<?> tree(final Supplier<V> valueRandomizer, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrTreeRandomizer.<V>builder()
				.collectionSizeRange(collectionSizeRange)
				.valueRandomizer(valueRandomizer)
				.build();
	}

	public static <V> Randomizer<?> queue(final Supplier<V> valueRandomizer, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrQueueRandomizer.<V>builder()
				.collectionSizeRange(collectionSizeRange)
				.valueRandomizer(valueRandomizer)
				.build();
	}

	public static <V> Randomizer<?> stream(final Supplier<V> valueRandomizer, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrStreamRandomizer.<V>builder()
				.collectionSizeRange(collectionSizeRange)
				.valueRandomizer(valueRandomizer)
				.build();
	}

	public static <V extends Comparable<V>> Randomizer<?> priorityQueue(final Supplier<V> valueRandomizer, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrPriorityQueueRandomizer.<V>builder()
				.valueRandomizer(valueRandomizer)
				.collectionSizeRange(collectionSizeRange)
				.build();
	}

	public static <K extends Comparable<K>, V> Randomizer<?> sortedMultimap(final Supplier<K> keyRandomizer, final Supplier<V> valueRandomizers, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrMultimapRandomizer.<K, V>builder()
				.collectionSizeRange(collectionSizeRange)
				.keyRandomizer(keyRandomizer)
				.valueRandomizer(valueRandomizers)
				.collector(TreeMultimap.withSeq().collector())
				.build();
	}

	public static <K extends Comparable<K>, V> Randomizer<?> sortedMap(final Supplier<K> keyRandomizer, final Supplier<V> valueRandomizers, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrMapRandomizer.<K, V>builder()
				.collectionSizeRange(collectionSizeRange)
				.keyRandomizer(keyRandomizer)
				.valueRandomizer(valueRandomizers)
				.collector(TreeMap.collector())
				.build();
	}

	public static <V extends Comparable<V>> Randomizer<?> sortedSet(final Supplier<V> valueRandomizer, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrSetRandomizer.<V>builder()
				.valueRandomizer(valueRandomizer)
				.collectionSizeRange(collectionSizeRange)
				.collector(TreeSet.collector())
				.build();
	}

	public static <V> Randomizer<?> list(final Supplier<V> valueRandomizer, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrListRandomizer.<V>builder()
				.valueRandomizer(valueRandomizer)
				.collectionSizeRange(collectionSizeRange)
				.build();
	}

	public static <K, V> Randomizer<?> linkedHashMap(final Supplier<K> keyRandomizer, final Supplier<V> valueRandomizers, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrMapRandomizer.<K, V>builder()
				.collectionSizeRange(collectionSizeRange)
				.keyRandomizer(keyRandomizer)
				.valueRandomizer(valueRandomizers)
				.collector(LinkedHashMap.collector())
				.build();
	}

	public static <K, V> Randomizer<?> hashMap(final Supplier<K> keyRandomizer, final Supplier<V> valueRandomizers, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrMapRandomizer.<K, V>builder()
				.collectionSizeRange(collectionSizeRange)
				.keyRandomizer(keyRandomizer)
				.valueRandomizer(valueRandomizers)
				.collector(HashMap.collector())
				.build();
	}

	public static <K, V> Randomizer<?> hashMultimap(final Supplier<K> keyRandomizer, final Supplier<V> valueRandomizers, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrMultimapRandomizer.<K, V>builder()
				.collectionSizeRange(collectionSizeRange)
				.keyRandomizer(keyRandomizer)
				.valueRandomizer(valueRandomizers)
				.collector(HashMultimap.withSeq().collector())
				.build();
	}

	public static <K, V> Randomizer<?> linkedHashMultimap(final Supplier<K> keyRandomizer, final Supplier<V> valueRandomizers, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrMultimapRandomizer.<K, V>builder()
				.collectionSizeRange(collectionSizeRange)
				.keyRandomizer(keyRandomizer)
				.valueRandomizer(valueRandomizers)
				.collector(LinkedHashMultimap.withSeq().collector())
				.build();
	}

	public static <V> Randomizer<?> hashSet(final Supplier<V> valueRandomizer, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrSetRandomizer.<V>builder()
				.valueRandomizer(valueRandomizer)
				.collectionSizeRange(collectionSizeRange)
				.collector(HashSet.collector())
				.build();
	}

	public static <V> Randomizer<?> linkedHashSet(final Supplier<V> valueRandomizer, final EasyRandomParameters.Range<Integer> collectionSizeRange) {
		return VavrSetRandomizer.<V>builder()
				.valueRandomizer(valueRandomizer)
				.collectionSizeRange(collectionSizeRange)
				.collector(LinkedHashSet.collector())
				.build();
	}
}
