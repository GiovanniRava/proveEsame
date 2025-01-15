package a01b.e1;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventSequenceProducerHelpsImpl implements EventSequenceProducerHelpers{

    @Override
    public <E> EventSequenceProducer<E> fromIterator(Iterator<Pair<Double, E>> iterator) {
        return () ->{
            if(iterator.hasNext()){
                return iterator.next();
            }
            throw new NoSuchElementException();
        };
    }
    private <E> Optional <E> optionalOfFaulty(Supplier<E> supplier){
        try{
            return Optional.of(supplier.get());
        }catch (Exception e){
            return Optional.empty();
        }
    }
    private <E> Stream<Pair<Double,E>> producerToStream(EventSequenceProducer<E> producer){
        return Stream.generate(()->optionalOfFaulty(producer :: getNext))
                .takeWhile(Optional::isPresent)
                .map(Optional::get);
    }
    @Override
    public <E> List<E> window(EventSequenceProducer<E> history, double fromTime, double toTime) {
        return this.producerToStream(history)
        .dropWhile(p-> p.get1()<fromTime)
        .takeWhile(p->p.get1()<toTime)
        .map(Pair::get2)
        .collect(Collectors.toList());
    }

    @Override
    public <E> Iterable<E> asEventContentIterable(EventSequenceProducer<E> sequence) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asEventContentIterable'");
    }

    @Override
    public <E> Optional<Pair<Double, E>> nextAt(EventSequenceProducer<E> sequence, double time) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextAt'");
    }

    @Override
    public <E> EventSequenceProducer<E> filter(EventSequenceProducer<E> sequence, Predicate<E> predicate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter'");
    }

}
