package a01d.e1;

import java.util.*;

public class TimetableFactoryImpl implements TimetableFactory{
    private static record BookingSlot (Set<String> rooms, Set<String> courses, List<Integer> hours) implements Timetable{
        
        @Override
        public Timetable addBooking(String room, String course, Day day, int hour, int duration) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addBooking'");
        }

        @Override
        public Optional<Integer> findPlaceForBooking(String room, Day day, int duration) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'findPlaceForBooking'");
        }

        @Override
        public Map<Integer, String> getDayAtRoom(String room, Day day) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDayAtRoom'");
        }

        @Override
        public Optional<Pair<String, String>> getDayAndHour(Day day, int hour) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDayAndHour'");
        }

        @Override
        public Map<Day, Map<Integer, String>> getCourseTable(String course) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCourseTable'");
        }

    }
    @Override
    public Timetable empty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'empty'");
    }

}
