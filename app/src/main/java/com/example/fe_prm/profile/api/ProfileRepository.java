package com.example.fe_prm.profile.api;

import com.example.fe_prm.view_your_reservation.api.APIClient;

public class ProfileRepository {
    public static ProfileService getProfileService(){
        return APIClient.getClient().create(ProfileService.class);
    }
}
