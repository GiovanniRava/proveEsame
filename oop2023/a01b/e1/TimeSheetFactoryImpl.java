package a01b.e1;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private List<String> createActivities(int numActivities){
        return Stream.iterate(1, i->i+1).map(i->"act"+i).limit(numActivities).collect(Collectors.toList());
    }

    private List<String> createDays (int numDays){
        return Stream.iterate(1, i->i+1).map(i->"days"+i).limit(numDays).collect(Collectors.toList());
    }
    @Override
    public TimeSheet flat(int numActivities, int numNames, int hours) {
        var activities = createActivities(numActivities);
        var days = createDays(numNames);
        return new TimeSheetData(activities, days, (a,d)->hours);
    }

    @Override
    public TimeSheet ofListsOfLists(List<String> activities, List<String> days, List<List<Integer>> data) {
        return new TimeSheetData(
            List.copyOf(activities),
            List.copyOf(days), 
            (a,d) -> (int)data.get(activities.indexOf(a)).get(data.indexOf(d)));
    }

    @Override
    public TimeSheet ofRawData(int numActivities, int numDays, List<Pair<Integer, Integer>> data) {
        var activities = createActivities(numActivities);
        var days = createDays(numDays);
        return new TimeSheetData(activities, days, 
        (a,d)->(int) data.stream().filter(p->p.get1().equals(activities.indexOf(a))&& p.get2().equals(days.indexOf(d))).count());
    }

    @Override
    public TimeSheet ofPartialMap(List<String> activities, List<String> days, Map<Pair<String, String>, Integer> data) {
        return new TimeSheetData(List.copyOf(activities), List.copyOf(days), 
        (a,d)-> data.getOrDefault(new Pair<>(a, d), 0));
    }

}
