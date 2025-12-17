package cn.edu.xmu.oomall.aftersale.controller.dto;

import lombok.Data;

@Data
public class ConfirmDto {
    private Boolean confirm;
    private String conclusion;
    private Integer type;
}
