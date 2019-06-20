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

package io.github.xshadov.easyrandom.vavr;

import io.github.xshadov.easyrandom.vavr.factory.VavrRandomizerFactory;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.annotation.Priority;
import org.jeasy.random.api.Randomizer;
import org.jeasy.random.api.RandomizerRegistry;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

@Priority(-3)
public class VavrRandomizerRegistry implements RandomizerRegistry {
	private VavrRandomizerFactory vavrRandomizerFactory;
	private EasyRandomParameters easyRandomParameters;
	private EasyRandom easyRandom;

	@Override
	public void init(EasyRandomParameters parameters) {
		this.easyRandomParameters = parameters;
		initializeFactory();
	}

	@Override
	public Randomizer<?> getRandomizer(final Field field) {
		final Class<?> fieldType = field.getType();

		return randomizerForType(fieldType, field.getGenericType());
	}

	private Randomizer<?> randomizerForType(final Class<?> fieldType, final Type genericType) {
		return vavrRandomizerFactory.fromTypes(fieldType, genericType);
	}

	@Override
	public Randomizer<?> getRandomizer(Class<?> type) {
		// Generic collections can be randomized only through fields, there is no way to get generic type by class
		return null;
	}

	public void setEasyRandom(EasyRandom easyRandom) {
		this.easyRandom = easyRandom;
		initializeFactory();
	}

	private void initializeFactory() {
		this.vavrRandomizerFactory = VavrRandomizerFactory
				.builder()
				.easyRandom(easyRandom)
				.parameters(easyRandomParameters)
				.build();
	}
}
