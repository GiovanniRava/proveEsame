package a01b.e1;



import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FlattenerFactoryImpl implements FlattenerFactory{

    @Override
    public Flattener<Integer, Integer> sumEach() {
        return new Flattener<Integer, Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                
                List <Integer> result = new ArrayList<>();
                for (List<Integer> innerList : list) {
                    int sum = 0;
                    for (Integer integer : innerList) {
                        sum += integer;
                    }
                    result.add(sum);
                }
                return result;
            }
            
        };
    }

    @Override
    public <X> Flattener<X, X> flattenAll() {
        return new Flattener<X,X>() {

            @Override
            public List<X> flatten(List<List<X>> list) {
                List <X> result = new ArrayList<>();
                for (List<X> innerList : list) {
                    for (X x : innerList) {
                        result.add(x);
                    }
                }
                return result;
            }
            
        };
    }

    @Override
    public Flattener<String, String> concatPairs() {
        return new Flattener<String, String>() {
            @Override
            public List<String> flatten(List<List<String>> list) {
                List<String> result = new ArrayList<>();
                for (int i = 0; i < list.size(); i += 2) {
                    // Prendi la prima lista della coppia
                    List<String> firstList = list.get(i);
                    // Prendi la seconda lista della coppia (se esiste)
                    List<String> secondList = (i + 1 < list.size()) ? list.get(i + 1) : new ArrayList<>();
                    
                    // Concatena tutte le stringhe delle due liste
                    StringBuilder concatenated = new StringBuilder();
                    for (String s : firstList) {
                        concatenated.append(s);
                    }
                    for (String s : secondList) {
                        concatenated.append(s);
                    }
                    
                    // Aggiungi la stringa concatenata al risultato
                    result.add(concatenated.toString());
                }
                return result;
            }
        };
    }

    @Override
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        return new Flattener<I,O>() {
            @Override
            public List<O> flatten(List<List<I>> list){
                List<O> result = new ArrayList<>();
                for (List<I> innerList : list) {
                    result.add(mapper.apply(innerList));  
                }
                return result;
            }
        };
    }

    @Override
    public Flattener<Integer, Integer> sumVectors() {
        return new Flattener<Integer, Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                List<Integer> result = new ArrayList<>();

                // Se la lista è vuota, restituisci una lista vuota
                if (list.isEmpty()) {
                    return result;
                }

                // Ottieni la dimensione delle liste interne (assumiamo che siano tutte della stessa dimensione)
                int size = list.get(0).size();

                // Itera per ogni posizione (indice) nelle liste interne
                for (int i = 0; i < size; i++) {
                    int sum = 0;

                    // Somma gli elementi nella posizione i di tutte le liste interne
                    for (List<Integer> innerList : list) {
                        sum += innerList.get(i);
                    }

                    // Aggiungi la somma alla lista dei risultati
                    result.add(sum);
                }

                return result;
            }
        };
}
}
