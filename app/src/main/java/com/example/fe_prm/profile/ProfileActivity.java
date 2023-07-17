package com.example.fe_prm.profile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fe_prm.HomePage;
import com.example.fe_prm.Loading;
import com.example.fe_prm.MainActivity;
import com.example.fe_prm.R;
import com.example.fe_prm.TableReservationActivity;
import com.example.fe_prm.profile.api.ProfileModel;
import com.example.fe_prm.profile.api.ProfileRepository;

import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout llButtonTableReservation, llButtonContractUs;
    AppCompatActivity backActivity;
    ImageView ivBack, ivLogout, ivEditPhone;
    CircleImageView ivAvatar;
    TextView tvPhone, tvMail, tvName;
    ProfileModel currentUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        setListeners();
        Loading.setLoading(this,true);
        getProfile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        backActivity = (AppCompatActivity) this.getIntent().getSerializableExtra("backActivity");
    }

    private void getProfile(){
        ProfileRepository.getProfileService().getProfile(MainActivity.AUTORIZATION_KEY).enqueue(
                new Callback<ProfileModel>() {
                    @Override
                    public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                        currentUser = response.body();
                        setUserData(response.body());
                        Loading.setLoading(ProfileActivity.this,false);
                    }

                    @Override
                    public void onFailure(Call<ProfileModel> call, Throwable t) {
                        Toast.makeText(ProfileActivity.this, "Failed to load profile", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    private void setUserData(ProfileModel profileModel){
        if (currentUser != null){
            tvMail.setText(currentUser.getEmail());
            tvPhone.setText(currentUser.getPhone());
//            tvName.setText(currentUser.getRole());
        } else Toast.makeText(ProfileActivity.this, "Token was expired", Toast.LENGTH_SHORT).show();
    }
    private void setListeners(){
        ivLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            MainActivity.AUTORIZATION_KEY ="Bearer ";
            startActivity(intent);
        });
        ivBack.setOnClickListener(v -> {
            Intent intent = new Intent(this,
                    backActivity != null?backActivity.getClass(): HomePage.class);
            startActivity(intent);
        });
        ivEditPhone.setOnClickListener(v->{
            EditText editText = new EditText(this);
            editText.setText(tvPhone.getText().toString());
            editText.setInputType(InputType.TYPE_CLASS_PHONE);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            if (editText.getText() == null) alert.setTitle("Update phone: ");
            else alert.setTitle("Thêm phone: ");
            alert.setView(editText,70,0,70,0);
            alert.setPositiveButton("Yes",(dialog, which) -> {
                String text  = String.valueOf(editText.getText());
                if (text.length() == 10 && text.startsWith("0")){
                    Call<ProfileModel> call = ProfileRepository.getProfileService()
                            .updatePhone(MainActivity.AUTORIZATION_KEY, new ProfileModel(editText.getText().toString()));
                    call.enqueue(new Callback<ProfileModel>() {
                        @Override
                        public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                            getProfile();
                            Toast.makeText(ProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<ProfileModel> call, Throwable t) {
                            Toast.makeText(ProfileActivity.this, "Failed to upate", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    editText.setError("Phone need to start with 0 and have exactly 10 numbers.");
                    dialog.dismiss();
                }
            });
            alert.setNegativeButton("No",(dialog, which) -> {});
            alert.show();
        });
        llButtonContractUs.setOnClickListener(v->{
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
//                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
//            }
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0839944818"));
            startActivity(intent);
        });
    }
    private void init(){
        llButtonContractUs = findViewById(R.id.ll_contract_us);
        llButtonTableReservation = findViewById(R.id.ll_table_reservation);
        ivLogout = findViewById(R.id.iv_logout);
        ivAvatar = findViewById(R.id.iv_avatar);
        ivBack = findViewById(R.id.iv_back);
        ivEditPhone = findViewById(R.id.iv_editPhone);
        tvPhone = findViewById(R.id.tv_phone);
        tvMail = findViewById(R.id.tv_mail);
        tvName = findViewById(R.id.tv_name);
    }
}
