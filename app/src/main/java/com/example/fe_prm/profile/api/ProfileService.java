package com.example.fe_prm.profile.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ProfileService {
    String PROFILE = "api/profile";

    @GET(PROFILE)
    Call<ProfileModel> getProfile(@Header("Authorization") String token);

    @POST(PROFILE + "/update-phone")
    Call<ProfileModel> updatePhone(@Header("Authorization") String token, @Body ProfileModel profileModel);
}
