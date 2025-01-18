package a04.e1;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class EitherFactoryImpl implements EitherFactory{

    @Override
    public <A, B> Either<A, B> success(B b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'success'");
    }

    @Override
    public <A, B> Either<A, B> failure(A a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'failure'");
    }

    @Override
    public <A> Either<Exception, A> of(Supplier<A> computation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'of'");
    }

    @Override
    public <A, B, C> Either<A, List<C>> traverse(List<B> list, Function<B, Either<A, C>> function) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'traverse'");
    }

}
