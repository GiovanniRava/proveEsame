package a06.e1;


import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

public class ZipCombinerFactoryImpl implements ZipCombinerFactory {
    private static <X, Y, Z> ZipCombiner<X, Y, Z> generic(BiFunction<X, Iterator<Y>, Stream<Z>> extractor){
        return new ZipCombiner<X,Y,Z>() {

            @Override
            public List<Z> zipCombine(List<X> l1, List<Y> l2) {
                var it2 = l2.iterator();
                return l1.stream().flatMap(m-> extractor.apply(m, it2)).toList();
            }
            
        };
    }
    @Override
    public <X, Y> ZipCombiner<X, Y, Pair<X, Y>> classical() {
       return generic((x, it)->Stream.of(new Pair<>(x, it.next())));
    }

    @Override
    public <X, Y, Z> ZipCombiner<X, Y, Z> mapFilter(Predicate<X> predicate, Function<Y, Z> mapper) {
        return generic((x, it)->Stream.of(mapper.apply(it.next())).filter(v-> predicate.test(x)));
    }

    @Override
    public <Y> ZipCombiner<Integer, Y, List<Y>> taker() {
        return generic((x,it)->Stream.of(Stream.iterate(0, n->n+1).limit(x).map(n->it.next()).toList()));
    }

    @Override
    public <X> ZipCombiner<X, Integer, Pair<X, Integer>> countUntilZero() {
        return generic((x, it)->Stream.of(new Pair<>(x, (int)Stream.iterate(it.next(), n -> n!= 0, n-> it.next()).count())));
    }

}
