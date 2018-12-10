package com.specialyang.secondstokill.controller.vo;

import com.specialyang.secondstokill.validate.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by Fan Yang in 2018/12/6 8:56 AM.
 */
@Data
public class LoginVo {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
