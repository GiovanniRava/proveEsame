package a02a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RecursiveIteratorHelpersImpl implements RecursiveIteratorHelpers{
    private <X> RecursiveIterator <X> cons ( X element, Supplier<RecursiveIterator<X>> next){
        return new RecursiveIterator<X>() {

            @Override
            public X getElement() {
                return element;
            }

            @Override
            public RecursiveIterator<X> next() {
                return next.get();
            }
            
        };
    }

    @Override
    public <X> RecursiveIterator<X> fromList(List<X> list) {
        return fromListHelper(list, 0);
    }

    private <X> RecursiveIterator <X> fromListHelper (List<X> list, int index){
        if(index>=list.size()){
            return null;
        }
        return cons(list.get(index), ()->fromListHelper(list, index+1));
    }

    @Override
    public <X> List<X> toList(RecursiveIterator<X> input, int max) {
        List<X> result = new ArrayList<>();
       for(int i = 0 ; i <max && input != null; i++){
            result.add(input.getElement());
            input = input.next();
        }
        return result;
    }

    @Override
    public <X, Y> RecursiveIterator<Pair<X, Y>> zip(RecursiveIterator<X> first, RecursiveIterator<Y> second) {
        if(first == null || second == null){
            return null;
        }
        return cons(new Pair<>(first.getElement(), second.getElement()), ()-> zip(first.next(), second.next()));
            
    }
    

    @Override
    public <X> RecursiveIterator<Pair<X, Integer>> zipWithIndex(RecursiveIterator<X> iterator) {
        return zipWithIndexHelper(iterator, 0);
    }

    private <X> RecursiveIterator<Pair<X, Integer>> zipWithIndexHelper(RecursiveIterator<X> iterator, int index) {
        if (iterator == null) {
            return null;
        }

        return cons(new Pair<>(iterator.getElement(), index), 
                    () -> zipWithIndexHelper(iterator.next(), index + 1)); 
    }


    @Override
    public <X> RecursiveIterator<X> alternate(RecursiveIterator<X> first, RecursiveIterator<X> second) {
        if (first == null) return second; // Se il primo è finito, restituiamo il secondo
        if (second == null) return first; // Se il secondo è finito, restituiamo il primo
    
        return cons(first.getElement(), 
                    () -> alternate(second, first.next())); 
    }    

}
