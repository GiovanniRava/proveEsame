package a01d.e1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimetableFactoryImpl implements TimetableFactory{

    private static record BookingSlot(String room, String course, Timetable.Day day, int hour){}


    private static record TimeTableImpl (Set<BookingSlot> data) implements Timetable{

        @Override
        public Set<String> rooms() {
            return data.stream().map(BookingSlot::room).collect(Collectors.toSet());
        }

        @Override
        public Set<String> courses() {
            return data.stream().map(BookingSlot::course).collect(Collectors.toSet());
        }

        @Override
        public List<Integer> hours() {
            return data.stream().map(BookingSlot::hour).distinct().sorted().collect(Collectors.toList());
        }

        @Override
        public Timetable addBooking(String room, String course, Day day, int hour, int duration) {
            return new TimeTableImpl(Stream.concat(
                Stream.iterate(hour, i->i+1).limit(duration).map(h->new BookingSlot(room, course, day, h)), data.stream()).collect(Collectors.toSet()));
        }

        @Override
        public Optional<Integer> findPlaceForBooking(String room, Day day, int duration) {
            return this.hours()
            .stream()
                .filter(h->Stream.iterate(h, i->i+1).limit(duration).allMatch(hh->data.stream().noneMatch(b->b.room.equals(room) && b.hour == hh)))
            .findFirst();
        }

        @Override
        public Map<Integer, String> getDayAtRoom(String room, Day day) {
            return data.stream()
            .filter(b-> b.room.equals(room))
            .filter(d->d.day == day)
            .collect(Collectors.toMap(BookingSlot::hour, BookingSlot::course));
        }

        @Override
        public Optional<Pair<String, String>> getDayAndHour(Day day, int hour) {
            return data.stream()
            .filter(d->d.day == day)
            .filter(h->h.hour == hour)
            .map(b-> new Pair<>(b.course, b.room()))
            .findAny();
        }

        @Override
        public Map<Day, Map<Integer, String>> getCourseTable(String course) {
            return data.stream()
                .filter(c->c.course.equals(course))
                    .collect(Collectors.groupingBy(
                        b->b.day, Collectors.toMap(BookingSlot::hour, BookingSlot::room)
                    ));
        }
        
        

    }
    @Override
    public Timetable empty() {
        return new TimeTableImpl(Set.of());  
    }

}
