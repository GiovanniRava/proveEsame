package a01a.e1;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

import java.util.*;
import java.util.List;
import java.util.Set;

public class ComposableParserFactoryImpl implements ComposableParserFactory {
    private static class ComposableParserImpl<T> implements ComposableParser<T>{
        private Set<Iterator<T>> iterators;
        
        public ComposableParserImpl(Set<Iterator<T>> iterators) {
            this.iterators = Set.copyOf(iterators);
        }

        @Override
        public boolean parse(Object t) {
            iterators = iterators.stream().filter(Iterator::hasNext).filter(it-> it.next().equals(t)).collect(toSet());
            return !iterators.isEmpty();
        }

        @Override
        public boolean end() {
            return this.iterators.stream().anyMatch(it->!it.hasNext());
        }
        
    }
    @Override
    public <X> ComposableParser<X> empty() {
        return fromList(Collections.emptyList());
    }

    @Override
    public <X> ComposableParser<X> one(X x) {
        return fromList(List.of(x));
    }

    @Override
    public <X> ComposableParser<X> fromList(List<X> list) {
        return fromAnyList(Set.of(list));
    }

    @Override
    public <X> ComposableParser<X> fromAnyList(Set<List<X>> input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromAnyList'");
    }

    @Override
    public <X> ComposableParser<X> seq(ComposableParser<X> parser, List<X> list) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seq'");
    }

    @Override
    public <X> ComposableParser<X> or(ComposableParser<X> p1, ComposableParser<X> p2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'or'");
    }

    @Override
    public boolean parse(Object t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parse'");
    }

    @Override
    public boolean end() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'end'");
    }

}
