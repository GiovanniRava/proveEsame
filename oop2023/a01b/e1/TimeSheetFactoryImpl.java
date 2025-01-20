package a01b.e1;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class TimeSheetFactoryImpl implements TimeSheetFactory{
    public static record TimeSheetData(List<String> activities, List<String> days, BiFunction<String, String, Integer> function) implements TimeSheet{

        @Override
        public int getSingleData(String activity, String day) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getSingleData'");
        }

        @Override
        public Map<String, Integer> sumsPerActivity() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'sumsPerActivity'");
        }

        @Override
        public Map<String, Integer> sumsPerDay() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'sumsPerDay'");
        }
    }


    
        @Override
        public List<String> activities() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'activities'");
        }

        @Override
        public List<String> days() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'days'");
        }

        @Override
        public int getSingleData(String activity, String day) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getSingleData'");
        }

        @Override
        public Map<String, Integer> sumsPerActivity() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'sumsPerActivity'");
        }

        @Override
        public Map<String, Integer> sumsPerDay() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'sumsPerDay'");
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
