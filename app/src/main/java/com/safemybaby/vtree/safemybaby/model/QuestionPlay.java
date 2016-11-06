package com.safemybaby.vtree.safemybaby.model;

import java.util.List;

/**
 * Created by longdg123 on 10/23/2016.
 */

public class QuestionPlay {

    /**
     * data : [{"id":1,"id_category":"8","result":"1","link_image":"http://i.imgur.com/OUDaF7W.jpg","link_explain":"http://i.imgur.com/nbYOVcj.jpg","name_image":"1"},{"id":2,"id_category":"8","result":"1","link_image":"http://i.imgur.com/drOm8Hf.jpg","link_explain":"http://i.imgur.com/1v7CzcC.jpg","name_image":"2"},{"id":7,"id_category":"8","result":"1","link_image":"http://i.imgur.com/XPQSqHW.jpg","link_explain":"http://i.imgur.com/h3XsRre.jpg","name_image":"3"},{"id":8,"id_category":"8","result":"1","link_image":"http://i.imgur.com/uOtuN34.jpg","link_explain":"http://i.imgur.com/ee52rhw.jpg","name_image":"4"},{"id":9,"id_category":"8","result":"1","link_image":"http://i.imgur.com/ZyumBRz.jpg","link_explain":"http://i.imgur.com/N5rmFc3.jpg","name_image":"5"},{"id":10,"id_category":"8","result":"1","link_image":"http://i.imgur.com/zw2Hg3E.jpg","link_explain":"http://i.imgur.com/IF35e6B.jpg","name_image":"6"},{"id":11,"id_category":"8","result":"1","link_image":"http://i.imgur.com/1E2h3v2.jpg","link_explain":"http://i.imgur.com/bu0gf89.jpg","name_image":"7"},{"id":12,"id_category":"8","result":"1","link_image":"http://i.imgur.com/DRu3f5k.jpg","link_explain":"http://i.imgur.com/2JSEt17.jpg","name_image":"8"},{"id":13,"id_category":"8","result":"1","link_image":"http://i.imgur.com/WZHVhPj.jpg","link_explain":"http://i.imgur.com/o6Pyvxu.jpg","name_image":"9"},{"id":14,"id_category":"8","result":"1","link_image":"http://i.imgur.com/fNECGIj.png","link_explain":"http://i.imgur.com/V9AlOHy.png","name_image":"Can day dien"},{"id":15,"id_category":"8","result":"1","link_image":"http://i.imgur.com/i7Db9j1.png","link_explain":"http://i.imgur.com/YATP7rk.png","name_image":"Choc o dien"}]
     * limit : 30
     * offset : 11
     */

    private int limit;
    private int offset;
    /**
     * id : 1
     * id_category : 8
     * result : 1
     * link_image : http://i.imgur.com/OUDaF7W.jpg
     * link_explain : http://i.imgur.com/nbYOVcj.jpg
     * name_image : 1
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
        private String result;
        private String link_image;
        private String link_explain;
        private String name_image;

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

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getLink_image() {
            return link_image;
        }

        public void setLink_image(String link_image) {
            this.link_image = link_image;
        }

        public String getLink_explain() {
            return link_explain;
        }

        public void setLink_explain(String link_explain) {
            this.link_explain = link_explain;
        }

        public String getName_image() {
            return name_image;
        }

        public void setName_image(String name_image) {
            this.name_image = name_image;
        }

        public DataBean(String result,String link_image,String link_explain){
            this.link_image = link_image;
            this.result = result;
            this.link_explain = link_explain;
        }
    }
}
