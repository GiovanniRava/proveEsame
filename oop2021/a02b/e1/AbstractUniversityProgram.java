package a02b.e1;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractUniversityProgram implements UniversityProgram {
    
    private final Map<String, Course> courseMap = new HashMap();

    @Override
    public void addCourse(String name, Sector sector, int credits) {
        if(this.courseMap.containsKey(name)){
            throw new IllegalArgumentException();
        }
        this.courseMap.put(name, new Course(name, sector, credits)); 
    }

    @Override
    public boolean isValid(Set<String> courseNames) {
        Set<Course> courses = courseNames.stream().map(this.courseMap::get).collect(Collectors.toSet());
        return this.getConstraints().stream().allMatch(constraint -> this.isConstraintSatisfied(constraint, courses));
    }
    private boolean isConstraintSatisfied(Pair<Predicate<Sector>, Predicate<Integer>> constraint, Set<Course> course){
        return constraint.get2().test(course.stream()
            .filter(c->constraint.get1().test(c.getSector()))
            .mapToInt(Course::getCredits)
            .sum());
    }
    protected abstract Set<Pair<Predicate<Sector>, Predicate<Integer>>> getConstraints();
    private static class Course{

        private String name; 
        private Sector sector; 
        private int credits;
        
        public Course(String name, Sector sector, int credits) {
            super();
            this.name = name;
            this.sector = sector;
            this.credits = credits;
        }

        public String getName() {
            return name;
        }

        public Sector getSector() {
            return sector;
        }

        public int getCredits() {
            return credits;
        }

        @Override
        public int hashCode() {
            return Objects.hash(credits, name, sector);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Course other = (Course) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            if (sector != other.sector)
                return false;
            if (credits != other.credits)
                return false;
            return true;
        }
        
        @Override
        public String toString() {
            return "Course [name=" + name + ", sector=" + sector + ", credits=" + credits + "]";
        }
        
    }

    
    

}
