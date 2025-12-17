package cn.edu.xmu.oomall.aftersale.mapper.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "service-service", url = "${service-service.url:http://localhost:8082}")
public interface ServiceOrderClient {

    @PostMapping("/internal/shops/{shopId}/aftersales/{id}/serviceorders")
    String createServiceOrder(@PathVariable("shopId") Long shopId, 
                              @PathVariable("id") Long id, 
                              @RequestBody Object serviceOrderDto);
}
