package com.wsw.cloudtest.Schedule;

import com.wsw.cloudtest.domain.LocalMessage;
import com.wsw.cloudtest.rabbitmq.RabbitService;
import com.wsw.cloudtest.service.LocalMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 15:38 2020/11/2
 * @Description: 定时器 检查未成功发送的消息，进行重新发送
 */
@Component
public class CheckMessageSchedule {
    @Autowired
    private LocalMessageService localMessageService;
    @Autowired
    private RabbitService rabbitService;

    /**
     * 兜底方案: 必须保证每个消息都发送到MQ消费端进行消费，保证数据最终一致
     * 每隔30秒检查本地消息表没有发送成功的消息，进行重试再次发送到MQ
     */
    @Scheduled(fixedDelay = 1000 * 30L)
    public void checkMessage(){
        List<LocalMessage> failMessages = localMessageService.selectFailMessage(0);
        if (null != failMessages && !failMessages.isEmpty()){
            for (LocalMessage failMessage : failMessages) {
                rabbitService.sendMessage(failMessage.getLocalMessageId(), failMessage.getMessage());
            }
        }
    }
}
