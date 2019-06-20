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

package io.github.xshadov.easyrandom.vavr.edge;

import io.github.xshadov.easyrandom.vavr.VavrGenerationTests;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import lombok.Value;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NestedGenericsRandomizeTest {
	@Value
	private static class NestedGenerics {
		private List<Set<List<Set<String>>>> nestedCollections;

		private Map<String, Map<String, Map<String, List<String>>>> nestedMaps;
	}

	@Test
	public void correctRandomization() {
		final NestedGenerics randomNested = VavrGenerationTests.random(NestedGenerics.class);

		assertThat(randomNested.getNestedCollections().size()).isBetween(2, 5);
		randomNested.getNestedCollections().forEach(value -> {
			assertThat(value.size()).isBetween(2, 5);

			value.forEach(oneNest -> {
				assertThat(oneNest.size()).isBetween(2, 5);

				oneNest.forEach(twoNest -> assertThat(twoNest.size()).isBetween(2, 5));
			});
		});

		assertThat(randomNested.getNestedMaps().keySet().size()).isBetween(2, 5);
		randomNested.getNestedMaps().values().forEach(value -> {
			assertThat(value.size()).isBetween(2, 5);

			value.values().forEach(oneNest -> {
				assertThat(oneNest.size()).isBetween(2, 5);

				oneNest.values().forEach(twoNest -> assertThat(twoNest.size()).isBetween(2, 5));
			});
		});
	}
}
