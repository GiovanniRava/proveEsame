package a06.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class FluentParserFactoryImpl implements FluentParserFactory {

    @Override
    public FluentParser<Integer> naturals() {
        return new FluentParser<Integer>() {
            private Integer expected = 0;
            @Override
            public FluentParser<Integer> accept(Integer value) {
               if(!expected.equals(value)){
                throw new IllegalStateException();
               }
               expected++;
               return this;
            }
            
        };
    }

    @Override
    public FluentParser<List<Integer>> incrementalNaturalLists() {
       return new  FluentParser<List<Integer>>() {
            private List<Integer> previous = new ArrayList<>();
            private Integer expected = 0;
            @Override
            public FluentParser<List<Integer>> accept(List<Integer> value) {
                if (previous.isEmpty() && !value.isEmpty()) {
                    throw new IllegalStateException("The first list must be empty.");
                }
                if(value.size()!= previous.size() + 1){
                    throw new IllegalStateException("List size must increase incrementally.");
                }
                if(value.get(value.size()-1)!= expected){
                    throw new IllegalStateException("Expected last element:"+expected);
                }
                expected++;
                previous = new ArrayList<>(value);
                return this;
            }
        
       };
    }

    @Override
    public FluentParser<Integer> repetitiveIncrementalNaturals() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'repetitiveIncrementalNaturals'");
    }

    @Override
    public FluentParser<String> repetitiveIncrementalStrings(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'repetitiveIncrementalStrings'");
    }

    @Override
    public FluentParser<Pair<Integer, List<String>>> incrementalPairs(int i0, UnaryOperator<Integer> op, String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incrementalPairs'");
    }

}
