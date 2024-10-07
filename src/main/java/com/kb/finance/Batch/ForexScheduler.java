package com.kb.finance.Batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Semaphore;

@Component
@EnableScheduling
public class ForexScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job forexJob;

    private final Object lock = new Object();
    private final Semaphore semaphore = new Semaphore(1);


//    @Scheduled(cron = "0 0 0 * * ?")  // 매일 00:00에 실행
//    @Scheduled(cron = "*/2 * * * * ?")  // 매 20초마다 실행
//    public void runExchangeJob() {
//        try {
//            jobLauncher.run(forexJob, new org.springframework.batch.core.JobParameters());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Scheduled(cron = "*/2 * * * * ?")  // 매 20초마다 실행
//    public void runForexJob() {
//        synchronized (lock) {
//            try {
//                // 현재 실행 중인 Job이 있는지 확인
//                List<JobExecution> runningJobs = jobRepository.getJobExecutions(forexJob.getName());
//                boolean isRunning = runningJobs.stream().anyMatch(JobExecution::isRunning);
//
//                // 실행 중인 Job이 없을 때만 새 Job 실행
//                if (!isRunning) {
//                    jobLauncher.run(forexJob, new JobParameters());
//                } else {
//                    System.out.println("다른 배치 작업이 실행 중입니다. 대기 중...");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    @Scheduled(cron = "*/2 * * * * ?")  // 매 20초마다 실행
    public void runForexJob() {
        try {
            if (semaphore.tryAcquire()) {
                jobLauncher.run(forexJob, new JobParameters());
            } else {
                System.out.println("다른 배치 작업이 실행 중입니다. 대기 중...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
