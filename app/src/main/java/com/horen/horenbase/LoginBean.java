package com.horen.horenbase;


import com.horen.base.bean.BaseResponse;

/**
 * @author :ChenYangYi
 * @date :2018/05/15/15:11
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LoginBean extends BaseResponse {

    /**
     * app_token : 467818aaf494ca410518e6549a9b842c9283d1cb
     * uploadurl : http://47.98.235.3:8901
     * user : {"app_token":"467818aaf494ca410518e6549a9b842c9283d1cb","company_id":"CM00000088","company_name":"","creator":"","org_id":"HC00000080","photo":"","role_id":"EOS_DBA","user_id":"HU00000952","user_mail":"1353095373@qq.com","user_mobile":"18370622030","user_name":"18616217917","user_nickname":"体验用户"}
     */

    private String app_token;
    private String uploadurl;
    private UserBean user;

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }

    public String getUploadurl() {
        return uploadurl;
    }

    public void setUploadurl(String uploadurl) {
        this.uploadurl = uploadurl;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * app_token : 467818aaf494ca410518e6549a9b842c9283d1cb
         * company_id : CM00000088
         * company_name :
         * creator :
         * org_id : HC00000080
         * photo :
         * role_id : EOS_DBA
         * user_id : HU00000952
         * user_mail : 1353095373@qq.com
         * user_mobile : 18370622030
         * user_name : 18616217917
         * user_nickname : 体验用户
         */

        private String app_token;
        private String company_id;
        private String company_name;
        private String creator;
        private String org_id;
        private String photo;
        private String role_id;
        private String user_id;
        private String user_mail;
        private String user_mobile;
        private String user_name;
        private String user_nickname;
        private String company_address;
        private String company_class;
        private String org_name;
        private String flag_data;
        private String consulting_mobile;
        private String consulting_name;

        public String getConsulting_mobile() {
            return consulting_mobile;
        }

        public void setConsulting_mobile(String consulting_mobile) {
            this.consulting_mobile = consulting_mobile;
        }

        public String getConsulting_name() {
            return consulting_name;
        }

        public void setConsulting_name(String consulting_name) {
            this.consulting_name = consulting_name;
        }

        public String getCompany_address() {
            return company_address;
        }

        public void setCompany_address(String company_address) {
            this.company_address = company_address;
        }

        public String getCompany_class() {
            return company_class;
        }

        public void setCompany_class(String company_class) {
            this.company_class = company_class;
        }

        public String getOrg_name() {
            return org_name;
        }

        public void setOrg_name(String org_name) {
            this.org_name = org_name;
        }

        public String getFlag_data() {
            return flag_data;
        }

        public void setFlag_data(String flag_data) {
            this.flag_data = flag_data;
        }

        public String getApp_token() {
            return app_token;
        }

        public void setApp_token(String app_token) {
            this.app_token = app_token;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_mail() {
            return user_mail;
        }

        public void setUser_mail(String user_mail) {
            this.user_mail = user_mail;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }
    }
}
