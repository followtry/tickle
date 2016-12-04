package cn.followtry.hadoop.demo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.StringUtils;

public class HelloHadoop {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    static enum CountersEnum { INPUT_WORDS }

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    private boolean caseSensitive;
    private Set<String> patternsToSkip = new HashSet<String>();

    private Configuration conf;
    private BufferedReader fis;

    @Override
    public void setup(Context context) throws IOException,
        InterruptedException {
      conf = context.getConfiguration();
      caseSensitive = conf.getBoolean("wordcount.case.sensitive", true);
      if (conf.getBoolean("wordcount.skip.patterns", true)) {
        URI[] patternsURIs = Job.getInstance(conf).getCacheFiles();
        for (URI patternsURI : patternsURIs) {
          Path patternsPath = new Path(patternsURI.getPath());
          String patternsFileName = patternsPath.getName().toString();
          parseSkipFile(patternsFileName);
        }
      }
    }

    private void parseSkipFile(String fileName) {
      try {
        fis = new BufferedReader(new FileReader(fileName));
        String pattern = null;
        while ((pattern = fis.readLine()) != null) {
          patternsToSkip.add(pattern);
        }
      } catch (IOException ioe) {
        System.err.println("Caught exception while parsing the cached file '"
            + StringUtils.stringifyException(ioe));
      }
    }

    @Override
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      String line = (caseSensitive) ?
          value.toString() : value.toString().toLowerCase();
      for (String pattern : patternsToSkip) {
        line = line.replaceAll(pattern, "");
      }
      StringTokenizer itr = new StringTokenizer(line);
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
        context.write(word, one);
        Counter counter = context.getCounter(CountersEnum.class.getName(),
            CountersEnum.INPUT_WORDS.toString());
        counter.increment(1);
      }
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }

private static final String preifx_path = "webhdfs://h2m1:50070/webhdfs/v1/user/root";
private static final String output_path = preifx_path+"/input?op=open";
private static final String input_path = preifx_path+"/output?op=create";
private static final String target_path = preifx_path+"/";
private static String path = "/user/root";


  public static void main(String[] args) throws Exception {
	  
      // 将本地文件上传到hdfs。
	  Configuration config = new Configuration();
      FileInputStream fis = new FileInputStream(new File("D:\\hello.java"));// 读取本地文件
      FileSystem fs = FileSystem.get(URI.create("webhdfs://h2m1:50070" ), config);
	  listFiles(fs,path);	
	//    Configuration conf = new Configuration();
//
//    Job job = Job.getInstance(conf, "word count");
//    job.setJarByClass(HelloHadoop.class);
//    job.setMapperClass(TokenizerMapper.class);
//    job.setCombinerClass(IntSumReducer.class);
//    job.setReducerClass(IntSumReducer.class);
//    job.setOutputKeyClass(Text.class);
//    job.setOutputValueClass(IntWritable.class);
//
//    List<String> otherArgs = new ArrayList<String>();
//
//    FileInputFormat.addInputPath(job, new Path(input_path));
//    FileOutputFormat.setOutputPath(job, new Path(output_path));
//
//    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }


private static void listFiles(FileSystem fs,String path) throws FileNotFoundException, IOException {
	String tempPath =path;
	FileStatus[] listStatus = fs.listStatus(new Path(tempPath));
	for (FileStatus f : listStatus) {
	    String dir = f.isDirectory() ? "目录":"文件";
	    String name = f.getPath().getName();
	    String realPath = f.getPath().toString();
	    System.out.println(dir+":"+name);
	    System.out.println("路径:"+realPath);
	    System.out.println("访问时间："+f.getAccessTime());
	    System.out.println("块大小:"+f.getBlockSize());
	    System.out.println("所属组:"+f.getGroup());
	    System.out.println("长度:"+f.getLen());
	    System.out.println("修改时间:"+f.getModificationTime());
	    System.out.println("所有者:"+f.getOwner());
	    System.out.println("权限:"+f.getPermission());
	    System.out.println("副本:"+f.getReplication());
	    System.out.println("============================================================");
	    if (f.isDirectory()) {
			listFiles(fs,realPath);
		}
//	        System.out.println(f.getSymlink());
	  }
}
}
