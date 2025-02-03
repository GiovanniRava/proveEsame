package a02b.e1;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CursorHelpersImpl implements CursorHelpers {

    private <X> Cursor<X> generic (Iterator<X> iterator){
        return new Cursor<X> (){
            X element = iterator.next();
            @Override
            public X getElement() {
                return element;
            }

            @Override
            public boolean advance() {
                if(iterator.hasNext()){
                    element = iterator.next();
                    return true;
                }
                return false;
            }

        };
    }
    @Override
    public <X> Cursor<X> fromNonEmptyList(List<X> list) {
        return this.generic(list.iterator());
    }

    @Override
    public Cursor<Integer> naturals() {
       return this.generic(Stream.iterate(0, i->i+1).iterator());
    }

    @Override
    public <X> Cursor<X> take(Cursor<X> input, int max) {
       return new Cursor<X>(){
        int count = 0;
        @Override
        public X getElement() {
            return input.getElement();
        }

        @Override
        public boolean advance() {
            if(count<max-1){
                count ++;
                return input.advance();
            }
            return false;
        }

       };
        
    }

    @Override
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        do {
            consumer.accept(input.getElement());
        } while (input.advance());
    }

    @Override
    public <X> List<X> toList(Cursor<X> input, int max) {
        List<X> result = new ArrayList();
        for(int i = 0; i < max ; i++){
            result.add(input.getElement());
            if(!input.advance()){
                break;
            }
        }
        return result;
    }

}
