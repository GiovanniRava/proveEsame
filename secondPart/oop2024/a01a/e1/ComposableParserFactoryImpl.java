package a01a.e1;

import java.util.List;
import java.util.Set;

public class ComposableParserFactoryImpl implements ComposableParserFactory {
    private static class ComposableParserImpl implements ComposableParser{

        @Override
        public boolean parse(Object t) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'parse'");
        }

        @Override
        public boolean end() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'end'");
        }
        
    }
    @Override
    public <X> ComposableParser<X> empty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'empty'");
    }

    @Override
    public <X> ComposableParser<X> one(X x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'one'");
    }

    @Override
    public <X> ComposableParser<X> fromList(List<X> list) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromList'");
    }

    @Override
    public <X> ComposableParser<X> fromAnyList(Set<List<X>> input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromAnyList'");
    }

    @Override
    public <X> ComposableParser<X> seq(ComposableParser<X> parser, List<X> list) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seq'");
    }

    @Override
    public <X> ComposableParser<X> or(ComposableParser<X> p1, ComposableParser<X> p2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'or'");
    }

    @Override
    public boolean parse(Object t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parse'");
    }

    @Override
    public boolean end() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'end'");
    }

}
