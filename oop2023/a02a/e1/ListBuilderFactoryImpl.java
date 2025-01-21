package a02a.e1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class ListBuilderFactoryImpl implements ListBuilderFactory{
    private <R, T> ListBuilder<R> flatMap (ListBuilder <T> builder, Function <T, ListBuilder<R>> fun){
        return new ListBuilderImpl<>(builder.build().stream().flatMap(t->fun.apply(t).build().stream()));
    }
    private class ListBuilderImpl<T> implements ListBuilder<T>{

        private final List<T> list; 
        
        public ListBuilderImpl(Stream<T> stream) {
            this.list = stream.toList();
        }

        @Override
        public ListBuilder<T> add(List<T> list) {
           return new ListBuilderImpl<>(Stream.concat(this.list.stream(), list.stream()));
        }

        @Override
        public ListBuilder<T> concat(ListBuilder<T> lb) {
            return add(lb.build());
        }

        @Override
        public ListBuilder<T> replaceAll(T t, ListBuilder<T> lb) {
            return flatMap(this, tt-> tt.equals(t)? lb : fromElement(tt));
        }

        @Override
        public ListBuilder<T> reverse() {
            var l2 = new ArrayList<>(list);
            Collections.reverse(l2);
            return new ListBuilderImpl<>(l2.stream());
        }

        @Override
        public List<T> build() {
            return this.list;
        }

    }
    @Override
    public <T> ListBuilder<T> empty() {
        return new ListBuilderImpl<>(Stream.empty());
    }
    @Override
    public <T> ListBuilder<T> fromElement(T t) {
        return new ListBuilderImpl<>(Stream.of(t));
    }
    @Override
    public <T> ListBuilder<T> fromList(List<T> list) {
       return new ListBuilderImpl<>(list.stream());
    }
    @Override
    public <T> ListBuilder<T> join(T start, T stop, List<ListBuilder<T>> builderList) {
        return this
        .fromElement(start)
        .add(builderList.stream().flatMap(lb->lb.build().stream()).toList())
        .add(List.of(stop));
    }

}
