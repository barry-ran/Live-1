package com.horen.domain.live;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/06/27/13:02
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class HomeLive {


    private List<PingtaiBean> pingtai;

    public List<PingtaiBean> getPingtai() {
        return pingtai;
    }

    public void setPingtai(List<PingtaiBean> pingtai) {
        this.pingtai = pingtai;
    }

    public static class PingtaiBean {
        public PingtaiBean(String address, String xinimg, String number, String title) {
            this.address = address;
            this.xinimg = xinimg;
            Number = number;
            this.title = title;
        }

        /**
         * address : jsonxingguang.txt
         * xinimg : http://ww1.sinaimg.cn/large/87c01ec7gy1fqi47x1heoj2020020748.jpg
         * Number : 22
         * title : 星光
         */



        private String address;
        private String xinimg;
        private String Number;
        private String title;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getXinimg() {
            return xinimg;
        }

        public void setXinimg(String xinimg) {
            this.xinimg = xinimg;
        }

        public String getNumber() {
            return Number;
        }

        public void setNumber(String Number) {
            this.Number = Number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
