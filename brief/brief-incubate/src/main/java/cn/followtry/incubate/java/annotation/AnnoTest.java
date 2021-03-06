package cn.followtry.incubate.java.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 用来测试注解及注解上的注解，这样可以根据注解上的注解判断当前注解是否可以生效.
 *
 * <p>如@Controller和@Component的关系.
 *
 * <p>created by followtry on 2017/03/15
 */
public class AnnoTest {

  /**
   * main.
   */
  public static void main(String[] args) throws IllegalAccessException {
    getAllStairAnnnotations(UserController.class);

    getAnnotationOnAnno(UserController.class);
  }

  /**
   * 获取注解的域和值.
   */
  public static void getAnnoFieldAndValue(Annotation anno) throws IllegalAccessException {
    Class<? extends Annotation> clazz = anno.getClass();
    Field[] fields = clazz.getDeclaredFields();
    if (fields != null && fields.length > 0) {
      for (Field field : fields) {
        field.setAccessible(true);
        System.out.println("\t\t" + field.getName() + ":" + field.get(anno));
      }
    }
  }

  /**
   * 获取注解上的注解.
   */
  public static void getAnnotationOnAnno(Class clazz) throws IllegalAccessException {
    List<Annotation> stairAnnnotations = getAllStairAnnnotations(clazz);
    if (stairAnnnotations != null && stairAnnnotations.size() > 0) {
      for (Annotation anno : stairAnnnotations) {
        Class<? extends Annotation> annotationType = anno.annotationType();
        Annotation[] secondAnnos = annotationType.getAnnotations();
        for (Annotation annotation : secondAnnos) {
          System.out.println("\t" + annotation.toString());
          getAnnoFieldAndValue(annotation);

        }
      }
    }
  }

  /**
   * 获取指定类上的一级注解.
   */
  public static List<Annotation> getAllStairAnnnotations(Class clazz) throws
          IllegalAccessException {
    Annotation[] annotations = clazz.getAnnotations();
    List<Annotation> annos = new ArrayList<>();
    for (Annotation anno : annotations) {
      System.out.println(anno.toString());
      getAnnoFieldAndValue(anno);
      annos.add(anno);
    }
    return annos;

  }
}
