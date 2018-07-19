package com.horen.movie.bean;

/**
 * @author :ChenYangYi
 * @date :2018/07/19/14:20
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class CustomMsg {

    /**
     * conversationDesc :
     * deviceType : Android
     * otherUserMsg : false
     * sender : {"birthday":"","city":"","coin":0,"diamonds":0,"emotional_state":"保密","family_chieftain":0,"family_id":0,"fans_count":0,"focus_count":0,"follow_id":0,"gameCurrency":0,"gh_status":0,"head_image":"http://ybslkj.com/public/attachment/201806/11/11/5b1df1df04933.png","is_agree":0,"is_authentication":0,"is_edit_sex":0,"is_remind":1,"is_vip":0,"item":{"个性签名":"TA好像忘记签名了","号":"186558","家乡":"火星 ","年龄":"你猜","情感状态":"保密","职业":"主播"},"job":"主播","levelImageResId":2130838393,"login_type":0,"luck_num":"","n_svideo_count":0,"nick_name":"郭","nick_nameFormat":"郭：","open_podcast_goods":0,"podcast_goods":0,"podcast_order":0,"podcast_pai":0,"proUser":false,"province":"火星","selected":false,"sex":1,"sexResId":2130838012,"shop_goods":0,"shopping_cart":0,"showId":"186558","show_podcast_goods":0,"show_podcast_order":0,"show_podcast_pai":0,"show_shopping_cart":0,"show_svideo":1,"show_user_order":0,"show_user_pai":0,"signature":"TA好像忘记签名了","society_chieftain":0,"society_id":0,"society_name":"","sort_num":2001000,"ticket":0,"use_diamonds":0,"useable_ticket":0,"user_id":"186558","user_level":1,"user_order":0,"user_pai":0,"v_explain":"","v_icon":"","v_type":"0","video_count":0,"vip_expire_time":"未开通"}
     * type : 5
     */

    private String conversationDesc;
    private String deviceType;
    private boolean otherUserMsg;
    private SenderBean sender;
    private int type;

    public String getConversationDesc() {
        return conversationDesc;
    }

    public void setConversationDesc(String conversationDesc) {
        this.conversationDesc = conversationDesc;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public boolean isOtherUserMsg() {
        return otherUserMsg;
    }

    public void setOtherUserMsg(boolean otherUserMsg) {
        this.otherUserMsg = otherUserMsg;
    }

    public SenderBean getSender() {
        return sender;
    }

    public void setSender(SenderBean sender) {
        this.sender = sender;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class SenderBean {
        /**
         * birthday :
         * city :
         * coin : 0
         * diamonds : 0
         * emotional_state : 保密
         * family_chieftain : 0
         * family_id : 0
         * fans_count : 0
         * focus_count : 0
         * follow_id : 0
         * gameCurrency : 0
         * gh_status : 0
         * head_image : http://ybslkj.com/public/attachment/201806/11/11/5b1df1df04933.png
         * is_agree : 0
         * is_authentication : 0
         * is_edit_sex : 0
         * is_remind : 1
         * is_vip : 0
         * item : {"个性签名":"TA好像忘记签名了","号":"186558","家乡":"火星 ","年龄":"你猜","情感状态":"保密","职业":"主播"}
         * job : 主播
         * levelImageResId : 2130838393
         * login_type : 0
         * luck_num :
         * n_svideo_count : 0
         * nick_name : 郭
         * nick_nameFormat : 郭：
         * open_podcast_goods : 0
         * podcast_goods : 0
         * podcast_order : 0
         * podcast_pai : 0
         * proUser : false
         * province : 火星
         * selected : false
         * sex : 1
         * sexResId : 2130838012
         * shop_goods : 0
         * shopping_cart : 0
         * showId : 186558
         * show_podcast_goods : 0
         * show_podcast_order : 0
         * show_podcast_pai : 0
         * show_shopping_cart : 0
         * show_svideo : 1
         * show_user_order : 0
         * show_user_pai : 0
         * signature : TA好像忘记签名了
         * society_chieftain : 0
         * society_id : 0
         * society_name :
         * sort_num : 2001000
         * ticket : 0
         * use_diamonds : 0
         * useable_ticket : 0
         * user_id : 186558
         * user_level : 1
         * user_order : 0
         * user_pai : 0
         * v_explain :
         * v_icon :
         * v_type : 0
         * video_count : 0
         * vip_expire_time : 未开通
         */

        private String birthday;
        private String city;
        private int coin;
        private int diamonds;
        private String emotional_state;
        private int family_chieftain;
        private int family_id;
        private int fans_count;
        private int focus_count;
        private int follow_id;
        private int gameCurrency;
        private int gh_status;
        private String head_image;
        private int is_agree;
        private int is_authentication;
        private int is_edit_sex;
        private int is_remind;
        private int is_vip;
        private ItemBean item;
        private String job;
        private int levelImageResId;
        private int login_type;
        private String luck_num;
        private int n_svideo_count;
        private String nick_name;
        private String nick_nameFormat;
        private int open_podcast_goods;
        private int podcast_goods;
        private int podcast_order;
        private int podcast_pai;
        private boolean proUser;
        private String province;
        private boolean selected;
        private int sex;
        private int sexResId;
        private int shop_goods;
        private int shopping_cart;
        private String showId;
        private int show_podcast_goods;
        private int show_podcast_order;
        private int show_podcast_pai;
        private int show_shopping_cart;
        private int show_svideo;
        private int show_user_order;
        private int show_user_pai;
        private String signature;
        private int society_chieftain;
        private int society_id;
        private String society_name;
        private int sort_num;
        private int ticket;
        private int use_diamonds;
        private int useable_ticket;
        private String user_id;
        private int user_level;
        private int user_order;
        private int user_pai;
        private String v_explain;
        private String v_icon;
        private String v_type;
        private int video_count;
        private String vip_expire_time;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCoin() {
            return coin;
        }

        public void setCoin(int coin) {
            this.coin = coin;
        }

        public int getDiamonds() {
            return diamonds;
        }

        public void setDiamonds(int diamonds) {
            this.diamonds = diamonds;
        }

        public String getEmotional_state() {
            return emotional_state;
        }

        public void setEmotional_state(String emotional_state) {
            this.emotional_state = emotional_state;
        }

        public int getFamily_chieftain() {
            return family_chieftain;
        }

        public void setFamily_chieftain(int family_chieftain) {
            this.family_chieftain = family_chieftain;
        }

        public int getFamily_id() {
            return family_id;
        }

        public void setFamily_id(int family_id) {
            this.family_id = family_id;
        }

        public int getFans_count() {
            return fans_count;
        }

        public void setFans_count(int fans_count) {
            this.fans_count = fans_count;
        }

        public int getFocus_count() {
            return focus_count;
        }

        public void setFocus_count(int focus_count) {
            this.focus_count = focus_count;
        }

        public int getFollow_id() {
            return follow_id;
        }

        public void setFollow_id(int follow_id) {
            this.follow_id = follow_id;
        }

        public int getGameCurrency() {
            return gameCurrency;
        }

        public void setGameCurrency(int gameCurrency) {
            this.gameCurrency = gameCurrency;
        }

        public int getGh_status() {
            return gh_status;
        }

        public void setGh_status(int gh_status) {
            this.gh_status = gh_status;
        }

        public String getHead_image() {
            return head_image;
        }

        public void setHead_image(String head_image) {
            this.head_image = head_image;
        }

        public int getIs_agree() {
            return is_agree;
        }

        public void setIs_agree(int is_agree) {
            this.is_agree = is_agree;
        }

        public int getIs_authentication() {
            return is_authentication;
        }

        public void setIs_authentication(int is_authentication) {
            this.is_authentication = is_authentication;
        }

        public int getIs_edit_sex() {
            return is_edit_sex;
        }

        public void setIs_edit_sex(int is_edit_sex) {
            this.is_edit_sex = is_edit_sex;
        }

        public int getIs_remind() {
            return is_remind;
        }

        public void setIs_remind(int is_remind) {
            this.is_remind = is_remind;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public int getLevelImageResId() {
            return levelImageResId;
        }

        public void setLevelImageResId(int levelImageResId) {
            this.levelImageResId = levelImageResId;
        }

        public int getLogin_type() {
            return login_type;
        }

        public void setLogin_type(int login_type) {
            this.login_type = login_type;
        }

        public String getLuck_num() {
            return luck_num;
        }

        public void setLuck_num(String luck_num) {
            this.luck_num = luck_num;
        }

        public int getN_svideo_count() {
            return n_svideo_count;
        }

        public void setN_svideo_count(int n_svideo_count) {
            this.n_svideo_count = n_svideo_count;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getNick_nameFormat() {
            return nick_nameFormat;
        }

        public void setNick_nameFormat(String nick_nameFormat) {
            this.nick_nameFormat = nick_nameFormat;
        }

        public int getOpen_podcast_goods() {
            return open_podcast_goods;
        }

        public void setOpen_podcast_goods(int open_podcast_goods) {
            this.open_podcast_goods = open_podcast_goods;
        }

        public int getPodcast_goods() {
            return podcast_goods;
        }

        public void setPodcast_goods(int podcast_goods) {
            this.podcast_goods = podcast_goods;
        }

        public int getPodcast_order() {
            return podcast_order;
        }

        public void setPodcast_order(int podcast_order) {
            this.podcast_order = podcast_order;
        }

        public int getPodcast_pai() {
            return podcast_pai;
        }

        public void setPodcast_pai(int podcast_pai) {
            this.podcast_pai = podcast_pai;
        }

        public boolean isProUser() {
            return proUser;
        }

        public void setProUser(boolean proUser) {
            this.proUser = proUser;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSexResId() {
            return sexResId;
        }

        public void setSexResId(int sexResId) {
            this.sexResId = sexResId;
        }

        public int getShop_goods() {
            return shop_goods;
        }

        public void setShop_goods(int shop_goods) {
            this.shop_goods = shop_goods;
        }

        public int getShopping_cart() {
            return shopping_cart;
        }

        public void setShopping_cart(int shopping_cart) {
            this.shopping_cart = shopping_cart;
        }

        public String getShowId() {
            return showId;
        }

        public void setShowId(String showId) {
            this.showId = showId;
        }

        public int getShow_podcast_goods() {
            return show_podcast_goods;
        }

        public void setShow_podcast_goods(int show_podcast_goods) {
            this.show_podcast_goods = show_podcast_goods;
        }

        public int getShow_podcast_order() {
            return show_podcast_order;
        }

        public void setShow_podcast_order(int show_podcast_order) {
            this.show_podcast_order = show_podcast_order;
        }

        public int getShow_podcast_pai() {
            return show_podcast_pai;
        }

        public void setShow_podcast_pai(int show_podcast_pai) {
            this.show_podcast_pai = show_podcast_pai;
        }

        public int getShow_shopping_cart() {
            return show_shopping_cart;
        }

        public void setShow_shopping_cart(int show_shopping_cart) {
            this.show_shopping_cart = show_shopping_cart;
        }

        public int getShow_svideo() {
            return show_svideo;
        }

        public void setShow_svideo(int show_svideo) {
            this.show_svideo = show_svideo;
        }

        public int getShow_user_order() {
            return show_user_order;
        }

        public void setShow_user_order(int show_user_order) {
            this.show_user_order = show_user_order;
        }

        public int getShow_user_pai() {
            return show_user_pai;
        }

        public void setShow_user_pai(int show_user_pai) {
            this.show_user_pai = show_user_pai;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getSociety_chieftain() {
            return society_chieftain;
        }

        public void setSociety_chieftain(int society_chieftain) {
            this.society_chieftain = society_chieftain;
        }

        public int getSociety_id() {
            return society_id;
        }

        public void setSociety_id(int society_id) {
            this.society_id = society_id;
        }

        public String getSociety_name() {
            return society_name;
        }

        public void setSociety_name(String society_name) {
            this.society_name = society_name;
        }

        public int getSort_num() {
            return sort_num;
        }

        public void setSort_num(int sort_num) {
            this.sort_num = sort_num;
        }

        public int getTicket() {
            return ticket;
        }

        public void setTicket(int ticket) {
            this.ticket = ticket;
        }

        public int getUse_diamonds() {
            return use_diamonds;
        }

        public void setUse_diamonds(int use_diamonds) {
            this.use_diamonds = use_diamonds;
        }

        public int getUseable_ticket() {
            return useable_ticket;
        }

        public void setUseable_ticket(int useable_ticket) {
            this.useable_ticket = useable_ticket;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getUser_level() {
            return user_level;
        }

        public void setUser_level(int user_level) {
            this.user_level = user_level;
        }

        public int getUser_order() {
            return user_order;
        }

        public void setUser_order(int user_order) {
            this.user_order = user_order;
        }

        public int getUser_pai() {
            return user_pai;
        }

        public void setUser_pai(int user_pai) {
            this.user_pai = user_pai;
        }

        public String getV_explain() {
            return v_explain;
        }

        public void setV_explain(String v_explain) {
            this.v_explain = v_explain;
        }

        public String getV_icon() {
            return v_icon;
        }

        public void setV_icon(String v_icon) {
            this.v_icon = v_icon;
        }

        public String getV_type() {
            return v_type;
        }

        public void setV_type(String v_type) {
            this.v_type = v_type;
        }

        public int getVideo_count() {
            return video_count;
        }

        public void setVideo_count(int video_count) {
            this.video_count = video_count;
        }

        public String getVip_expire_time() {
            return vip_expire_time;
        }

        public void setVip_expire_time(String vip_expire_time) {
            this.vip_expire_time = vip_expire_time;
        }

        public static class ItemBean {
            /**
             * 个性签名 : TA好像忘记签名了
             * 号 : 186558
             * 家乡 : 火星
             * 年龄 : 你猜
             * 情感状态 : 保密
             * 职业 : 主播
             */

            private String 个性签名;
            private String 号;
            private String 家乡;
            private String 年龄;
            private String 情感状态;
            private String 职业;

            public String get个性签名() {
                return 个性签名;
            }

            public void set个性签名(String 个性签名) {
                this.个性签名 = 个性签名;
            }

            public String get号() {
                return 号;
            }

            public void set号(String 号) {
                this.号 = 号;
            }

            public String get家乡() {
                return 家乡;
            }

            public void set家乡(String 家乡) {
                this.家乡 = 家乡;
            }

            public String get年龄() {
                return 年龄;
            }

            public void set年龄(String 年龄) {
                this.年龄 = 年龄;
            }

            public String get情感状态() {
                return 情感状态;
            }

            public void set情感状态(String 情感状态) {
                this.情感状态 = 情感状态;
            }

            public String get职业() {
                return 职业;
            }

            public void set职业(String 职业) {
                this.职业 = 职业;
            }
        }
    }
}
