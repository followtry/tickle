package cn.followtry.design.pattern.observer;

/**
 * Created by followtry on 2017/6/5.
 */
public class ObServerMainTest {
  /** main. */
  public static void main(String[] args) {
    //出现语文老师
    ChineseTeacher chineseTeacher = new ChineseTeacher();
    MathTeacher mathTeacher = new MathTeacher();
    
    //学生认可自己的老师
    Student zhangsan = new Student("张三",chineseTeacher);
    Student lisi = new Student("李四",chineseTeacher);
    Student wangwu = new Student("王五",chineseTeacher);
    Student maliu = new Student("马六",chineseTeacher);
    
    //老师确认自己的学生
    chineseTeacher.attach(zhangsan);
    chineseTeacher.attach(lisi);
    chineseTeacher.attach(wangwu);
    chineseTeacher.attach(maliu);
  
    mathTeacher.attach(zhangsan);
    mathTeacher.attach(lisi);
    mathTeacher.attach(wangwu);
    mathTeacher.attach(maliu);
    
    //老师发出上课的指示并通知所有人
    chineseTeacher.setInstruction("现在开始上语文课!");
    mathTeacher.setInstruction("下了语文课后，我布置下数学作业！");
    chineseTeacher.sendNotify();
    mathTeacher.sendNotify();
  }
}
