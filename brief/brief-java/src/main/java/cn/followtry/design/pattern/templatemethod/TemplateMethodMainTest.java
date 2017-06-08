package cn.followtry.design.pattern.templatemethod;

/**
 * Created by followtry on 2017/6/6.
 */
public class TemplateMethodMainTest {
  /** main. */
  public static void main(String[] args) {
    Informant1 informant1 = new Informant1();
    Informant2 informant2 = new Informant2();
    Informant3 informant3 = new Informant3();
    informant1.answerAllQuestion();
    informant2.answerAllQuestion();
    informant3.answerAllQuestion();
  }
}
