package a07.e1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TaskFactoryImpl implements TaskFactory {

    @Override
    public Task<Integer> counter() {
        return new Task<Integer>() {
            private int count = 0;
            @Override
            public void reset() {
                count = 0;
            }

            @Override
            public void computationStep() {
                count++;
            }

            @Override
            public Integer temporaryResult() {
                return count;
            }
            
        };
    }

    @Override
    public Task<List<Integer>> fibonacciSequenceCreator() {
       return new Task<List<Integer>>() {
        List<Integer> fibonacciSequence = new ArrayList<>();
        @Override
        public void reset() {
            fibonacciSequence.clear();
            fibonacciSequence.add(0);
            fibonacciSequence.add(1);
        }

        @Override
        public void computationStep() {
            fibonacciSequence.add(fibonacciSequence.get((fibonacciSequence.size()-2))+fibonacciSequence.get(fibonacciSequence.size()-1));
        }

        @Override
        public List<Integer> temporaryResult() {
            return new ArrayList<>(fibonacciSequence);
        }
        
       };
    }

    @Override
    public Task<Set<Integer>> removeBiggerThan(Set<Integer> set, int bound) {
        return new Task<Set<Integer>>() {
            private Set<Integer> newSet = new HashSet<>(set);
            @Override
            public void reset() {
                newSet= new HashSet<>(set);
            }

            @Override
            public void computationStep() {
                Optional<Integer> maxAboveBound = set.stream().filter(n->n>bound).max(Integer::compareTo);
                if(maxAboveBound.isEmpty()){
                    return;
                }
                maxAboveBound.isPresent(set::remove);
            }

            @Override
            public Set<Integer> temporaryResult() {
               return new HashSet<>(set);
            }
            
        };
    }

}
