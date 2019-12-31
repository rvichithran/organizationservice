package cloud.microservices.spring.boot.organizationservice.utils;

public class UserContext {

    public static final String CORRELATION_ID = "tmx-correlation-id";

    private String correlationId;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
