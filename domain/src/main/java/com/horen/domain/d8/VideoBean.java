package com.horen.domain.d8;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/15:36
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class VideoBean {
    /**
     * access_lv : 0
     * duration : 1:22:28
     * id_encrypt : YHwvPtLYVNDod6aY
     * play_count : 119
     * thumb_href : http://img.qstaic.com:80/thumb/2018-06-06/w0R2EhhwPA2MkYPDbxpl_thumb_1.jpg
     * timeout : 1小时前
     * title : 艺术主题宾馆屌丝眼镜黄毛啪啪啪长发美女清纯妹纸可以口爆吞精让人羡慕各种激情缠绵好生快活
     */

    private int access_lv;
    private String duration;
    private String id_encrypt;
    private int play_count;
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
