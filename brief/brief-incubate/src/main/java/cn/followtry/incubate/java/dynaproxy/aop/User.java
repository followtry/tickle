package cn.followtry.incubate.java.dynaproxy.aop;

/**
 * Created by followtry on 2017/3/26 0026.
 */
public class User {

    private String name;

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public void sayHello() {
        System.out.println("Hello,I'm " + name);
    }
}
