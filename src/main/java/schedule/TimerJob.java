package schedule;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimerJob implements Job{
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("{}", new Date());
	}
}
