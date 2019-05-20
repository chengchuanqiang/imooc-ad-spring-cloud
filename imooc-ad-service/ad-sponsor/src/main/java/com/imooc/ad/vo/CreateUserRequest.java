package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 10:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String username;

    public boolean validate() {
        return !StringUtils.isEmpty(username);
    }
}
