package schedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TimerScheduler {
	public static void main(String[] args) throws Exception{
		String cronExpression = "0/5 * * * * ?";
		
		// 1. 스케쥴러 생성
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		// 2. Job 등록 (해야할일)
		JobDetail job = JobBuilder.newJob(TimerJob.class).withIdentity("timerJob", "grp1").build();
		// 3. Trigger 설정 (호출 횟수 빈도)
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("timerTrigger", "grp1")
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
				.build();
		// 4. 스케쥴러에 Job, Trigger 등록
		scheduler.scheduleJob(job, trigger);
		
		// 5. 테스트 용 60초간 동작후 스케쥴러 마무리
		scheduler.start();
		Thread.sleep(60 * 1000);
		scheduler.shutdown();
	}
}
