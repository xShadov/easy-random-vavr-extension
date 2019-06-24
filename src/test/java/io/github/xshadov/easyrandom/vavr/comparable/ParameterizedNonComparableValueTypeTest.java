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
import io.github.xshadov.easyrandom.vavr.exception.GenericParameterNotComparableException;
import io.vavr.collection.List;
import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedMultimap;
import io.vavr.collection.SortedSet;
import lombok.Value;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.isA;

@SuppressWarnings("deprecation")
public class ParameterizedNonComparableValueTypeTest extends VavrGenerationTests {
	@Rule
	public org.junit.rules.ExpectedException expectedException = org.junit.rules.ExpectedException.none();

	@Value
	private static class WithParameterizedList {
		private SortedSet<List<String>> value;
	}

	@Value
	private static class WithMultiplyParametrizedList {
		private SortedSet<List<List<List<List<String>>>>> value;
	}

	@Value
	private static class WithNonComparableParameterizedMapKey {
		private SortedMap<List<String>, String> value;
	}

	@Value
	private static class WithNonComparableParameterizedMultimapKey {
		private SortedMultimap<List<String>, String> value;
	}

	@Test
	public void sortedSetWithNonComparableParameterizedGenericParameter() {
		expectedException.expectCause(isA(GenericParameterNotComparableException.class));

		random(WithParameterizedList.class);
	}

	@Test
	public void sortedSetWithMultiplyNestedNonComparableParameterizedGenericParameter() {
		expectedException.expectCause(isA(GenericParameterNotComparableException.class));

		random(WithMultiplyParametrizedList.class);
	}

	@Test
	public void sortedMapWithNonComparableParameterizedKey() {
		expectedException.expectCause(isA(GenericParameterNotComparableException.class));

		random(WithNonComparableParameterizedMapKey.class);
	}

	@Test
	public void sortedMultimapWithNonComparableParameterizedKey() {
		expectedException.expectCause(isA(GenericParameterNotComparableException.class));

		random(WithNonComparableParameterizedMultimapKey.class);
	}
}
