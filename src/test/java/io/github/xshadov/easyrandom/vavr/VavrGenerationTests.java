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

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class VavrGenerationTests {
	public static <T> T random(Class<T> clazz) {
		final EasyRandomParameters parameters = new EasyRandomParameters()
				.collectionSizeRange(2, 5)
				.stringLengthRange(2, 3);

		return generator(parameters).nextObject(clazz);
	}

	public static <T> T random(Class<T> clazz, EasyRandomParameters easyRandomParameters) {
		return generator(easyRandomParameters).nextObject(clazz);
	}

	private static EasyRandom generator(EasyRandomParameters easyRandomParameters) {
		final VavrRandomizerRegistry vavrRandomizerRegistry = new VavrRandomizerRegistry();

		final EasyRandom easyRandom = new EasyRandom(easyRandomParameters.randomizerRegistry(vavrRandomizerRegistry));

		vavrRandomizerRegistry.setEasyRandom(easyRandom);

		return easyRandom;
	}
}
