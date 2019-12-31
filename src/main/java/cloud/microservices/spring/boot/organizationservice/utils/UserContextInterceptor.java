package cloud.microservices.spring.boot.organizationservice.utils;

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
        System.out.println(" Adding correlation id "+UserContextHolder.get().getCorrelationId());
        httpHeaders.add(UserContext.CORRELATION_ID, UserContextHolder.get().getCorrelationId());
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
