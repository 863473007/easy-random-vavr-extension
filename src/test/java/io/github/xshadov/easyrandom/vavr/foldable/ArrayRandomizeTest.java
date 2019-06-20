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

package io.github.xshadov.easyrandom.vavr.foldable;

import io.github.xshadov.easyrandom.vavr.VavrGenerationTests;
import io.vavr.collection.Array;
import io.vavr.collection.List;
import lombok.Value;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ArrayRandomizeTest {
	@Value
	private static class Person {
		private Array<String> array;
		private Array<Array<String>> nestedArray;
		private Array<List<String>> nestedListArray;
	}

	@Test
	public void correctRandomization() {
		final Person randomPerson = VavrGenerationTests.random(Person.class);

		assertThat(randomPerson.getArray().size()).isBetween(2, 5);

		assertThat(randomPerson.getNestedArray().size()).isBetween(2, 5);
		randomPerson.getNestedArray().forEach(inner -> assertThat(inner.size()).isBetween(2, 5));

		assertThat(randomPerson.getNestedListArray().size()).isBetween(2, 5);
		randomPerson.getNestedListArray().forEach(inner -> assertThat(inner.size()).isBetween(2, 5));
	}
}