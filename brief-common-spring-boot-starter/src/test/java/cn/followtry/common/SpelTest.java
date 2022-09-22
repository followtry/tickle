package cn.followtry.common;

import cn.followtry.common.utils.ValueUtil;
import org.junit.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author followtry
 * @since 2021/10/13 8:54 下午
 */
public class SpelTest {

    private SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    private static final LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Test
    public void testSpelRespBool() {
        User user = new User();
        user.setMale(true);

        Expression expression = spelExpressionParser.parseExpression("male");
        Boolean value = expression.getValue(user, Boolean.class);
        System.out.println(value);
        Assert.isTrue(Objects.equals(value, true), "预期为true，实际为false");
    }

    @Test
    public void testSpelRespInt() {
        User user = new User();
        user.setAge(22);

        Expression expression = spelExpressionParser.parseExpression("age == 22");
        Boolean value = expression.getValue(user, Boolean.class);
        System.out.println(value);


        Expression expression2 = spelExpressionParser.parseExpression("age");
        Integer value2 = expression2.getValue(user, Integer.class);
        System.out.println(value2);

        Assert.isTrue(Objects.equals(value, true), "预期为true，实际为false");
    }

    @Test
    public void testSpelReqParam() throws NoSuchMethodException {
        Method method = this.getClass().getDeclaredMethod("hello", String.class, User.class);
        String[] parameterNames = discoverer.getParameterNames(method);
        User user = new User();
        user.setName("zhangSan");
        user.setAge(23);
        user.setMale(true);
        Object[] argValues = {"zhangSan", user};
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], argValues[i]);
        }

        Expression expression = spelExpressionParser.parseExpression("#user.age == 23");
        String value = expression.getValue(context,String.class);
        System.out.println(value);
    }

    @Test
    public void testSpelReqParam02() throws NoSuchMethodException {
        Method method = this.getClass().getDeclaredMethod("hello", String.class, User.class);
        User user = new User();
        user.setName("zhangSan");
        user.setAge(23);
        user.setMale(true);
        Object[] argValues = {"zhangSan", user};

        Object userAge = ValueUtil.requestValue(method, argValues, "#user.age");
        System.out.println(userAge);

        Object name = ValueUtil.requestValue(method, argValues, "name");
        System.out.println(name);

        Object resUser = ValueUtil.requestValue(method, argValues, "user");
        System.out.println(resUser);

        Object userName = ValueUtil.requestValue(method, argValues, "user.name");
        System.out.println(userName);
    }

    @Test
    public void testSpelReqParam03() throws NoSuchMethodException {
        Method method = this.getClass().getDeclaredMethod("hello", String.class, User.class);
        User user = new User();
        user.setName("zhangSan");
        user.setAge(23);
        user.setMale(true);
        Object[] argValues = {"zhangSan", user};

        Object userAge = ValueUtil.respValue(user,"age",null);
        System.out.println(userAge);

        Object name = ValueUtil.respValue(user,"name",null);
        System.out.println(name);


        Object male = ValueUtil.respValue(user,"male",null);
        System.out.println(male);

    }

    public String hello(String name, User user) {
        return "name:" + name + ",user=" + user;
    }

    static class User {

        private String name;

        private Integer age;

        private Boolean male;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Boolean getMale() {
            return male;
        }

        public void setMale(Boolean male) {
            this.male = male;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", male=" + male +
                    '}';
        }
    }

}
