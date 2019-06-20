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
import io.vavr.collection.PriorityQueue;
import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedMultimap;
import io.vavr.collection.SortedSet;
import lombok.Value;
import org.jeasy.random.ObjectCreationException;
import org.junit.Test;

public class NotComparableValueTypeTest {
	private static class NonComparable {

	}

	@Value
	private static class WithSet {
		private SortedSet<NonComparable> value;
	}

	@Value
	private static class WithMap {
		private SortedMap<NonComparable, String> value;
	}

	@Value
	private static class WithMultimap {
		private SortedMultimap<NonComparable, String> value;
	}

	@Value
	private static class WithPriorityQueue {
		private PriorityQueue<NonComparable> value;
	}

	@Test(expected = ObjectCreationException.class)
	public void setOfNonComparables() {
		VavrGenerationTests.random(WithSet.class);
	}

	@Test(expected = ObjectCreationException.class)
	public void mapOfNonComparables() {
		VavrGenerationTests.random(WithMap.class);
	}

	@Test(expected = ObjectCreationException.class)
	public void multimapOfNonComparables() {
		VavrGenerationTests.random(WithMultimap.class);
	}

	@Test(expected = ObjectCreationException.class)
	public void priorityQueueOfNonComparables() {
		VavrGenerationTests.random(WithPriorityQueue.class);
	}
}
