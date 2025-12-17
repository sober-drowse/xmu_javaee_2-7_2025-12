package cn.edu.xmu.oomall.aftersale.dao.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.edu.xmu.oomall.aftersale.mapper.po.AftersalePo;

@Data
@NoArgsConstructor
public class Aftersale {
    private Long id;
    private Long shopId;
    private Integer type;
    private Integer status;
    private String reason;
    private String conclusion;
    private String consignee;
    private String mobile;
    private String address;
    private Long regionId;

    public Aftersale(AftersalePo po) {
        this.id = po.getId();
        this.shopId = po.getShopId();
        this.type = po.getType();
        this.status = po.getStatus();
        this.reason = po.getReason();
        this.conclusion = po.getConclusion();
        this.consignee = po.getConsignee();
        this.mobile = po.getMobile();
        this.address = po.getAddress();
        this.regionId = po.getRegionId();
    }
}
