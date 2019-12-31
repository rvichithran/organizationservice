package cloud.microservices.spring.boot.organizationservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/organization")
public class OrgRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand
    @RequestMapping(value = "/license-mode-rt", method = RequestMethod.GET)
    public String license() {
        final ResponseEntity<String> restExchange = restTemplate.exchange(
                //"http://licensingservice/v1/licenses/mode",
                "http://zuulproxy/api/license/v1/licenses/mode",
                HttpMethod.GET,
                null, String.class);
        return restExchange.getBody();
    }

}
