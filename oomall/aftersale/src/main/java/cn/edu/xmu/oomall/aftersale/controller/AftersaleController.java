package cn.edu.xmu.oomall.aftersale.controller;

import cn.edu.xmu.oomall.aftersale.controller.dto.ConfirmDto;
import cn.edu.xmu.oomall.aftersale.service.AfterSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shops/{shopId}/aftersales")
public class AftersaleController {

    @Autowired
    private AfterSaleService afterSaleService;

    @PutMapping("/{id}/confirm")
    public Map<String, Object> confirm(@PathVariable Long shopId, 
                                       @PathVariable Long id, 
                                       @RequestBody ConfirmDto confirmDto) {
        afterSaleService.confirm(shopId, id, confirmDto.getConfirm(), confirmDto.getConclusion(), confirmDto.getType());
        
        Map<String, Object> result = new HashMap<>();
        result.put("errno", 0);
        result.put("errmsg", "成功");
        return result;
    }
}
