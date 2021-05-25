package com.trantan.newspagesmanagerment.service;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.constants.RequestConstants;
import com.trantan.newspagesmanagerment.model.response.Website;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebsiteService {
    @GET("/api/website/websites")
    Observable<ResponseBody<Page<Website>>> getPageWebsites(
            @Query(RequestConstants.SORT_BY) List<String> sortBy,
            @Query(RequestConstants.SORT_TYPE) List<String> sortType,
            @Query(RequestConstants.PAGE_INDEX) int pageIndex,
            @Query(RequestConstants.PAGE_SIZE) int pageSize
    );
}
