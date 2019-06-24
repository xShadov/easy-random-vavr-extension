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

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import lombok.Builder;
import lombok.Getter;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;
import org.jeasy.random.randomizers.AbstractRandomizer;

import java.util.stream.Collector;

@Getter
@Builder
class VavrSetRandomizer<T> extends AbstractRandomizer<Set<T>> implements VavrCollectionRandomizer<T, Set<T>> {
	private Randomizer<? extends T> valueRandomizer;
	private EasyRandomParameters.Range<Integer> collectionSizeRange;
	@Builder.Default
	private Collector<T, ?, ? extends Set<T>> collector = HashSet.collector();

	@Override
	public Set<T> getRandomValue() {
		return getDistinctCollection(collector);
	}
}
