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

package io.github.xshadov.easyrandom.vavr.comparable;

import io.github.xshadov.easyrandom.vavr.VavrGenerationTests;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.collection.TreeMap;
import lombok.Value;
import org.jeasy.random.EasyRandomParameters;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TreeMapRandomizeTest {
	@Value
	private static class Person {
		private TreeMap<String, Integer> simple;
		private TreeMap<String, List<String>> listMap;
		private TreeMap<String, Set<String>> setMap;
		private TreeMap<String, TreeMap<String, String>> nestedMap;
	}

	@Test
	public void correctRandomization() {
		final Person randomPerson = VavrGenerationTests.random(Person.class);

		assertThat(randomPerson.getSimple().size()).isBetween(2, 5);

		assertThat(randomPerson.getListMap().size()).isBetween(2, 5);
		randomPerson.getListMap().forEach((key, value) -> assertThat(value.size()).isBetween(2, 5));

		assertThat(randomPerson.getNestedMap().size()).isBetween(2, 5);
		randomPerson.getNestedMap().forEach((key, value) -> assertThat(value.size()).isBetween(2, 5));
	}

	@Test
	public void randomizationWithConstantRanges() {
		final EasyRandomParameters parameters = new EasyRandomParameters()
				.collectionSizeRange(2, 5)
				.randomize(String.class, () -> "constant")
				.randomize(Integer.class, () -> 123);

		final Person randomPerson = VavrGenerationTests.random(Person.class, parameters);

		assertThat(randomPerson.getSimple().size()).isEqualTo(1);

		assertThat(randomPerson.getListMap().size()).isEqualTo(1);
		randomPerson.getListMap().forEach((key, value) -> assertThat(value.size()).isBetween(2, 5));

		assertThat(randomPerson.getSetMap().size()).isEqualTo(1);
		randomPerson.getSetMap().forEach((key, value) -> assertThat(value.size()).isEqualTo(1));

		assertThat(randomPerson.getNestedMap().size()).isEqualTo(1);
		randomPerson.getNestedMap().forEach((key, value) -> assertThat(value.size()).isEqualTo(1));
	}
}
