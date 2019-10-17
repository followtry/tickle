package cn.followtry;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/17
 */
public class UserInfo implements User{
    /**  */
    private Integer id;

    /**  */
    private String name;

    /**  */
    private Integer age;

    /**  */
    private Integer sex;

    private Location location;

    public UserInfo() {
    }

    public UserInfo(Integer id, String name, Integer age, Integer sex, Location location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.location = location;
    }

    public UserInfo(Integer id, String name, Integer age, Integer sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public Object userInfo() {
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", id=" + id +
                ", location=" + location +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
