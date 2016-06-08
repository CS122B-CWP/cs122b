package com.example.pengyuanfan.fablix;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengyuanfan.fablix.json.LoginResult;
import com.example.pengyuanfan.fablix.login.LoginParser;
import com.example.pengyuanfan.fablix.util.ConnectionState;
import com.example.pengyuanfan.fablix.util.HttpGetThread;
import com.example.pengyuanfan.fablix.util.HttpPostThread;
import com.example.pengyuanfan.fablix.util.SoftKeyBoard;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    LinearLayout mainLayout;
    TextView usernameV;
    TextView passwordV;

    Context appContext;
    Context actContext;

    private ConnectionState cs;

    private URL loginURL;
    private String user;
    private static LoginResult lgR=null;

    private Handler onGetLoginResult=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            Log.d("d","returned");
            if(msg.what== HttpGetThread.success){
                lgR = LoginParser.parse(msg.getData().getString("result"));
                if(lgR!=null){
                    if(lgR.isLogin_result()){
                        Intent loginToSearch = new Intent(LoginActivity.this, Fablix_Search.class);
                        startActivity(loginToSearch);
                        return;
                    }else{
                        Toast.makeText(actContext, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }

            Toast.makeText(actContext, "connection failed", Toast.LENGTH_SHORT).show();

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mainLayout = (LinearLayout) findViewById(R.id.login_main);
        usernameV = (TextView)findViewById(R.id.u_username);
        passwordV = (TextView)findViewById(R.id.u_password);

        appContext = this.getApplicationContext();
        actContext = this.getBaseContext();

        cs = new ConnectionState(appContext);

        SoftKeyBoard.setupUI(mainLayout, this);
        try {
            loginURL = new URL(appContext.getString(R.string.fablix_Url)+appContext.getString(R.string.fablix_loginUrl));
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

    }

    public static LoginResult getLgR(){
        return lgR;
    }

    public void login(View view){
        String username = usernameV.getText().toString().trim(),
               password = passwordV.getText().toString().trim();
        if(username.equals("")){
            Toast.makeText(actContext, actContext.getText(R.string.no_username), Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.equals("")){
            Toast.makeText(actContext, actContext.getText(R.string.no_password), Toast.LENGTH_SHORT).show();
            return;
        }
        if(cs.isConnectingToInternet()) {
            progressDialog = ProgressDialog.show(this, "", "Downloading...");
            new HttpPostThread(loginURL, onGetLoginResult,
                    "username=" + username +
                    "&" +
                    "password=" + password).start();
        }
    }
}
