package a07.e1;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;

public class TaskExecutorImpl implements TaskExecutor{

    @Override
    public <T> Optional<T> executeUntilConditionOrBound(Task<T> task, Predicate<T> resultCondition, int bound) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeUntilConditionOrBound'");
    }

    @Override
    public <T> Iterator<T> executeForever(Task<T> task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeForever'");
    }

}
