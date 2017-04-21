package cn.followtry.hadoop.demo.v2.mr;

import cn.followtry.hadoop.demo.hdfs.HDFSOper;
import cn.followtry.hadoop.demo.util.DebugConfUtil;
import java.io.IOException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取任意两个人的共同朋友.
 *
 * <p>双向都是好友即A是B的好友，那么B也是A的好友
 *
 * @author jingzz
 * @version 0.0.3
 * @since 0.0.3
 */
public class BidirectionalCommonFriends {


  private static Logger LOGGER = LoggerFactory.getLogger(BidirectionalCommonFriends.class);

  static class CFMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>
            .Context context) throws IOException, InterruptedException {

      StringTokenizer valueLine = new StringTokenizer(value.toString());

      // 获取每一行的自己
      String owner = null;
      if (valueLine.hasMoreTokens()) {
        owner = valueLine.nextToken();
      }

      // 获取每一行人员的朋友
      //使用treeset可对元素进行排序
      Set<Object> set = new TreeSet<Object>();
      while (valueLine.hasMoreTokens()) {
        set.add(valueLine.nextToken());
      }

      // A的所有朋友两两相交的共同好友中肯定有A
      Object[] friends = set.toArray();
      /*
			 * a b c d e
			 * 
			 * ab ac ad ae bc bd be cd ce de
			 */
      for (int i = 0; i < friends.length - 1; i++) {
        for (int j = i + 1; j < friends.length; j++) {
          String twoFriend = (String) friends[i] + (String) friends[j];
          // A的任意两个好友的共同好友都有A
          context.write(new Text(twoFriend), new Text(owner));
        }
      }
    }
  }

  static class CFReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>
            .Context context) throws IOException, InterruptedException {
      StringBuilder cf = new StringBuilder();
      for (Text friend : values) {
        cf.append(friend.toString());
        cf.append(":");
      }
      context.write(key, new Text(cf.toString()));
    }
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException,
          InterruptedException {
    Configuration conf = new Configuration();
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
    if (otherArgs == null || otherArgs.length < 2) {
      LOGGER.error("用法：参数最少为两个，最后一个为输出目录，其他为输入路径");
      System.exit(-1);
    }

    int inputNum = otherArgs.length - 1;
    StringBuilder inputPaths = new StringBuilder();
    for (int i = 0; i < inputNum; i++) {
      inputPaths.append(otherArgs[i]);
      if (i < inputNum - 1) {
        inputPaths.append(",");
      }
    }

    String outputPath = otherArgs[inputNum];

    HDFSOper.rmExistsOutputDir(outputPath);

    DebugConfUtil.confByOS(conf);

    Job job = Job.getInstance(conf, "Bidirectional Common Friend num " + RandomUtils.nextInt());
    job.setJarByClass(BidirectionalCommonFriends.class);

    job.setMapperClass(CFMapper.class);
    job.setReducerClass(CFReducer.class);

    job.setOutputValueClass(Text.class);
    job.setOutputKeyClass(Text.class);

    FileInputFormat.setInputPaths(job, inputPaths.toString());
    FileOutputFormat.setOutputPath(job, new Path(outputPath));

    System.exit(job.waitForCompletion(true) ? 0 : 1);

  }

}
