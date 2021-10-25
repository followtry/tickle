package cn.followtry.common;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author followtry
 * @since 2021/10/13 8:54 下午
 */
public class SpelTest {

    private SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    @Test
    public void testSpelRespBool() {
        User user = new User();
        user.setMale(true);

        Expression expression = spelExpressionParser.parseExpression("male");
        Boolean value = expression.getValue(user,Boolean.class);
        System.out.println(value);
        Assert.isTrue(Objects.equals(value,true),"预期为true，实际为false");
    }

    @Test
    public void testSpelRespInt() {
        User user = new User();
        user.setAge(22);

        Expression expression = spelExpressionParser.parseExpression("age == 22");
        Boolean value = expression.getValue(user,Boolean.class);
        System.out.println(value);
        Assert.isTrue(Objects.equals(value,true),"预期为true，实际为false");
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
    }

}
