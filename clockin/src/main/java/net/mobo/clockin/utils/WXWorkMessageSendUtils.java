package net.mobo.clockin.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Async("messageSendExecutor")
public class WXWorkMessageSendUtils {

    private final String ROBOT_URL_PREFIX = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=b2956ad5-f17b-42cf-88c9-7c73bcbfe8d4";


    public void sendErrorMarkDownMessage(String msg){
            WeChatMessageUtil weChatMessageUtil = WeChatMessageUtil.errMarkDownBuilder("测试指令",msg).build();
            String data = JSON.toJSONString(weChatMessageUtil);
            String rtn = HttpUtil.post(ROBOT_URL_PREFIX, data);
    }

}
