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

public class NestedGenericsRandomizeTest extends VavrGenerationTests {
	@Value
	private static class NestedGenerics {
		private List<Set<List<Set<String>>>> nestedCollections;

		private Map<String, Map<String, Map<String, List<String>>>> nestedMaps;
	}

	@Test
	public void correctRandomization() {
		final NestedGenerics randomNested = random(NestedGenerics.class);

		assertSizeInRange(randomNested.getNestedCollections());
		randomNested.getNestedCollections().forEach(value -> {
			assertSizeInRange(value);

			value.forEach(oneNest -> {
				assertSizeInRange(oneNest);

				oneNest.forEach(this::assertSizeInRange);
			});
		});

		assertSizeInRange(randomNested.getNestedMaps().keySet());
		randomNested.getNestedMaps().values().forEach(value -> {
			assertSizeInRange(value);

			value.values().forEach(oneNest -> {
				assertSizeInRange(oneNest);

				oneNest.values().forEach(this::assertSizeInRange);
			});
		});
	}
}
