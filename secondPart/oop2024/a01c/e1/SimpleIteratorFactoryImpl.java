package a01c.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleIteratorFactoryImpl implements SimpleIteratorFactory {

    @Override
    public SimpleIterator<Integer> naturals() {
        return new SimpleIterator<Integer>() {
            int i=0;
            @Override
            public Integer next() {
                return i++;
            }
            
        };
    }

    @Override
    public <X> SimpleIterator<X> circularFromList(List<X> list) {
        return new SimpleIterator<X>() {
            int index = -1;
            @Override
            public X next() {
                if(list.isEmpty()){
                    throw new NoSuchElementException("List is Empty");
                }
                index++;
                
                if(index > list.size()-1){
                    index = 0;
                }
               return list.get(index);
            }
        };
    }

    @Override
    public <X> SimpleIterator<X> cut(int size, SimpleIterator<X> simpleIterator) {
        return new SimpleIterator<X>() {
            int index = 0;
            @Override
            public X next() {
                if(simpleIterator==null){
                    throw new NoSuchElementException();
                }
                if(index >=size){
                    throw new NoSuchElementException();
                }
                index++;
                return simpleIterator.next();
            }
            
        };
    }

    @Override
    public <X> SimpleIterator<Pair<X, X>> window2(SimpleIterator<X> simpleIterator) {
       return new SimpleIterator<Pair<X,X>>() {
        X prev = null;
        boolean isFirst = true;

        @Override
        public Pair<X, X> next() {
            if(simpleIterator == null){
                throw new NoSuchElementException();
            }
            try {
                if(isFirst){
                    prev = simpleIterator.next();
                    isFirst = false;
                }

                X current = simpleIterator.next();

                Pair<X,X> newPair = new Pair<X,X>(prev, current);
                prev = current;

                return newPair;
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException();
            }
        }
        
       };
    }

    @Override
    public SimpleIterator<Integer> sumPairs(SimpleIterator<Integer> simpleIterator) {
        return new SimpleIterator<Integer>() {
            int prev = 0;
            private boolean isFirst= true;
            @Override
            public Integer next() {
                if(simpleIterator == null){
                    throw new NoSuchElementException();
                }
                try {
                    if(isFirst){
                        prev = simpleIterator.next();
                        isFirst = false;
                    }
                    int current = simpleIterator.next();

                    int sum = prev + current;
                    prev = current;
                    return sum;
                } catch (NoSuchElementException e) {
                    throw new NoSuchElementException();
                }
            }
            
        };
    }

    @Override
    public <X> SimpleIterator<List<X>> window(int windowSize, SimpleIterator<X> simpleIterator) {
       return new SimpleIterator<List<X>>() {
        boolean isFirst = true;
        List<X> prev = new ArrayList<>();
        @Override
        public List<X> next() {
            if(simpleIterator == null){
                throw new NoSuchElementException();
            }
            if(isFirst){
                for (int i = 0; i<= windowSize-1  ; i++){
                    prev.add(simpleIterator.next());
                }
                isFirst = false;
                return prev;
            }
            prev.removeFirst();
            prev.add(simpleIterator.next());
            return new ArrayList<>(prev);

            
        }
        
       };
    }

}
