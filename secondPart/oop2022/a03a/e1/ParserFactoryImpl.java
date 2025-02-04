package a03a.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class ParserFactoryImpl implements ParserFactory{

    @Override
    public <X> Parser<X> fromFinitePossibilities(Set<List<X>> acceptedSequences) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
               List<X> list = new ArrayList<>();
               while(iterator.hasNext()){
                    list.add(iterator.next());
               }

               return acceptedSequences.contains(list);
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromGraph(X x0, Set<Pair<X, X>> transitions, Set<X> acceptanceInputs) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                List<X> list = new ArrayList<>();
                while(iterator.hasNext()){
                    list.add(iterator.next());
                }

                if(list.isEmpty()){
                    return false;
                }

                if(!list.get(0).equals(x0)){
                    return false;
                }
                for (int i = 0 ; i < list.size()-1; i++){
                    if(acceptanceInputs.contains(new Pair<>(list.get(i), list.get(i+1)))){
                        return false;
                    }
                }
                return acceptanceInputs.contains(list.get(list.size()-1));
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                List<X> list = new ArrayList<>();
                while(iterator.hasNext()){
                    list.add(iterator.next());
                }

                if(list.isEmpty()){
                    return false;
                }

                if(!list.getFirst().equals(x0)){
                    return false;
                }
                for (int i = 1; i < list.size() - 1 ; i++){
                    if(!next.apply(list.get(i-1)).equals(Optional.of(list.get(i)))){
                        return false;
                    }
                }
                return next.apply(list.get(list.size()-1)).isEmpty();

            }
            
        };
    }

    @Override
    public <X> Parser<X> recursive(Function<X, Optional<Parser<X>>> nextParser, boolean isFinal) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                if(!iterator.hasNext()){
                    return isFinal;
                }

                return nextParser.apply(iterator.next()).map(i->i.accept(iterator)).orElse(false);
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromParserWithInitial(X x, Parser<X> parser) {
        return recursive(p->p.equals(x)
                ?Optional.of(parser)
                : Optional.empty(), 
                false);
    }

}
