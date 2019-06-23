package io.github.xshadov.easyrandom.vavr.randomizers;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Value;
import io.vavr.collection.*;
import io.vavr.control.Option;
import lombok.experimental.UtilityClass;
import org.jeasy.random.api.Randomizer;

@UtilityClass
class VavrEmptyRandomizers {
	private static final Map<Class<?>, Value<?>> EMPTY_RANDOMIZERS = allRandomizers();

	static Option<Randomizer<?>> from(final Class<?> clazz) {
		return EMPTY_RANDOMIZERS
				.get(clazz)
				.map(VavrEmptyRandomizers::toRandomizer);
	}

	private static Randomizer<?> toRandomizer(Value<?> val) {
		return () -> val;
	}

	private static Map<Class<?>, Value<?>> allRandomizers() {
		return listRandomizers()
				.appendAll(setRandomizers())
				.appendAll(mapRandomizers())
				.appendAll(multimapRandomizers())
				.appendAll(collectionRandomizers())
				.collect(HashMap.collector());
	}

	private static List<Tuple2<Class<?>, Value<?>>> listRandomizers() {
		return List.of(
				Tuple.of(Foldable.class, List.empty()),
				Tuple.of(Traversable.class, List.empty()),
				Tuple.of(Seq.class, List.empty()),
				Tuple.of(LinearSeq.class, List.empty()),
				Tuple.of(List.class, List.empty())
		);
	}

	private static List<Tuple2<Class<?>, Value<?>>> setRandomizers() {
		return List.of(
				Tuple.of(Set.class, HashSet.empty()),
				Tuple.of(SortedSet.class, TreeSet.empty()),
				Tuple.of(HashSet.class, HashSet.empty()),
				Tuple.of(LinkedHashSet.class, LinkedHashSet.empty()),
				Tuple.of(TreeSet.class, TreeSet.empty())
		);
	}

	private static List<Tuple2<Class<?>, Value<?>>> mapRandomizers() {
		return List.of(
				Tuple.of(Map.class, HashMap.empty()),
				Tuple.of(HashMap.class, HashMap.empty()),
				Tuple.of(SortedMap.class, TreeMap.empty()),
				Tuple.of(TreeMap.class, TreeMap.empty()),
				Tuple.of(LinkedHashMap.class, LinkedHashMap.empty())
		);
	}

	private static List<Tuple2<Class<?>, Value<?>>> multimapRandomizers() {
		return List.of(
				Tuple.of(Multimap.class, HashMultimap.withSeq().empty()),
				Tuple.of(HashMultimap.class, HashMultimap.withSeq().empty()),
				Tuple.of(SortedMultimap.class, TreeMultimap.withSeq().empty()),
				Tuple.of(LinkedHashMultimap.class, LinkedHashMultimap.withSeq().empty()),
				Tuple.of(TreeMultimap.class, TreeMultimap.withSeq().empty())
		);
	}

	private static List<Tuple2<Class<?>, Value<?>>> collectionRandomizers() {
		return List.of(
				Tuple.of(IndexedSeq.class, Array.empty()),
				Tuple.of(Array.class, Array.empty()),
				Tuple.of(Stream.class, Stream.empty()),
				Tuple.of(Vector.class, Vector.empty()),
				Tuple.of(Queue.class, Queue.empty()),
				Tuple.of(PriorityQueue.class, PriorityQueue.empty()),
				Tuple.of(Tree.class, Tree.empty())
		);
	}
}
