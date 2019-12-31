package cloud.microservices.spring.boot.organizationservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/organization")
public class OrgFeignClient {

    @Autowired
    private OrgFeignClientIF orgFeignClientIF;

    @HystrixCommand(
            fallbackMethod = "alternateRoute",
            threadPoolKey = "licensesByOrg",
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "1"),
                @HystrixProperty(name = "maxQueueSize", value = "-1")
            })
    @RequestMapping(value = "/license-mode-fc", method = RequestMethod.GET)
    public String license() {
        return orgFeignClientIF.license();
    }

    public String alternateRoute() {
        return "Calling fallback method";
    }
}
