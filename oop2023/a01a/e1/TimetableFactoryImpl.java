package a01a.e1;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimetableFactoryImpl  implements TimetableFactory{
    private static <T>Set<T> addToSet(Set<T> s, T t ){
        return concatSet(s, Set.of(t));
    }
    private static <T> Set <T> concatSet (Set<T> s, Set <T> s2){
        return Stream.concat(s.stream(), s2.stream()).collect(Collectors.toSet());
    }
    private static record TimetableData(Set<String> activities, Set<String> days, BiFunction<String, String, Integer> data) implements Timetable{

        @Override
        public Timetable addHour(String activity, String day) {
            return new TimetableData(
                addToSet(activities, activity), 
                addToSet(days, day), 
                (a,d)-> data.apply(a,d) + (activity.equals(a) && day.equals(d)? 1 : 0)
                );
        }

        @Override
        public int getSingleData(String activity, String day) {
            return data.apply(activity, day);
        }
        private int statistics(BiPredicate <String, String> predicate){
            return activities.stream()
                .flatMap(a->days.stream()
                        .filter(d->predicate.test(a, d))
                        .map(d->this.getSingleData(a, d)))
                .collect(Collectors.summingInt(i -> i));
        }

        @Override
        public int sums(Set<String> activities, Set<String> days) {
           return statistics((a,d)->activities.contains(a)&& days.contains(d));
        }

    }
    @Override
    public Timetable empty() {
         
            
        
    }

    @Override
    public Timetable single(String activity, String day) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'single'");
    }

    @Override
    public Timetable join(Timetable table1, Timetable table2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'join'");
    }

    @Override
    public Timetable cut(Timetable table, BiFunction<String, String, Integer> bounds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cut'");
    }

}
