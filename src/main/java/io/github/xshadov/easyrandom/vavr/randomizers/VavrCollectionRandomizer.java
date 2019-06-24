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

import io.vavr.collection.List;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;

import java.util.stream.Collector;

interface VavrCollectionRandomizer<T, C> {
	int ATTEMPT_THRESHOLD = 3;

	Randomizer<? extends T> getValueRandomizer();

	EasyRandomParameters.Range<Integer> getCollectionSizeRange();

	default <A> C getRandomCollection(Collector<? super T, A, ? extends C> collector) {
		final int count = VavrRandomizeUtils.randomSize(getCollectionSizeRange());

		return List.fill(count, () -> getValueRandomizer().getRandomValue())
				   .collect(collector);
	}

	default <A> C getDistinctCollection(Collector<? super T, A, ? extends C> collector) {
		final int count = VavrRandomizeUtils.randomSize(getCollectionSizeRange());

		final java.util.Set<T> javaSet = new java.util.HashSet<>();

		// attempt to fill set as close as possible to requested range
		// we can't try forever, because randomizer might not be able to produce enough distinct values
		int attempts = 0;
		while (javaSet.size() < count && attempts++ < ATTEMPT_THRESHOLD * count) {
			javaSet.add(getValueRandomizer().getRandomValue());
		}

		return javaSet.stream().collect(collector);
	}
}
