package com.aship.alltv.api;

import com.aship.alltv.model.CategoryList;
import com.aship.alltv.model.ChannelList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(HttpParams.SHEET_API_END_POINT)
    Call<ChannelList> getChannelList(@Query("id") String sheetId, @Query("sheet") String sheetName);

    @GET(HttpParams.SHEET_API_END_POINT)
    Call<CategoryList> getCategoryList(@Query("id") String sheetId, @Query("sheet") String sheetName);
}
