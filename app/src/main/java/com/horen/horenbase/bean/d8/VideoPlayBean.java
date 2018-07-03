package com.horen.horenbase.bean.d8;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/07/03/12:47
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class VideoPlayBean {

    /**
     * channel : {"normal":[""],"vip":[""]}
     * channel_original : {"normal":"md.vsilent.space:80","vip":"md.csteam.space:80"}
     * has_dash : false
     * is_vip : false
     * keys : {"normal":"key-MjAxOC0wNy0xMC0xMy0xMzoyMDE2LTEwLTI5OjFJWXlLR2FvNnQxZFpmcUJhSFBzX05ELm1wNDpudWxsOmRBUmRjU1ZJNTRiY2JLSkFlbnJvVzBjYWRvRzhPRno1","vip":"key-MjAxOC0wNy0xMC0xMy0xMzoyMDE2LTEwLTI5OjFJWXlLR2FvNnQxZFpmcUJhSFBzX05ELm1wNDpudWxsOmRBUmRjU1ZJNTRiY2JLSkFlbnJvVzBjYWRvRzhPRno1"}
     * keys_dash : {"normal":"key-MjAxOC0wNy0xMC0xMy0xMzoyMDE2LTEwLTI5OjFJWXlLR2FvNnQxZFpmcUJhSFBzX05ELm1wNDpudWxsOmRBUmRjU1ZJNTRiY2JLSkFlbnJvVzBjYWRvRzhPRno1","vip":"key-MjAxOC0wNy0xMC0xMy0xMzoyMDE2LTEwLTI5OjFJWXlLR2FvNnQxZFpmcUJhSFBzX05ELm1wNDpudWxsOmRBUmRjU1ZJNTRiY2JLSkFlbnJvVzBjYWRvRzhPRno1"}
     */

    private ChannelBean channel;
    private ChannelOriginalBean channel_original;
    private boolean has_dash;
    private boolean is_vip;
    private KeysBean keys;

    public ChannelBean getChannel() {
        return channel;
    }

    public void setChannel(ChannelBean channel) {
        this.channel = channel;
    }

    public ChannelOriginalBean getChannel_original() {
        return channel_original;
    }

    public void setChannel_original(ChannelOriginalBean channel_original) {
        this.channel_original = channel_original;
    }

    public boolean isHas_dash() {
        return has_dash;
    }

    public void setHas_dash(boolean has_dash) {
        this.has_dash = has_dash;
    }

    public boolean isIs_vip() {
        return is_vip;
    }

    public void setIs_vip(boolean is_vip) {
        this.is_vip = is_vip;
    }

    public KeysBean getKeys() {
        return keys;
    }

    public void setKeys(KeysBean keys) {
        this.keys = keys;
    }


    public static class ChannelBean {
        private List<String> normal;
        private List<String> vip;

        public List<String> getNormal() {
            return normal;
        }

        public void setNormal(List<String> normal) {
            this.normal = normal;
        }

        public List<String> getVip() {
            return vip;
        }

        public void setVip(List<String> vip) {
            this.vip = vip;
        }
    }

    public static class ChannelOriginalBean {
        /**
         * normal : md.vsilent.space:80
         * vip : md.csteam.space:80
         */

        private String normal;
        private String vip;

        public String getNormal() {
            return normal;
        }

        public void setNormal(String normal) {
            this.normal = normal;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }
    }

    public static class KeysBean {
        /**
         * normal : key-MjAxOC0wNy0xMC0xMy0xMzoyMDE2LTEwLTI5OjFJWXlLR2FvNnQxZFpmcUJhSFBzX05ELm1wNDpudWxsOmRBUmRjU1ZJNTRiY2JLSkFlbnJvVzBjYWRvRzhPRno1
         * vip : key-MjAxOC0wNy0xMC0xMy0xMzoyMDE2LTEwLTI5OjFJWXlLR2FvNnQxZFpmcUJhSFBzX05ELm1wNDpudWxsOmRBUmRjU1ZJNTRiY2JLSkFlbnJvVzBjYWRvRzhPRno1
         */

        private String normal;
        private String vip;

        public String getNormal() {
            return normal;
        }

        public void setNormal(String normal) {
            this.normal = normal;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }
    }
}
