package com.horen.domain.sd;

/**
 * @author :ChenYangYi
 * @date :2018/07/19/09:30
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class SDInitModel {

    private String accountType;
    private String city;
    private int distribution;
    private String sdkappid;
    private String full_group_id;
    private String on_line_group_id;

    public String getOn_line_group_id() {
        return on_line_group_id;
    }

    public void setOn_line_group_id(String on_line_group_id) {
        this.on_line_group_id = on_line_group_id;
    }

    public String getFull_group_id() {
        return full_group_id;
    }

    public void setFull_group_id(String full_group_id) {
        this.full_group_id = full_group_id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDistribution() {
        return distribution;
    }

    public void setDistribution(int distribution) {
        this.distribution = distribution;
    }

    public String getSdkappid() {
        return sdkappid;
    }

    public void setSdkappid(String sdkappid) {
        this.sdkappid = sdkappid;
    }
}
