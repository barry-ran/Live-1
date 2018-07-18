package com.horen.domain.sd;

/**
 * @author :ChenYangYi
 * @date :2018/07/18/15:45
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class SDPlayerUrl {


    /**
     * act : get_video2
     * countdown : 5
     * ctl : video
     * error :
     * group_id : 170415
     * id : 170415
     * is_live_pay : 1
     * is_only_play_voice : 0
     * is_pay_over : 0
     * live_fee : 7
     * live_pay_type : 0
     * online_status : 1
     * preview_play_url :
     * room_id : 170415
     * status : 1
     * user_id : 170415
     */

    private String act;
    private int countdown;
    private String ctl;
    private String error;
    private String group_id;
    private int id;
    private int is_live_pay;
    private int is_only_play_voice;
    private int is_pay_over;
    private int live_fee;
    private int live_pay_type;
    private String online_status;
    private String preview_play_url;
    private int room_id;
    private int status;
    private String user_id;
    private String head_image;
    private String play_rtmp;
    private String title;

    public String getHead_image() {
        return head_image;
    }

    public String getPlay_rtmp() {
        return play_rtmp;
    }

    public String getTitle() {
        return title;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
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

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_live_pay() {
        return is_live_pay;
    }

    public void setIs_live_pay(int is_live_pay) {
        this.is_live_pay = is_live_pay;
    }

    public int getIs_only_play_voice() {
        return is_only_play_voice;
    }

    public void setIs_only_play_voice(int is_only_play_voice) {
        this.is_only_play_voice = is_only_play_voice;
    }

    public int getIs_pay_over() {
        return is_pay_over;
    }

    public void setIs_pay_over(int is_pay_over) {
        this.is_pay_over = is_pay_over;
    }

    public int getLive_fee() {
        return live_fee;
    }

    public void setLive_fee(int live_fee) {
        this.live_fee = live_fee;
    }

    public int getLive_pay_type() {
        return live_pay_type;
    }

    public void setLive_pay_type(int live_pay_type) {
        this.live_pay_type = live_pay_type;
    }

    public String getOnline_status() {
        return online_status;
    }

    public void setOnline_status(String online_status) {
        this.online_status = online_status;
    }

    public String getPreview_play_url() {
        return preview_play_url;
    }

    public void setPreview_play_url(String preview_play_url) {
        this.preview_play_url = preview_play_url;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
