package com.safemybaby.vtree.safemybaby.model;

import java.util.List;

/**
 * Created by longdg123 on 10/14/2016.
 */

public interface CategoryModel {
    int getCatesCount();
    Category getCate(int position);
    List<Category> getAllCate(String url);
    //int insertCate(Note note);
   // boolean loadData();
}
