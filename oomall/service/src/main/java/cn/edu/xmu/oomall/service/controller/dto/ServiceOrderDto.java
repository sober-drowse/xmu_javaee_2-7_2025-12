package cn.edu.xmu.oomall.service.controller.dto;

import lombok.Data;

@Data
public class ServiceOrderDto {
    private Integer type;
    private Consignee consignee;

    @Data
    public static class Consignee {
        private String name;
        private String mobile;
        private Long regionId;
        private String address;
    }
}
