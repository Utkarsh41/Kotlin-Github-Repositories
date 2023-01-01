package com.utkarsh.githubkotlin.Services;

import com.utkarsh.githubkotlin.LanguageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {
//    @GET("/search/repositories?q=""+language:kotlin/")
//    Call<LanguageModel> getAllLanguage(@Url String url);

//    https://api.github.com -->Base url
//    /search/repositories?q=%22%22+language:kotlin&pushed:%3E2022-12-19
    @GET("/search/repositories?q=language:kotlin&pushed:>2022-12-19")
//    &pushed:>2022-12-19
    Call<LanguageModel> getItems();



//    This is final url
//    https://api.github.com/search/repositories?q=language:kotlin&pushed:>2022-12-19


}
