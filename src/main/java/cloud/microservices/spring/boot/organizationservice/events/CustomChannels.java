package cloud.microservices.spring.boot.organizationservice.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {

    @Input("inboundLicenseChanges")
    SubscribableChannel license();

}
