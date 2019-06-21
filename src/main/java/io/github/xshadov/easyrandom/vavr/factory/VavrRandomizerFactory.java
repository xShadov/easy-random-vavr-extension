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

import lombok.Builder;
import lombok.Getter;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;

import java.lang.reflect.Type;

@Getter
@Builder
public class VavrRandomizerFactory {
	private EasyRandom easyRandom;
	private EasyRandomParameters parameters;

	public Randomizer<?> fromTypes(final Class<?> fieldType, final Type genericType) {
		if (VavrTypes.isComparable(fieldType))
			return comparableRandomizer().of(fieldType, genericType);

		if (VavrTypes.isList(fieldType))
			return listRandomizer().of(genericType);

		if (VavrTypes.isSet(fieldType))
			return setRandomizer().of(fieldType, genericType);

		if (VavrTypes.isMap(fieldType))
			return mapRandomizer().of(fieldType, genericType);

		if (VavrTypes.isCollection(fieldType))
			return collectionRandomizer().of(fieldType, genericType);

		if (VavrTypes.isMultimap(fieldType))
			return multimapRandomizer().of(fieldType, genericType);

		return null;
	}

	private MultimapRandomizerFactory multimapRandomizer() {
		return new MultimapRandomizerFactory(this);
	}

	private CollectionRandomizerFactory collectionRandomizer() {
		return new CollectionRandomizerFactory(this);
	}

	private ComparableRandomizerFactory comparableRandomizer() {
		return new ComparableRandomizerFactory(this);
	}

	private ListRandomizerFactory listRandomizer() {
		return new ListRandomizerFactory(this);
	}

	private SetRandomizerFactory setRandomizer() {
		return new SetRandomizerFactory(this);
	}

	private MapRandomizerFactory mapRandomizer() {
		return new MapRandomizerFactory(this);
	}
}
