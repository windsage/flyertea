package com.chao.flyertea.net;

import com.chao.flyertea.bean.CreditVariable;
import com.chao.flyertea.bean.FavorVariable;
import com.chao.flyertea.bean.ForumVariable;
import com.chao.flyertea.bean.LoginMsg;
import com.chao.flyertea.bean.LoginVariable;
import com.chao.flyertea.bean.ReplyResult;
import com.chao.flyertea.bean.Result;
import com.chao.flyertea.bean.ThreadVariable;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FlyerteaApi {

    @FormUrlEncoded
    @POST("api/mobile/index.php?")
    Call<Result<LoginVariable, LoginMsg>> login(@FieldMap Map<String, String> params);

    @GET("source/plugin/mobile/mobile.php?")
    Call<Result<ForumVariable, Object>> getForumListByFid();

    @GET("source/plugin/mobile/mobile.php?")
    Call<Result<ThreadVariable, Object>> getResponseListByTid();

    @FormUrlEncoded
    @POST("source/plugin/mobile/mobile.php?")
    Call<ReplyResult> postThreadBySmart(@FieldMap Map<String, String> params);


    // https://www.flyertea.com/source/plugin/mobile/mobile.php?module=basicdata&type=forumlist&version=5&appkey=98bf6a79892a1148a1&token=MjA4NzA1M3wxNTg4NzgyNzQ1MzM5fGFlOGJiNDNjNmJhNzMxOWViY2Q0MDRhYTliNzAzZDc1OGY3NWZlNjdlOGY2NzhhOGUxOTc2OGE3MjMzMzcyNmE%253D&appversion=7.18.0
    @GET("source/plugin/mobile/mobile.php?")
    Call<Result<FavorVariable, Object>> getMyFavourite();

   // https://www.flyertea.com/source/plugin/mobile/mobile.php?module=users&version=6&type=apptask&method=get&appkey=98bf6a79892a1148a1&token=MjA4NzA1M3wxNTk0MzExMTg1MDg3fGMwNWJlOTRiMzRjODE1NTRiMjljMzRiNWI5YTUyZTMyMjBjMDgyZWZmOTE4MWUwZmM0MGQwZjQ4NTUwOTQwMjU%253D&appversion=7.20.1&taskid=12
    @GET("source/plugin/mobile/mobile.php?")
    Call<Result<CreditVariable,Object>>  getCredit();
}
