package com.safemybaby.vtree.safemybaby.model;

import java.util.List;

/**
 * Created by longdg123 on 10/13/2016.
 */

public class Category_Item {

    /**
     * data : [{"id":1,"id_category":"1","name":"abc xyz1","img":"1","content":"mot hai ba 5"}]
     * limit : 30
     * offset : 1
     */

    private int limit;
    private int offset;
    /**
     * id : 1
     * id_category : 1
     * name : abc xyz1
     * img : 1
     * content : mot hai ba 5
     */

    private List<DataBean> data;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String id_category;
        private String name;
        private String img;
        private String content;
        private String img_cover;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getId_category() {
            return id_category;
        }

        public void setId_category(String id_category) {
            this.id_category = id_category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg_cover() {
            return img_cover;
        }

        public void setImg_cover(String img_cover) {
            this.img_cover = img_cover;
        }

        public DataBean(String img, String img_cover,String content,String name) {
            this.img_cover = img_cover;
            this.img = img;
            this.content = content;
            this.name = name;
        }
    }
}
