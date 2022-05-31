package com.zeng.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.zeng.common.lang.Const;
import com.zeng.common.lang.Result;
import com.zeng.entity.SysUser;
import com.zeng.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;

@Slf4j
@RestController
public class AuthController extends BaseController{

    @Autowired
    Producer producer;

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/captcha")
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = producer.createText();
        String key = UUID.randomUUID().toString();

        // 为了测试
        key = "aaaaa";
        code = "11111";

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());

        // 存储到redis中
        redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);

        log.info("验证码 -- {} - {}", key, code);
        return Result.succ(
                MapUtil.builder()
                        .put("token", key)
                        .put("captchaImg", base64Img)
                        .build()
        );
    }


    /**
     * 获取用户信息接口
     * @param principal
     * @return
     */
    @GetMapping("/sys/userInfo")
    public Result userInfo(Principal principal){
        SysUser sysUser = sysUserService.getByUsername(principal.getName());

        return Result.succ(
                MapUtil.builder()
                    .put("id",sysUser.getId())
                    .put("username",sysUser.getUsername())
                    .put("avatar",sysUser.getAvatar())
                        .put("created",sysUser.getCreated())
                        .map()
        );
    }
}
