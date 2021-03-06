package com.horen.domain.d8;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/07/04/16:56
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class NavigationTag extends BaseIndexPinyinBean {


    /**
     * count : 1
     * items : [{"id":176,"name":"asia pacific","type":0}]
     */
    private String tagName;
    private int count;
    private List<ItemsBean> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public String getTagName() {
        return tagName;
    }

    public NavigationTag setTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    @Override
    public String getTarget() {
        return tagName;
    }

    public static class ItemsBean {
        /**
         * id : 176
         * name : asia pacific
         * type : 0
         */

        private int id;
        private String name;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
