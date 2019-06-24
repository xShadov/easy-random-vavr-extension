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

package io.github.xshadov.easyrandom.vavr.factory;

import io.github.xshadov.easyrandom.vavr.randomizers.VavrRandomizers;
import org.jeasy.random.api.Randomizer;
import org.jeasy.random.util.ReflectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

interface CommonRandomizerFactory {
	VavrRandomizerFactory getFactory();

	default <V> Randomizer<V> valueRandomizer(final Type type) {
		final Randomizer<V> randomizer;

		if (ReflectionUtils.isParameterizedType(type)) {
			final ParameterizedType nestedParametrizedType = (ParameterizedType) type;

			randomizer = (Randomizer<V>) getFactory().fromTypes((Class<?>) nestedParametrizedType.getRawType(), nestedParametrizedType);
		} else {
			final Class<V> rawType = (Class<V>) type;

			if (VavrTypes.contains(rawType))
				randomizer = (Randomizer<V>) VavrRandomizers.empty(rawType);
			else
				randomizer = () -> getFactory().getEasyRandom().nextObject(rawType);
		}

		return randomizer;
	}
}
