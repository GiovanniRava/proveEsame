package a01b.e1;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TimeSheetFactoryImpl implements TimeSheetFactory{
    public static record TimeSheetData(List<String> activities, List<String> days, BiFunction<String, String, Integer> function) implements TimeSheet{

        @Override
        public int getSingleData(String activity, String day) {
            return activities.contains(activity) && days.contains(day)? function.apply(activity, day) : 0;
        }

        @Override
        public Map<String, Integer> sumsPerActivity() {
            return activities.stream()
            .map(act-> new Pair<>(act, days.stream()
                    .map(day->function.apply(act, day))
                    .collect(Collectors.summingInt(i->i))))
            .collect(Collectors.toMap(Pair :: get1, Pair :: get2));
        }

        @Override
        public Map<String, Integer> sumsPerDay() {
            return days.stream()
                    .map(day -> new Pair<>(day,activities.stream()
                            .map(act -> fun.apply(act,day))
                            .collect(Collectors.summingInt(i->i))))
                    .collect(Collectors.toMap(Pair::get1, Pair::get2));
        }
    }

    @Override
    public TimeSheet flat(int numActivities, int numNames, int hours) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flat'");
    }

    @Override
    public TimeSheet ofListsOfLists(List<String> activities, List<String> days, List<List<Integer>> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ofListsOfLists'");
    }

    @Override
    public TimeSheet ofRawData(int numActivities, int numDays, List<Pair<Integer, Integer>> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ofRawData'");
    }

    @Override
    public TimeSheet ofPartialMap(List<String> activities, List<String> days, Map<Pair<String, String>, Integer> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ofPartialMap'");
    }

}
