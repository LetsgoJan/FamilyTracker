package com.example.familytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.familytracker.buisnessLayer.AccountManager;

public class MainActivity extends AppCompatActivity {

    private Button addButton;
    private AccountManager accountManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        context = this.getApplicationContext();
//        buisnessController = new BuisnessController(context);
//        accountManager = buisnessController.getAccountManager();

        addButton = (Button) findViewById(R.id.bt_main_createnewfamilyaccount) ;
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
            }
        });
    }


}
