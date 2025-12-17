package cn.edu.xmu.oomall.service.dao;

import cn.edu.xmu.oomall.service.dao.bo.ServiceOrder;
import cn.edu.xmu.oomall.service.mapper.ServiceOrderPOMapper;
import cn.edu.xmu.oomall.service.mapper.po.ServiceOrderPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceOrderDao {

    @Autowired
    private ServiceOrderPOMapper serviceOrderPOMapper;

    public ServiceOrder save(ServiceOrder serviceOrder) {
        ServiceOrderPo po = new ServiceOrderPo();
        po.setShopId(serviceOrder.getShopId());
        po.setAftersaleId(serviceOrder.getAftersaleId());
        po.setType(serviceOrder.getType());
        po.setStatus(serviceOrder.getStatus());
        po.setConsignee(serviceOrder.getConsignee());
        po.setMobile(serviceOrder.getMobile());
        po.setAddress(serviceOrder.getAddress());
        po.setRegionId(serviceOrder.getRegionId());
        
        ServiceOrderPo savedPo = serviceOrderPOMapper.save(po);
        return new ServiceOrder(savedPo);
    }
}
