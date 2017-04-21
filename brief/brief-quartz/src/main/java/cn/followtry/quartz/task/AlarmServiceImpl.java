package cn.followtry.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Alarm服务实现类.
 * @author jingzz
 * @since 2016年11月24日 上午11:10:02
 */
@Service
public class AlarmServiceImpl implements AlarmService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AlarmServiceImpl.class);

  @Override
  @Scheduled(cron = "0/5 * * * * ?")
  public void myAlarm() {
    LOGGER.info("启动我的闹钟");
  }

  @Override
  @Scheduled(cron = "0/4 * * * * ?")
  public void callMe() {
    LOGGER.info("懒猪，快点起床！");
  }

}
