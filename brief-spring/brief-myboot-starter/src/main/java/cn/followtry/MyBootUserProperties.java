package cn.followtry;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/17
 */
@ConfigurationProperties(
        prefix = "my-boot"
)
public class MyBootUserProperties {
    public static final String MY_BOOT_PREFIX = "my-boot";

    /**  */
    private String name;

    /**  */
    private Integer age;

    private Integer sex;

    @NestedConfigurationProperty
    private Location location;

    public MyBootUserProperties() {
    }

    public MyBootUserProperties(String name, Integer age, Integer sex, Location location) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.location = location;
    }

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "MyBootUserProperties{" +
                "age=" + age +
                ", location=" + location +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
