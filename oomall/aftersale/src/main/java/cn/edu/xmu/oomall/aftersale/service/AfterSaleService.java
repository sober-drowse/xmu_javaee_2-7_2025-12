package cn.edu.xmu.oomall.aftersale.service;

import cn.edu.xmu.oomall.aftersale.dao.AftersaleDao;
import cn.edu.xmu.oomall.aftersale.dao.bo.Aftersale;
import cn.edu.xmu.oomall.aftersale.mapper.openfeign.ServiceOrderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AfterSaleService {

    @Autowired
    private AftersaleDao aftersaleDao;

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    @Transactional
    public void confirm(Long shopId, Long id, Boolean confirm, String conclusion, Integer type) {
        log.info("Audit aftersale: shopId={}, id={}, confirm={}, conclusion={}, type={}", shopId, id, confirm, conclusion, type);
        
        Aftersale aftersale = aftersaleDao.findById(id);
        if (aftersale == null) {
            throw new RuntimeException("Aftersale not found");
        }

/*        // 新增：检查状态，防止重复审核
        if (aftersale.getStatus() != 0) {
            throw new RuntimeException("Aftersale has been audited");
        }*/

        if (confirm) {
            aftersale.setStatus(1); // 审核通过
            aftersale.setConclusion(conclusion);
            aftersaleDao.save(aftersale);

            // 如果是维修类型 (type == 2)，调用服务模块创建服务单
            if (type != null && type == 2) {
                log.info("Creating service order for aftersale id: {}", id);
                Map<String, Object> serviceOrderDto = new HashMap<>();
                serviceOrderDto.put("type", 0); // 默认上门
                
                Map<String, Object> consignee = new HashMap<>();
                consignee.put("name", aftersale.getConsignee());
                consignee.put("mobile", aftersale.getMobile());
                consignee.put("address", aftersale.getAddress());
                consignee.put("regionId", aftersale.getRegionId());
                
                serviceOrderDto.put("consignee", consignee);

                try {
                    serviceOrderClient.createServiceOrder(shopId, id, serviceOrderDto);
                    log.info("Service order created successfully");
                } catch (Exception e) {
                    log.error("Failed to create service order", e);
                    throw new RuntimeException("Failed to create service order");
                }
            }
        } else {
            aftersale.setStatus(2); // 审核不通过
            aftersale.setConclusion(conclusion);
            aftersaleDao.save(aftersale);
        }
    }
}
