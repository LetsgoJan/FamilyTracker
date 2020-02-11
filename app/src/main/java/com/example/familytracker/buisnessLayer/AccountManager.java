package com.example.familytracker.buisnessLayer;

import com.example.familytracker.AccountActivity;
import com.example.familytracker.Model.Account;
import com.example.familytracker.dataLayer.AccountDbManager;

public class AccountManager {
    private AccountDbManager accountDbManager;

    public AccountManager(AccountDbManager accountDbManager){
        this.accountDbManager = accountDbManager;
    }

    public void CreateAccount(Account account, AccountActivity accountActivity){
        accountDbManager.createAccount(account, accountActivity);
    }
}
