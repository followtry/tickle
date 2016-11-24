/**
 * 
 */
package cn.followtry.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author jingzz
 * @time 2016年11月24日 上午11:10:02
 * @name brief-quartz/cn.followtry.quartz.AlarmServiceImpl
 * @since 2016年11月24日 上午11:10:02
 */
@Service
public class AlarmServiceImpl implements AlarmService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AlarmServiceImpl.class);

	@Override
	@Scheduled(cron="0/5 * * * * ?")
	public void myAlarm() {
		LOGGER.info("启动我的闹钟");
	}

}
