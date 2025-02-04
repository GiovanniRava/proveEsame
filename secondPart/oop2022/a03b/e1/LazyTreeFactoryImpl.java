package a03b.e1;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LazyTreeFactoryImpl implements LazyTreeFactory{
    
    public <X> LazyTree<X> cons (Supplier<LazyTree<X>> right, Supplier<LazyTree<X>> left, Optional<X> root){
        return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                return root.isPresent();
            }

            @Override
            public X root() {
                if(!hasRoot()){
                    throw new NoSuchElementException();
                }
                return root.get();
                
            }

            @Override
            public LazyTree<X> left() {
                if(!hasRoot()){
                    throw new NoSuchElementException();
                }
                return left.get();
            }

            @Override
            public LazyTree<X> right() {
                if(!hasRoot()){
                    throw new NoSuchElementException();
                }
                return right.get();
            }
            
        };
    }
    @Override
    public <X> LazyTree<X> constantInfinite(X value) {
        return cons(
            ()->constantInfinite(value), 
            ()-> constantInfinite(value),
            Optional.of(value));
    }

    
    @Override
    public <X> LazyTree<X> fromMap(X root, Map<X, Pair<X, X>> map) {
        return cons(
            () -> map.containsKey(root) ? fromMap(map.get(root).getX(), map) : emptyTree(),
            () -> map.containsKey(root) ? fromMap(map.get(root).getY(), map) : emptyTree(),
            Optional.of(root)
        );
    }

    private <X>LazyTree<X> emptyTree(){
        return cons(
            ()->emptyTree(), 
            () -> emptyTree(), 
            Optional.empty()
        );
    }

    @Override
    public <X> LazyTree<X> fromTwoIterations(X root, UnaryOperator<X> leftOp, UnaryOperator<X> rightOp) {
        return cons(
            ()-> fromTwoIterations(rightOp.apply(root), leftOp, rightOp), 
            () -> fromTwoIteration(leftOp.apply(root), leftOp, rightOp), 
            Optional.of(root)
        );
    }

    @Override
    public <X> LazyTree<X> fromTreeWithBound(LazyTree<X> tree, int bound) {
        
    }

}
