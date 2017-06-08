package cn.followtry.incubate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import sun.misc.Unsafe;

/**
 * Created by followtry on 2017/4/26. ${END}
 */
public class UnSafeTest {
  /** main. */
  public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {


    Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
    unsafeField.setAccessible(true);
    Unsafe unsafe = (Unsafe)unsafeField.get(null);


    Target target = new Target();
    Field age = Target.class.getDeclaredField("age");
    Integer ageValue = (Integer)age.get(target);
    System.out.println("原始值：" + ageValue);

    long offset = unsafe.objectFieldOffset(age);

    //修改值时，int和Integer是不同的类型，一个是对象，一个是基本类型。
    boolean b = unsafe.compareAndSwapObject(target,offset,ageValue,10);
    System.out.println("是否已经修改：" + b);

    Integer anInt = (Integer)age.get(target);
    System.out.println("修改之后的值为："+anInt);
  }

  static class Target {
    Integer age = 24;
    String name = "jingzz";
  }
}
