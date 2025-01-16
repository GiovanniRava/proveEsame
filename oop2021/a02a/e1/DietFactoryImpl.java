package a02a.e1;

import java.util.Map;

public class DietFactoryImpl implements DietFactory{

    @Override
    public Diet standard() {
        return new Diet() {

            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'addFood'");
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'isValid'");
            }
            
        };
    }

    @Override
    public Diet lowCarb() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lowCarb'");
    }

    @Override
    public Diet highProtein() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'highProtein'");
    }

    @Override
    public Diet balanced() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'balanced'");
    }

}
