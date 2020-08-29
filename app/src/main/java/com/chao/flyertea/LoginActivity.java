package com.chao.flyertea;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.chao.flyertea.bean.LoginMsg;
import com.chao.flyertea.bean.LoginVariable;
import com.chao.flyertea.bean.Result;
import com.chao.flyertea.net.FlyerteaApi;
import com.chao.flyertea.util.Constants;
import com.chao.flyertea.util.RequestUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "XUCHAO";
    private Button btn1;
    private Button btn2;

    private LoginVariable login = null;
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestPermission();
        initViews();
    }


    private void initViews() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("飞翔的荷兰号", "uranus0127");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("希埃尔", "uranus0127");
            }
        });
    }


    private void login(String username, String password) {
        HashMap<String, String> query = RequestUtils.createRequestParams();
        query.put("mobile", "yes");
        query.put("cookietime", "2592000");
        query.put("filter", "typeid");
        query.put("module", Constants.MODULE_LOGIN);
        query.put("loginsubmit", "yes");
        query.put("loginfield", "auto");
        query.put("transcode", "yes");
        query.put("version", "4");

        HashMap<String, String> info = new HashMap<>();
        info.put("username", username);
        info.put("password", password);

        FlyerteaApi request = RequestUtils.createRequest(query);

        request.login(info).enqueue(new Callback<Result<LoginVariable, LoginMsg>>() {
            @Override
            public void onResponse(Call<Result<LoginVariable, LoginMsg>> call, Response<Result<LoginVariable, LoginMsg>> response) {
                Result<LoginVariable, LoginMsg> loginResult = response.body();
                if (loginResult.getMessage().getMessageval().equals(Constants.LOGIN_SUCCESS)) {
                    //登录成功
                    LoginVariable bean = loginResult.getVariables();
                    login = bean;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("login", login);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<Result<LoginVariable, LoginMsg>> call, Throwable t) {
                Log.e(TAG, "--------------------------");
            }
        });
    }


    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1091);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1091) {
            int sum = 0;
            for (int i : grantResults) {
                sum += i;
            }
            boolean isGrant = sum == 0;
            if (!isGrant) {
                Toast.makeText(LoginActivity.this, "please grant storage permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
