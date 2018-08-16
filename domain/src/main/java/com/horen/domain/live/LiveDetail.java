package com.horen.domain.live;

import java.io.Serializable;
import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/06/27/14:42
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LiveDetail implements Serializable {

    private List<ZhuboBean> zhubo;

    public List<ZhuboBean> getZhubo() {
        return zhubo;
    }

    public void setZhubo(List<ZhuboBean> zhubo) {
        this.zhubo = zhubo;
    }

    public static class ZhuboBean implements Serializable {
        /**
         * address : http://t.cn/Ruq0O4m
         * img : http://appimage.douquzhibo.com/4134091?v=1528444574
         * title : 你泽妹
         */

        private String address;
        private String img;
        private String title;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
