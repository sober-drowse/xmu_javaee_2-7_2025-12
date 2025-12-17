package cn.edu.xmu.oomall.service.dao.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.edu.xmu.oomall.service.mapper.po.ServiceOrderPo;

@Data
@NoArgsConstructor
public class ServiceOrder {
    private Long id;
    private Long shopId;
    private Long aftersaleId;
    private Integer type;
    private Integer status;
    private String consignee;
    private String mobile;
    private String address;
    private Long regionId;

    public ServiceOrder(ServiceOrderPo po) {
        this.id = po.getId();
        this.shopId = po.getShopId();
        this.aftersaleId = po.getAftersaleId();
        this.type = po.getType();
        this.status = po.getStatus();
        this.consignee = po.getConsignee();
        this.mobile = po.getMobile();
        this.address = po.getAddress();
        this.regionId = po.getRegionId();
    }
}
