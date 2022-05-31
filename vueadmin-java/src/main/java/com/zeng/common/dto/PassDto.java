package com.zeng.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PassDto implements Serializable {

    @NotBlank(message = "新密码不能为空")
    public String password;

    @NotBlank(message = "旧密码不能为空")
    public String currentPass;
}
