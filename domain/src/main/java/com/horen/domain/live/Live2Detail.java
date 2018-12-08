package com.horen.domain.live;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/06/27/14:42
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class Live2Detail implements Serializable {

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private int count;
        private List<ListsBean> lists;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public List<LiveDetail.ZhuboBean> toList() {
            List<LiveDetail.ZhuboBean> mData = new ArrayList<>();
            for (ListsBean list : lists) {
                mData.add(new LiveDetail.ZhuboBean(replaceHttpUrl(list.getPlay_url()), replaceHttpUrl(list.getImg()), replaceHttpUrl(list.getTitle())));
            }
            return mData;
        }

        /**
         * 替换http网址中的\
         */
        public String replaceHttpUrl(String url) {
            if (TextUtils.isEmpty(url))
                return "";
            return url.replaceAll("\\\\", "");
        }

        public static class ListsBean {
            /**
             * title : johnnyforlea
             * img : https://roomimg.stream.highwebmedia.com/ri/johnnyforlea.jpg?1544153880
             * play_url : https://edge217.stream.highwebmedia.com/live-hls/amlst:johnnyforlea-sd-c60ed02377b6e1e63e04577ffe73e6f8aec1da2aae7589bd6e6b4aeb2adf3dbb_trns_h264/playlist.m3u8
             * flag : 1
             * id : 1
             * live_adv : {"name":"真金真人棋牌牛牛，点击试玩秒提现 ","link":"https://co.we654.com/"}
             * number : 69
             * name : jsonoumeiCOUPLE.txt
             * type : pingtai
             */

            private String title;
            private String img;
            private String play_url;
            private int flag;
            private int id;
            private LiveAdvBean live_adv;
            private int number;
            private String name;
            private String type;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getPlay_url() {
                return play_url;
            }

            public void setPlay_url(String play_url) {
                this.play_url = play_url;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public LiveAdvBean getLive_adv() {
                return live_adv;
            }

            public void setLive_adv(LiveAdvBean live_adv) {
                this.live_adv = live_adv;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class LiveAdvBean {
                /**
                 * name : 真金真人棋牌牛牛，点击试玩秒提现
                 * link : https://co.we654.com/
                 */

                private String name;
                private String link;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }
            }
        }
    }
}
