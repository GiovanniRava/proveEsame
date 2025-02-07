package a06.e1;

import java.util.ArrayList;
import java.util.Collections;
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
        return new FluentParser<Integer>() {
            private int expected = 0; // Il primo valore atteso è 0
            private int maxValue = 0; // Il valore massimo che la sequenza può raggiungere
    
            @Override
            public FluentParser<Integer> accept(Integer value) {
                // Controlla se il valore ricevuto è quello atteso
                if (value != expected) {
                    throw new IllegalStateException("Expected " + expected + " but received " + value);
                }
    
                // Se abbiamo raggiunto il valore massimo, ripartiamo da 0 con un nuovo massimo più alto
                if (expected == maxValue) {
                    maxValue++;  // Aumentiamo il limite per il prossimo ciclo
                    expected = 0; // Ripartiamo da zero
                } else {
                    expected++; // Altrimenti continuiamo normalmente
                }
    
                return this;
            }
        };
    }
    
    @Override
    public FluentParser<String> repetitiveIncrementalStrings(String s) {
        return new FluentParser<String>() {
            private String expected = s; // Il primo valore atteso è s
            private int maxValue = 1; // Il valore massimo che la sequenza può raggiungere
            @Override
            public FluentParser<String> accept(String value) {
                // Controlla se il valore ricevuto è quello atteso
                if (!value.equals(expected)) {
                    throw new IllegalStateException("Expected " + expected + " but received " + value);
                }
    
                // Se abbiamo raggiunto il valore massimo, ripartiamo da 0 con un nuovo massimo più alto
                if (expected.length() == maxValue) {
                    maxValue++;  // Aumentiamo il limite per il prossimo ciclo
                    expected = s; // Ripartiamo da zero
                } else {
                    expected= expected +s; // Altrimenti continuiamo normalmente
                }
    
                return this;
            }
            
        };
    }

    @Override
    public FluentParser<Pair<Integer, List<String>>> incrementalPairs(int i0, UnaryOperator<Integer> op, String s) {
        return new FluentParser<Pair<Integer, List<String>>>() {
            private int currentInt = i0; // Valore iniziale
            private int maxLength = 0;  // Dimensione della lista attesa
            private List<String> currentList = new ArrayList<>(); // Lista inizialmente vuota

            @Override
            public FluentParser<Pair<Integer, List<String>>> accept(Pair<Integer, List<String>> value) {
                // Controlla se il valore dell'intero è quello atteso
                if (!value.getX().equals(currentInt)) {
                    throw new IllegalStateException("Expected " + currentInt + " but received " + value.getX());
                }

                // Controlla se la lista è quella attesa
                if (!value.getY().equals(currentList)) {
                    throw new IllegalStateException("Expected " + currentList + " but received " + value.getY());
                }

                // Aggiorna il valore dell'intero usando la funzione `op`
                currentInt = op.apply(currentInt);

                // Incrementa la lunghezza della lista attesa e crea una nuova lista con `s`
                maxLength += 2;
                currentList = new ArrayList<>(Collections.nCopies(maxLength, s));

                return this;
            }
        };
}

}
