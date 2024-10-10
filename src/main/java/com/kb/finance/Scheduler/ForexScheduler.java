package com.kb.finance.Scheduler;

import com.kb.finance.service.ForexSchedulerService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ForexScheduler {

    private final ForexSchedulerService forexSchedulerService;

    public ForexScheduler(ForexSchedulerService forexSchedulerService) {
        this.forexSchedulerService = forexSchedulerService;
    }

    static int counter = 0;
        @Scheduled(cron = "0 0 0 * * ?")  // 매일 00:00에 실행
//    @Scheduled(cron = "0 0/1 * * * ?")  // 매 1분마다 실행
    public void runForexJob() {
        try {
            counter++;
            if(counter % 2 == 0){
                forexSchedulerService.processForexData();
                System.out.println("실행" + counter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
