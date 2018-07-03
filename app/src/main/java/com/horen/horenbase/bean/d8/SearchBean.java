package com.horen.horenbase.bean.d8;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/09:13
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class SearchBean {

    /**
     * inputs : {"page":"1","per_page":"5","title":"舔"}
     * paginator : {"current_page":1,"from":1,"last_page":2,"next_page_url":"http://email.d8dizhi.at.gmail.com.d8-app.space/api/v2/videos?page=2","per_page":"5","to":5,"total":7}
     * search : 舔
     * videos : [{"access_lv":0,"duration":"0:04:51","id_encrypt":"a5HHQbM2x8F2saNM","play_count":12201,"status":10,"thumb_href":"http://img.qstaic.com:80/thumb/2016-10-26/112lEHU0b8dAHvIiz1PB_thumb_6.jpg","timeout":"1年前","title":"害羞的人妻被舔阴"},{"access_lv":0,"duration":"0:07:00","id_encrypt":"8puISQchHBJdD89m","play_count":5455,"status":10,"thumb_href":"http://img.qstaic.com:80/thumb/2016-10-29/1IYyKGao6t1dZfqBaHPs_thumb_3.jpg","timeout":"1年前","title":"一对女同性恋在互舔阴"},{"access_lv":0,"duration":"0:09:50","id_encrypt":"TlOTkmtKsueKD8pY","play_count":3885,"status":10,"thumb_href":"http://img.qstaic.com:80/thumb/2016-10-15/ADL4v7AWCUoPPAg76VRI_thumb_1.jpg","timeout":"1年前","title":"热带沙滩上让人幻想的舔阴"},{"access_lv":0,"duration":"0:08:55","id_encrypt":"CnPVN3yPwWALGJ2N","play_count":14033,"status":10,"thumb_href":"http://img.qstaic.com:80/thumb/2017-03-13/EU0Imozlhg9H6IUoMBsJ_thumb_8.jpg","timeout":"1年前","title":"穿着高跟鞋黑丝被舔阴《VX群里的女神》"},{"access_lv":0,"duration":"0:39:45","id_encrypt":"QXzDGpJYBXkcNvxT","play_count":6738,"status":10,"thumb_href":"http://img.qstaic.com:80/thumb/2017-03-23/HK4viz9m4psq6HO9CiU2_thumb_3.jpg","timeout":"1年前","title":"两个熟女互舔阴被男人看见就是一顿狂戳"}]
     */

    private InputsBean inputs;
    private PaginatorBean paginator;
    private String search;
    private List<VideosBean> videos;

    public InputsBean getInputs() {
        return inputs;
    }

    public void setInputs(InputsBean inputs) {
        this.inputs = inputs;
    }

    public PaginatorBean getPaginator() {
        return paginator;
    }

    public void setPaginator(PaginatorBean paginator) {
        this.paginator = paginator;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class InputsBean {
        /**
         * page : 1
         * per_page : 5
         * title : 舔
         */

        private String page;
        private String per_page;
        private String title;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class PaginatorBean {
        /**
         * current_page : 1
         * from : 1
         * last_page : 2
         * next_page_url : http://email.d8dizhi.at.gmail.com.d8-app.space/api/v2/videos?page=2
         * per_page : 5
         * to : 5
         * total : 7
         */

        private int current_page;
        private int from;
        private int last_page;
        private String next_page_url;
        private String per_page;
        private int to;
        private int total;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public String getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(String next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class VideosBean {
        /**
         * access_lv : 0
         * duration : 0:04:51
         * id_encrypt : a5HHQbM2x8F2saNM
         * play_count : 12201
         * status : 10
         * thumb_href : http://img.qstaic.com:80/thumb/2016-10-26/112lEHU0b8dAHvIiz1PB_thumb_6.jpg
         * timeout : 1年前
         * title : 害羞的人妻被舔阴
         */

        private int access_lv;
        private String duration;
        private String id_encrypt;
        private int play_count;
        private int status;
        private String thumb_href;
        private String timeout;
        private String title;

        public int getAccess_lv() {
            return access_lv;
        }

        public void setAccess_lv(int access_lv) {
            this.access_lv = access_lv;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getId_encrypt() {
            return id_encrypt;
        }

        public void setId_encrypt(String id_encrypt) {
            this.id_encrypt = id_encrypt;
        }

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getThumb_href() {
            return thumb_href;
        }

        public void setThumb_href(String thumb_href) {
            this.thumb_href = thumb_href;
        }

        public String getTimeout() {
            return timeout;
        }

        public void setTimeout(String timeout) {
            this.timeout = timeout;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
