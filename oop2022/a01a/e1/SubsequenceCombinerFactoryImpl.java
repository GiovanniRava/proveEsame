package a01a.e1;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory{

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return new SubsequenceCombiner<Integer,Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                int sum = 0;
                List<Integer> returnList = new LinkedList<>();
                for (int i = 0; i<list.size(); i++){
                    if(i % 3 == 1){
                        returnList.add(sum);
                        sum = 0;
                    }
                    sum += list.get(i);
                }
                return returnList;
            }
        };

    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tripletsToList'");
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countUntilZero'");
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'singleReplacer'");
    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cumulateToList'");
    }


}
