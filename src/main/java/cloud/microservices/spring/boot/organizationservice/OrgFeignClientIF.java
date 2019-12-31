package cloud.microservices.spring.boot.organizationservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("licensingservice")
public interface OrgFeignClientIF {

    @RequestMapping(method= RequestMethod.GET, value="/v1/licenses/mode")
    String license();
}
