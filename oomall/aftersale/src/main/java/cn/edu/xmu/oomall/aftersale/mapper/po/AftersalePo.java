package cn.edu.xmu.oomall.aftersale.mapper.po;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "aftersale")
public class AftersalePo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shopId;
    
    // 0:换货, 1:退货, 2:维修
    private Integer type;
    
    // 0:待审核, 1:审核通过, 2:审核不通过
    private Integer status;
    
    private String reason;
    private String conclusion;
    
    private Long customerId;
    private Long orderId;
    private Long orderItemId;
    private Integer quantity;
    
    private String consignee;
    private String mobile;
    private String address;
    private Long regionId;
    
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
