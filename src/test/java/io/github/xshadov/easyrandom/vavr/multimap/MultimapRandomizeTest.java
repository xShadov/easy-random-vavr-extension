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

package io.github.xshadov.easyrandom.vavr.multimap;

import io.github.xshadov.easyrandom.vavr.VavrGenerationTests;
import io.vavr.collection.Multimap;
import lombok.Value;
import org.junit.Test;

public class MultimapRandomizeTest extends VavrGenerationTests {
	@Value
	private static class Person {
		private Multimap<String, Integer> integerMultimap;
		private Multimap<String, String> stringMultimap;
	}

	@Test
	public void correctRandomization() {
		final Person randomPerson = random(Person.class);

		assertSizeInRange(randomPerson.getIntegerMultimap());

		assertSizeInRange(randomPerson.getStringMultimap());
	}
}
