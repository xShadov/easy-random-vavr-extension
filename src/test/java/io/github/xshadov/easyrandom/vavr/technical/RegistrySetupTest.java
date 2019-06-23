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
import lombok.Value;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.isA;

@SuppressWarnings("deprecation")
public class RegistrySetupTest {
	@Rule
	public org.junit.rules.ExpectedException expectedException = org.junit.rules.ExpectedException.none();

	@Value
	private static class Person {
		private List<Integer> list;
	}

	@Test
	public void incorrectSetup() {
		expectedException.expectCause(isA(IllegalStateException.class));

		final VavrRandomizerRegistry vavrRandomizerRegistry = new VavrRandomizerRegistry();

		final EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters().randomizerRegistry(vavrRandomizerRegistry));

		easyRandom.nextObject(Person.class);
	}

	@Test
	public void setterSetup() {
		final VavrRandomizerRegistry vavrRandomizerRegistry = new VavrRandomizerRegistry();

		final EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters().randomizerRegistry(vavrRandomizerRegistry));

		vavrRandomizerRegistry.setEasyRandom(easyRandom);

		final Person result = easyRandom.nextObject(Person.class);

		assertThat(result).isNotNull();
	}

	@Test
	public void constructorSetup() {
		final EasyRandom outerEasyRandom = new EasyRandom();

		final VavrRandomizerRegistry vavrRandomizerRegistry = new VavrRandomizerRegistry(outerEasyRandom);

		final EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters().randomizerRegistry(vavrRandomizerRegistry));

		final Person result = easyRandom.nextObject(Person.class);

		assertThat(result).isNotNull();
	}
}
