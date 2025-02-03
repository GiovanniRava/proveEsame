package a01a.e1;

import java.util.ArrayList;
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
                    sum += list.get(i);
                    if((i+1) % 3 == 0 || i == list.size() -1){
                        returnList.add(sum);
                        sum = 0;
                    }
                }
                return returnList;
            }
        };

    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return new SubsequenceCombiner<X,List<X>>() {
            
            @Override
            public List<List<X>> combine(List<X> list) {
            List<List<X>> result = new ArrayList<>();
            List <X> temp = new ArrayList<>();   
            for (int i = 0; i < list.size(); i++) {
                temp.add(list.get(i)); // Aggiungi elemento alla sottolista

                if ((i + 1) % 3 == 0 || i == list.size() - 1) { // Ogni 3 elementi o alla fine
                    result.add(new ArrayList<>(temp)); // Aggiungi copia della sottolista
                    temp.clear(); // Resetta la sottolista per il prossimo gruppo
                }
            }
            return result;
            }
            
        };
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return new SubsequenceCombiner<Integer,Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                List<Integer> result = new ArrayList<>();
                int sum = 0;
                for (int i = 0 ; i < list.size(); i++){
                    if(list.get(i) == 0){
                        result.add(sum);
                        sum = 0;
                    }else{
                        sum ++;
                    }
                }
                if(sum > 0){
                    result.add(sum);
                }
                return result;
            }
            
        };
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return new SubsequenceCombiner<X,Y>() {

            @Override
            public List<Y> combine(List<X> list) {
                List <Y> result = new ArrayList<>();
                for ( int i = 0 ; i < list.size(); i++){
                    result.add(function.apply(list.get(i)));
                }
                return result;
            }
            
        };
    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cumulateToList'");
    }


}

