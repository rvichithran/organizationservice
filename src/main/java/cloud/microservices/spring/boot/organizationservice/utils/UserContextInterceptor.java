package cloud.microservices.spring.boot.organizationservice.utils;

import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders httpHeaders = httpRequest.getHeaders();
        httpHeaders.add(UserContext.CORRELATION_ID, UserContextHolder.get().getCorrelationId());
        httpHeaders.add(UserContext.AUTH_TOKEN, UserContextHolder.get().getAuthToken());
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
