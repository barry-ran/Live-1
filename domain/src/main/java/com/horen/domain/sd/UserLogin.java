package com.horen.domain.sd;

/**
 * @author :ChenYangYi
 * @date :2018/07/18/14:37
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class UserLogin {

    /**
     * act : do_login
     * ctl : login
     * error : 登录成功
     * first_login : 0
     * is_agree : 0
     * is_lack : 0
     * login_send_score : 0
     * new_level : 0
     * nick_name : 186413
     * status : 1
     * user_id : 186413
     * user_info : {"head_image":"http://ybslkj.com/public/attachment/201806/11/11/5b1df1df04933.png","mobile":"18370622030","nick_name":"186413","user_id":"186413"}
     */

    private String act;
    private String ctl;
    private String error;
    private int first_login;
    private int is_agree;
    private int is_lack;
    private int login_send_score;
    private int new_level;
    private String nick_name;
    private int status;
    private int user_id;
    private UserInfoBean user_info;

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getCtl() {
        return ctl;
    }

    public void setCtl(String ctl) {
        this.ctl = ctl;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getFirst_login() {
        return first_login;
    }

    public void setFirst_login(int first_login) {
        this.first_login = first_login;
    }

    public int getIs_agree() {
        return is_agree;
    }

    public void setIs_agree(int is_agree) {
        this.is_agree = is_agree;
    }

    public int getIs_lack() {
        return is_lack;
    }

    public void setIs_lack(int is_lack) {
        this.is_lack = is_lack;
    }

    public int getLogin_send_score() {
        return login_send_score;
    }

    public void setLogin_send_score(int login_send_score) {
        this.login_send_score = login_send_score;
    }

    public int getNew_level() {
        return new_level;
    }

    public void setNew_level(int new_level) {
        this.new_level = new_level;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public static class UserInfoBean {
        /**
         * head_image : http://ybslkj.com/public/attachment/201806/11/11/5b1df1df04933.png
         * mobile : 18370622030
         * nick_name : 186413
         * user_id : 186413
         */

        private String head_image;
        private String mobile;
        private String nick_name;
        private String user_id;

        public String getHead_image() {
            return head_image;
        }

        public void setHead_image(String head_image) {
            this.head_image = head_image;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
