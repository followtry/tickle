package cn.followtry;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/17
 */
public class Location {

    /**  */
    private String province;

    /**  */
    private String location;

    /**  */
    private String county;

    /**  */
    private Integer doorNo;

    public Location(String province, String location, String county, Integer doorNo) {
        this.province = province;
        this.location = location;
        this.county = county;
        this.doorNo = doorNo;
    }

    public Location() {
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(Integer doorNo) {
        this.doorNo = doorNo;
    }


    @Override
    public String toString() {
        return "Location{" +
                "county='" + county + '\'' +
                ", doorNo=" + doorNo +
                ", location='" + location + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
