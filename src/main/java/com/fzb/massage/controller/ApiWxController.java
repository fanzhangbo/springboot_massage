package com.fzb.massage.controller;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@RestController
@RequestMapping("api/wx")
public class ApiWxController {

    @Autowired
    public RestTemplate restTemplate;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Map<String, Object> wxLogin(@RequestParam("code") String code) {

        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);

        String appId = "wx91ff7f2931a77592";
        String secret = "4b2e08c622c45b2b37fe7e1ce08e1689";

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId +
                "&secret=" + secret +
                "&js_code=" + code +
                "&grant_type=authorization_code";

        String jsonData = this.restTemplate.getForObject(url, String.class);
        if (StringUtils.contains(jsonData, "errcode")) {
            // 校验出错了
            result.put("code", 500);
            result.put("msg", "登录失败");
            return result;
        }

        String md5Key = DigestUtils.md5Hex("massage_" + jsonData);
        result.put("ticket", md5Key);

        return result;
    }

}
