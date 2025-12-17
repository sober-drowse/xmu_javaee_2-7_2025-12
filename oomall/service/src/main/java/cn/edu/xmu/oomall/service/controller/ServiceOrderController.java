package cn.edu.xmu.oomall.service.controller;

import cn.edu.xmu.oomall.service.controller.dto.ServiceOrderDto;
import cn.edu.xmu.oomall.service.dao.bo.ServiceOrder;
import cn.edu.xmu.oomall.service.service.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/internal/shops/{shopId}/aftersales")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @PostMapping("/{id}/serviceorders")
    public Map<String, Object> createServiceOrder(@PathVariable Long shopId, 
                                                  @PathVariable Long id, 
                                                  @RequestBody ServiceOrderDto dto) {
        ServiceOrder serviceOrder = serviceOrderService.createServiceOrder(shopId, id, dto);
        
        Map<String, Object> result = new HashMap<>();
        result.put("errno", 0);
        result.put("errmsg", "成功");
        result.put("data", serviceOrder);
        return result;
    }
}
