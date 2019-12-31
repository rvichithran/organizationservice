package cloud.microservices.spring.boot.organizationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/organization")
public class OrgDiscoveryClientController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/license-mode-dc", method = RequestMethod.GET)
    public String license() {
        final RestTemplate restTemplate = new RestTemplate();
        final List<ServiceInstance> listOfServices = discoveryClient.getInstances("LICENSINGSERVICE");
        final String serviceUri = String.format("%s/v1/licenses/mode",
                listOfServices.get(0).getUri().toString());
        final ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, String.class);
        return restExchange.getBody();
    }

}
