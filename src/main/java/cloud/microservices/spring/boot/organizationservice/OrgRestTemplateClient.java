package cloud.microservices.spring.boot.organizationservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
                //"http://licensingservice/v1/license/mode",
                "http://zuulproxy/api/license/v1/license/mode",
                HttpMethod.GET,
                null, String.class);
        return restExchange.getBody();
    }


    @HystrixCommand
    @RequestMapping(value = "/find-license/{id}", method = RequestMethod.GET)
    public String findLicense(@PathVariable("id") String licenseId) {
        final ResponseEntity<String> restExchange = restTemplate.exchange(
                //"http://licensingservice/v1/license/mode",
                "http://zuulproxy/api/license/v1/license/id/"+licenseId,
                HttpMethod.GET,
                null, String.class);
        return restExchange.getBody();
    }

    @HystrixCommand
    @RequestMapping(value = "/delete-license/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicense(@PathVariable("id") String licenseId) {
        final ResponseEntity<String> restExchange = restTemplate.exchange(
                //"http://licensingservice/v1/license/mode",
                "http://zuulproxy/api/license/v1/license/id/"+licenseId,
                HttpMethod.DELETE,
                null, String.class);
    }




}
