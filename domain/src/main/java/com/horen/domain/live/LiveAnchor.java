package com.horen.domain.live;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * @author :ChenYangYi
 * @date :2018/07/06/13:52
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class LiveAnchor extends LitePalSupport implements Serializable{
    /**
     * 主播名
     */
    private String name;
    /**
     * 直播图片链接
     */
    private String imageUrl;
    /**
     * 直播间播放链接
     */
    private String url;

    public LiveAnchor(Builder builder) {
        this.name = builder.name;
        this.imageUrl = builder.imageUrl;
        this.url = builder.url;
    }

    public static class Builder {

        private String name;
        private String imageUrl;
        private String url;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public LiveAnchor builder() {
            return new LiveAnchor(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrl() {
        return url;
    }
}
