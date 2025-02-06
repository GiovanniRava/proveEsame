package a05.e1;

public class ExpressionParserFactoryImpl implements ExpressionParserFactory{
    private class ExpressionParserImpl implements ExpressionParser{
        private final S state;
        p
        @Override
        public void acceptNumber(int n) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'acceptNumber'");
        }

        @Override
        public void acceptSum() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'acceptSum'");
        }

        @Override
        public void acceptOpenParen() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'acceptOpenParen'");
        }

        @Override
        public void acceptCloseParen() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'acceptCloseParen'");
        }

        @Override
        public void close() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'close'");
        }

    }
    @Override
    public ExpressionParser oneSum() {
        
    }

    @Override
    public ExpressionParser zeroOrManySums() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'zeroOrManySums'");
    }

    @Override
    public ExpressionParser oneLevelParens() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'oneLevelParens'");
    }

    @Override
    public ExpressionParser manySumsWithBoxingParens() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'manySumsWithBoxingParens'");
    }

}
