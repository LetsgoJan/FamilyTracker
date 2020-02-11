package com.example.familytracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.familytracker.Model.Account;

public class MemberListAcitvity extends AppCompatActivity {
    private Button addMemberButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        addMemberButton = (Button) findViewById(R.id.bt_view_add_member);
        String str = getIntent().getParcelableExtra("account");

        addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberListAcitvity.this, MemberAddActivity.class).putExtra("account", new Account("accountname","","")));
            }
        });
    }
}
