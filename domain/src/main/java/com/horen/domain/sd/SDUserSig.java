package com.horen.domain.sd;

public class SDUserSig {
    private String usersig;
    private int status;
    private String act;
    private String ctl;


    public void setUsersig(String usersig) {
        this.usersig = usersig;
    }
    public String getUsersig() {
        return usersig;
    }


    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }


    public void setAct(String act) {
        this.act = act;
    }
    public String getAct() {
        return act;
    }


    public void setCtl(String ctl) {
        this.ctl = ctl;
    }
    public String getCtl() {
        return ctl;
    }
}
