package cn.edu.xmu.oomall.service.service;

import cn.edu.xmu.oomall.service.controller.dto.ServiceOrderDto;
import cn.edu.xmu.oomall.service.dao.ServiceOrderDao;
import cn.edu.xmu.oomall.service.dao.bo.ServiceOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderDao serviceOrderDao;

    @Transactional
    public ServiceOrder createServiceOrder(Long shopId, Long aftersaleId, ServiceOrderDto dto) {
        log.info("Creating service order: shopId={}, aftersaleId={}, dto={}", shopId, aftersaleId, dto);
        
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setShopId(shopId);
        serviceOrder.setAftersaleId(aftersaleId);
        serviceOrder.setType(dto.getType());
        serviceOrder.setStatus(0); // 初始状态
        
        if (dto.getConsignee() != null) {
            serviceOrder.setConsignee(dto.getConsignee().getName());
            serviceOrder.setMobile(dto.getConsignee().getMobile());
            serviceOrder.setAddress(dto.getConsignee().getAddress());
            serviceOrder.setRegionId(dto.getConsignee().getRegionId());
        }
        
        return serviceOrderDao.save(serviceOrder);
    }
}
