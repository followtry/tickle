package cn.followtry.incubate;

import cn.followtry.java.ext.processor.anno.TestAnno;

/**
 * Created by followtry on 2017/4/28.
 */
public class ProcessorTest {


  @TestAnno(value = "jingzz",name = "荆中志")
  public static String msg = "1232";

  /** main. */
  public static void main(String[] args) {
    System.out.println("开始ss");
    System.out.println(msg);
    System.out.println("结束");
  }
}
