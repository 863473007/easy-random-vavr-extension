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

import io.vavr.collection.*;
import org.jeasy.random.util.ReflectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

enum VavrTypes {
	LIST_TYPES(Foldable.class, Traversable.class, Seq.class, LinearSeq.class, List.class),
	SET_TYPES(Set.class, SortedSet.class, HashSet.class, LinkedHashSet.class, TreeSet.class),
	MAP_TYPES(Map.class, HashMap.class, SortedMap.class, TreeMap.class, LinkedHashMap.class),
	MULTIMAP_TYPES(Multimap.class, SortedMultimap.class, TreeMultimap.class, HashMultimap.class, LinkedHashMultimap.class),
	COLLECTION_TYPES(IndexedSeq.class, Stream.class, Array.class, Vector.class, Queue.class, PriorityQueue.class, Tree.class),
	COMPARABLE_TYPES(SortedSet.class, TreeSet.class, TreeMap.class, SortedMap.class, SortedMultimap.class, TreeMultimap.class, PriorityQueue.class);

	private List<Class<?>> types;

	VavrTypes(Class<?>... types) {
		this.types = List.of(types);
	}

	public static boolean isComparable(Class<?> type) {
		return COMPARABLE_TYPES.types.contains(type);
	}

	public static boolean isList(Class<?> type) {
		return LIST_TYPES.types.contains(type);
	}

	public static boolean isMap(Class<?> type) {
		return MAP_TYPES.types.contains(type);
	}

	public static boolean isSet(Class<?> type) {
		return SET_TYPES.types.contains(type);
	}

	public static boolean isMultimap(Class<?> type) {
		return MULTIMAP_TYPES.types.contains(type);
	}

	public static boolean isCollection(final Class<?> type) {
		return COLLECTION_TYPES.types.contains(type);
	}

	public static boolean contains(final Class<?> type) {
		return isCollection(type) || isMap(type) || isMultimap(type) || isSet(type) || isList(type);
	}

	public static boolean needsEmptyRandomizer(final Class<?> fieldType, Type genericType) {
		if (!ReflectionUtils.isParameterizedType(genericType))
			return true;

		if (VavrTypes.isMap(fieldType) || VavrTypes.isMultimap(fieldType)) {
			final Type keyGenericType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
			final Type valueGenericType = ((ParameterizedType) genericType).getActualTypeArguments()[1];

			return ReflectionUtils.isWildcardType(keyGenericType) || ReflectionUtils.isWildcardType(valueGenericType);
		} else {
			final Type firstGenericType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

			return ReflectionUtils.isWildcardType(firstGenericType);
		}
	}
}
