package net.mobo.clockin.schedule;


import net.mobo.clockin.utils.WXWorkMessageSendUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 10782
 */
@Component
public class ClockInSchedule {

    @Resource
    private WXWorkMessageSendUtils wxWorkMessageSendUtils;

    @Scheduled(cron = "0 10 15 * * ?")
    public void clockIn(){
        wxWorkMessageSendUtils.sendErrorMarkDownMessage("测试测试");
    }
}
