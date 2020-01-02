package cloud.microservices.spring.boot.organizationservice.hysterix;

import cloud.microservices.spring.boot.organizationservice.utils.UserContext;
import cloud.microservices.spring.boot.organizationservice.utils.UserContextHolder;

import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<V> implements Callable<V> {

    private Callable<V> callable;
    private UserContext userContext;

    public DelegatingUserContextCallable(Callable<V> callable, UserContext userContext) {
        this.callable = callable;
        this.userContext = userContext;
    }

    @Override
    public V call() throws Exception {
        UserContextHolder.get().setCorrelationId(userContext.getCorrelationId());
        UserContextHolder.get().setAuthToken(userContext.getAuthToken());
        return callable.call();
    }
}
