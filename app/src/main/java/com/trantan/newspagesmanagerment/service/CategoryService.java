package com.trantan.newspagesmanagerment.service;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.constants.RequestConstants;
import com.trantan.newspagesmanagerment.model.response.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CategoryService {
    @POST("/api/category/retrieve-categories")
    Observable<ResponseBody<Page<Category>>> retrieveCategories();

    @GET("/api/category/categories")
    Observable<ResponseBody<Page<Category>>> getCategories(
            @Query(RequestConstants.SORT_BY) List<String> sortBy,
            @Query(RequestConstants.SORT_TYPE) List<String> sortType,
            @Query(RequestConstants.PAGE_INDEX) int pageIndex,
            @Query(RequestConstants.PAGE_SIZE) int pageSize
    );
}
