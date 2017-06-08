package cn.followtry.incubate.java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 操作csv文件 Created by jingzz on 2017/3/13.
 */
public class CSVFileOper {

  private static Random random = new Random();

  public static void main(String[] args) throws IOException {
    String basePath = "D:" + File.separator;
    String fileName = basePath + "ofo-code.csv";
    System.out.println("路径：" + fileName);
    readFile(fileName);
    String outputCode = "123456";
    String outputValue = "987";
    writeFile(fileName,outputCode,outputValue);
    readFile(fileName);
    System.out.println("退出");
  }

  private static void writeFile(String fileName,String outputCode,String outputValue) throws
          IOException {
    System.out.println("写操作开始");
    FileWriter writer = new FileWriter(fileName,true);
    int i = 0;
    while (i < 10) {
      writer.write(System.getProperty("line.separator"));
      writer.write(outputCode + random.nextInt(10) + "," + outputValue + random.nextInt(10));
      i++;
    }
    writer.close();
    System.out.println("写操作完成");
  }

  private static void readFile(String fileName) throws IOException {
    System.out.println("读操作开始");
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String line = null;
    while ((line = reader.readLine()) != null) {
      String[] values = line.split(",");
      if (values.length == 2) {
        System.out.println("单车编号：【" + values[0] + "】,密码：【" + values[1] + "]");
      }
    }

    reader.close();
    System.out.println("读操作完成");
  }

}
