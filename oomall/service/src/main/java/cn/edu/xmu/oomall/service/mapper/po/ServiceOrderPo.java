package cn.edu.xmu.oomall.service.mapper.po;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "service_order")
public class ServiceOrderPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shopId;
    private Long aftersaleId;
    
    // 0:上门, 1:寄件, 2:线下
    private Integer type;
    
    private Integer status;
    
    private String consignee;
    private String mobile;
    private String address;
    private Long regionId;
    
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
