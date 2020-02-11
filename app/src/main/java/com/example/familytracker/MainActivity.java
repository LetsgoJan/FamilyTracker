package com.example.familytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.familytracker.Model.Account;
import com.example.familytracker.Model.Member;
import com.example.familytracker.buisnessLayer.AccountManager;
import com.example.familytracker.dataLayer.AccountDbManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addButton;
    private Button loginButton;
    private EditText usernameField;
    private EditText passwordField;
    private AccountManager accountManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountManager = new AccountManager(new AccountDbManager(this));

        addButton = (Button) findViewById(R.id.bt_main_createnewfamilyaccount) ;
        loginButton = (Button) findViewById(R.id.bt_main_login);
        usernameField = (EditText) findViewById(R.id.et_main_familyname);
        passwordField = (EditText) findViewById(R.id.et_main_password);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountManager.getAccountByUsernameAndPassword(new Account(usernameField.getText().toString(),passwordField.getText().toString(),""), MainActivity.this);
            }
        });
    }

    public void onLogin(Boolean succes){
        if (succes){
            ArrayList<Member> members = new ArrayList<Member>();
            startActivity(new Intent(this, MemberListAcitvity.class).putExtra("members", members));
        }
    }
}
