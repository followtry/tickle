/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package cn.followtry.service.scheduler.core.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractBatchThroughputDataFlowElasticJob;

import cn.followtry.service.scheduler.bean.ScheduleJob;
import cn.followtry.service.scheduler.util.PrintContext;

public class BatchThroughputDataFlowJob extends AbstractBatchThroughputDataFlowElasticJob<ScheduleJob> {
    
	 private PrintContext printContext = new PrintContext(BatchThroughputDataFlowJob.class);
	
	 private Logger LOG = LoggerFactory.getLogger(BatchThroughputDataFlowJob.class);
	 
    public boolean isStreamingProcess() {
        return true;
    }

    public ExecutorService getExecutorService() {
        return Executors.newFixedThreadPool(10);
    }

	public int processData(JobExecutionMultipleShardingContext shardingContext, List<ScheduleJob> data) {
		printContext.printProcessDataMessage(data);
		LOG.info("成功被调用");
        int successCount = 1;
        return successCount;
	}

	public List<ScheduleJob> fetchData(JobExecutionMultipleShardingContext shardingContext) {
		printContext.printFetchDataMessage(shardingContext.getShardingItems());
        return new ArrayList<ScheduleJob>();
	}
}
