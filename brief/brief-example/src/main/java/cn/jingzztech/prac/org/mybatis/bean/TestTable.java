package cn.jingzztech.prac.org.mybatis.bean;

import java.util.Date;

public class TestTable extends BaseBean {
    /**  */
	private static final long serialVersionUID = 1L;

	private Integer testId;

    private String testName;

    private Date testDate;

    private Date testTime;

    public TestTable(Integer testId, String testName, Date testDate, Date testTime) {
        this.testId = testId;
        this.testName = testName;
        this.testDate = testDate;
        this.testTime = testTime;
    }

    public TestTable() {
        super();
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }
}