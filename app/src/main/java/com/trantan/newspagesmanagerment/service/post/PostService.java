package com.trantan.newspagesmanagerment.service.post;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.constants.RequestConstants;
import com.trantan.newspagesmanagerment.model.response.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostService {
    @GET("/api/post/posts")
    Observable<ResponseBody<Page<Post>>> getPosts(
            @Query("websiteID") Integer websiteID,
            @Query("categoryID") Integer categoryID,
            @Query(RequestConstants.SORT_BY) List<String> sortBy,
            @Query(RequestConstants.SORT_TYPE) List<String> sortType,
            @Query(RequestConstants.PAGE_INDEX) int pageIndex,
            @Query(RequestConstants.PAGE_SIZE) int pageSize
    );
}
