package cn.followtry.design.pattern.templatemethod;

import java.util.stream.Stream;

/**
 * 设置调查问卷
 * Created by followtry on 2017/6/6.
 */
public abstract class AbstractQuestionnaire {
  
  String question1(){
    return "你的名字是【"+name()+"】";
  }
  
  String question2(){
    return "你的性别是【"+sex()+"】";
  }
  String question3(){
    return "你的年龄是【"+age()+"】";
  }
  String question4(){
    return "你的职业是【"+jobs()+"】";
  }
  String question5(){
    return "你的住址是【"+addr()+"】";
  }
  
  abstract String name();
  abstract String sex();
  abstract String age();
  abstract String jobs();
  abstract String addr();
  
  public void answerAllQuestion(){
    System.out.println("开始答题");
    Stream.of(question1(),question2(),question3(),question4(),question5()).forEach(answer ->{
      System.out.println(answer);
    });
    System.out.println("答题完毕");
    System.out.println();
    
  }
  
}
