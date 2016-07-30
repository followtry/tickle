package cn.jingzz.brief.dao.model;

import java.util.Date;

public class MyBatis {
    private String id;

    private String name;

    private Date time;

    private String mark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

	@Override
	public String toString() {
		return "MyBatis [id=" + id + ", name=" + name + ", time=" + time + ", mark=" + mark + "]";
	}
    
}