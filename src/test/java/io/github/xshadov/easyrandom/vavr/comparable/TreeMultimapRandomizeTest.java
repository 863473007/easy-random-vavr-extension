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
import io.vavr.collection.TreeMultimap;
import lombok.Value;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TreeMultimapRandomizeTest {
	@Value
	private static class Person {
		private TreeMultimap<String, Integer> integerMultimap;
		private TreeMultimap<String, String> stringMultimap;
	}

	@Test
	public void correctRandomization() {
		final Person randomPerson = VavrGenerationTests.random(Person.class);

		assertThat(randomPerson.getIntegerMultimap().size()).isBetween(2, 5);

		assertThat(randomPerson.getStringMultimap().size()).isBetween(2, 5);
	}
}
