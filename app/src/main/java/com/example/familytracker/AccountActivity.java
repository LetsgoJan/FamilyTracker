package com.example.familytracker;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familytracker.Model.Account;
import com.example.familytracker.Model.MyEventListener;
import com.example.familytracker.buisnessLayer.AccountManager;
import com.example.familytracker.dataLayer.AccountDbManager;
import com.example.familytracker.dataLayer.Volley;

public class AccountActivity extends AppCompatActivity implements MyEventListener {

    private Button addButton;
    private EditText usernameField;
    private EditText passwordField;
    private EditText passwordFieldConfirm;
    private EditText adminEmailField;
    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        accountManager = new AccountManager(new AccountDbManager(this.getApplicationContext()));
        final Volley volley = Volley.getInstance(this.getApplicationContext());

        addButton = (Button) findViewById(R.id.bt_account_create_account) ;
        usernameField = (EditText) findViewById(R.id.et_account_familyname);
        adminEmailField = (EditText) findViewById(R.id.et_account_admin_email_address);
        passwordField = (EditText) findViewById(R.id.et_account_password) ;
        passwordFieldConfirm = (EditText) findViewById(R.id.et_account_password_confirm) ;


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Account account = new Account(usernameField.getText().toString(), passwordField.getText().toString(), adminEmailField.getText().toString());
//                accountManager.CreateAccount(account);

                volley.sendRequest("");
            }
        });

        usernameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passwordField.getText() != passwordFieldConfirm.getText()){
                    passwordFieldConfirm.setBackgroundColor(4);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(passwordField.getText() != passwordFieldConfirm.getText()){
                    passwordFieldConfirm.setBackgroundColor(4);
                }
            }
        });
    }

    @Override
    public void onEventCompleted() {

    }

    @Override
    public void onEventFailed() {

    }
}
