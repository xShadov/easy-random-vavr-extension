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

import io.github.xshadov.easyrandom.vavr.VavrGenerationTests;
import io.vavr.collection.*;
import lombok.Value;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WildcardTypesRandomizeTest extends VavrGenerationTests {
	@Value
	private static class SimpleTypes {
		private Set<?> set;
		private List<?> list;
		private Foldable<?> foldable;
		private Traversable<?> traversable;
		private Seq<?> seq;
		private LinearSeq<?> linearSeq;
		private SortedSet<?> sortedSet;
		private HashSet<?> hashSet;
		private LinkedHashSet<?> linkedHashSet;
		private TreeSet<?> treeSet;
		private Map<?, ?> map;
		private HashMap<?, ?> hashMap;
		private SortedMap<?, ?> sortedMap;
		private TreeMap<?, ?> treeMap;
		private LinkedHashMap<?, ?> linkedHashMap;
		private Multimap<?, ?> multimap;
		private SortedMultimap<?, ?> sortedMultimap;
		private TreeMultimap<?, ?> treeMultimap;
		private HashMultimap<?, ?> hashMultimap;
		private LinkedHashMultimap<?, ?> linkedHashMultimap;
		private IndexedSeq<?> indexedSeq;
		private Stream<?> stream;
		private Array<?> array;
		private Vector<?> vector;
		private Queue<?> queue;
		private PriorityQueue<?> priorityQueue;
		private Tree<?> tree;
	}

	@Value
	private static class NestedTypes {
		private Set<Set<?>> set;
		private List<List<?>> list;
		private Foldable<Foldable<?>> foldable;
		private Traversable<Traversable<?>> traversable;
		private Seq<Seq<?>> seq;
		private LinearSeq<LinearSeq<?>> linearSeq;
		private HashSet<HashSet<?>> hashSet;
		private LinkedHashSet<LinkedHashSet<?>> linkedHashSet;
		private Map<String, Map<String, ?>> map;
		private HashMap<String, HashMap<String, ?>> hashMap;
		private SortedMap<String, SortedMap<String, ?>> sortedMap;
		private TreeMap<String, TreeMap<String, ?>> treeMap;
		private LinkedHashMap<String, LinkedHashMap<String, ?>> linkedHashMap;
		private Multimap<String, Multimap<String, ?>> multimap;
		private SortedMultimap<String, SortedMultimap<String, ?>> sortedMultimap;
		private TreeMultimap<String, TreeMultimap<String, ?>> treeMultimap;
		private HashMultimap<String, HashMultimap<String, ?>> hashMultimap;
		private LinkedHashMultimap<String, LinkedHashMultimap<String, ?>> linkedHashMultimap;
		private IndexedSeq<IndexedSeq<?>> indexedSeq;
		private Stream<Stream<?>> stream;
		private Array<Array<?>> array;
		private Vector<Vector<?>> vector;
		private Queue<Queue<?>> queue;
		private Tree<Tree<?>> tree;
	}

	@Test
	public void wilcardSimpleTypes() {
		final SimpleTypes result = random(SimpleTypes.class);

		assertThat(result.getSet()).isNotNull();
		assertThat(result.getList()).isNotNull();
		assertThat(result.getArray()).isNotNull();
		assertThat(result.getFoldable()).isNotNull();
		assertThat(result.getHashMap()).isNotNull();
		assertThat(result.getHashMultimap()).isNotNull();
		assertThat(result.getHashSet()).isNotNull();
		assertThat(result.getIndexedSeq()).isNotNull();
		assertThat(result.getLinearSeq()).isNotNull();
		assertThat(result.getLinkedHashMap()).isNotNull();
		assertThat(result.getLinkedHashMultimap()).isNotNull();
		assertThat(result.getLinkedHashSet()).isNotNull();
		assertThat(result.getMap()).isNotNull();
		assertThat(result.getMultimap()).isNotNull();
		assertThat(result.getPriorityQueue()).isNotNull();
		assertThat(result.getQueue()).isNotNull();
		assertThat(result.getSeq()).isNotNull();
		assertThat(result.getSortedMap()).isNotNull();
		assertThat(result.getSortedMultimap()).isNotNull();
		assertThat(result.getSortedSet()).isNotNull();
		assertThat(result.getStream()).isNotNull();
		assertThat(result.getTraversable()).isNotNull();
		assertThat(result.getTree()).isNotNull();
		assertThat(result.getTreeMap()).isNotNull();
		assertThat(result.getTreeMultimap()).isNotNull();
		assertThat(result.getTreeSet()).isNotNull();
		assertThat(result.getVector()).isNotNull();
	}

	@Test
	public void wilcardNestedTypes() {
		final NestedTypes result = random(NestedTypes.class);

		assertThat(result.getSet()).isNotNull();
		assertThat(result.getList()).isNotNull();
		assertThat(result.getArray()).isNotNull();
		assertThat(result.getFoldable()).isNotNull();
		assertThat(result.getHashMap()).isNotNull();
		assertThat(result.getHashMultimap()).isNotNull();
		assertThat(result.getHashSet()).isNotNull();
		assertThat(result.getIndexedSeq()).isNotNull();
		assertThat(result.getLinearSeq()).isNotNull();
		assertThat(result.getLinkedHashMap()).isNotNull();
		assertThat(result.getLinkedHashMultimap()).isNotNull();
		assertThat(result.getLinkedHashSet()).isNotNull();
		assertThat(result.getMap()).isNotNull();
		assertThat(result.getMultimap()).isNotNull();
		assertThat(result.getQueue()).isNotNull();
		assertThat(result.getSeq()).isNotNull();
		assertThat(result.getSortedMap()).isNotNull();
		assertThat(result.getSortedMultimap()).isNotNull();
		assertThat(result.getStream()).isNotNull();
		assertThat(result.getTraversable()).isNotNull();
		assertThat(result.getTree()).isNotNull();
		assertThat(result.getTreeMap()).isNotNull();
		assertThat(result.getTreeMultimap()).isNotNull();
		assertThat(result.getVector()).isNotNull();
	}
}
