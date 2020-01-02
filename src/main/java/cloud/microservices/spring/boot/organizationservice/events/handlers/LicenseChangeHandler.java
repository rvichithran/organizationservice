package cloud.microservices.spring.boot.organizationservice.events.handlers;

import cloud.microservices.spring.boot.organizationservice.events.CustomChannels;
import cloud.microservices.spring.boot.organizationservice.events.model.LicenseChangeModel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomChannels.class)
public class LicenseChangeHandler {

    @StreamListener("inboundLicenseChanges")
    public void loggerSink(LicenseChangeModel licenseChange) {
        System.out.println(" License changed "+licenseChange.getAction()+" "+licenseChange.getLicenseId());
    }

}
