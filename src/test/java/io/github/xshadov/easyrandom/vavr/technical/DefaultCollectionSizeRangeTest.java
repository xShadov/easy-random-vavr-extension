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

package io.github.xshadov.easyrandom.vavr.technical;

import io.github.xshadov.easyrandom.vavr.VavrRandomizerRegistry;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import lombok.Value;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultCollectionSizeRangeTest {
	@Value
	private static class Person {
		private List<Integer> list;
		private Set<Integer> set;
		private Map<String, List<String>> map;
	}

	@Test
	public void withoutDefaultCollectionSizeRange() {
		final VavrRandomizerRegistry vavrRandomizerRegistry = new VavrRandomizerRegistry();

		final EasyRandomParameters params = new EasyRandomParameters().randomizerRegistry(vavrRandomizerRegistry);
		final EasyRandom easyRandom = new EasyRandom(params);

		vavrRandomizerRegistry.setEasyRandom(easyRandom);

		final Person person = easyRandom.nextObject(Person.class);

		final EasyRandomParameters.Range<Integer> collectionSizeRange = params.getCollectionSizeRange();

		assertThat(person.getList().size()).isBetween(collectionSizeRange.getMin(), collectionSizeRange.getMax());
		assertThat(person.getSet().size()).isBetween(collectionSizeRange.getMin(), collectionSizeRange.getMax());
		assertThat(person.getMap().keySet().size()).isBetween(collectionSizeRange.getMin(), collectionSizeRange.getMax());

		person.getMap().values().forEach(value -> assertThat(value.size()).isBetween(collectionSizeRange.getMin(), collectionSizeRange.getMax()));
	}
}
