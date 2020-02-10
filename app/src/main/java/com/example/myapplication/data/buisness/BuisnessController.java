package com.example.myapplication.data.buisness;

import com.example.myapplication.data.model.Account;
import com.example.myapplication.data.model.Member;
import com.example.myapplication.data.model.Relation;

import java.util.List;

public class BuisnessController {
    private AccountManager accountManager = new AccountManager();
    private MemberManager memberManager = new MemberManager();
    private RelationManager relationManager = new RelationManager();

    public Account getAccount(String username){
        return accountManager.getAccount(username);
    }

    public void createAccount(Account account){
        return;
    }

    public void setAccount(Account account){
        return;
    }

    public void deleteAccount(Account account) {
        return;
    }

    public List<Member> getMembers(Account account){
        return  memberManager.getMembers(account);
    }

    public void createMember(Member member){
        return;
    }

    public void setMember(Member member){
        return;
    }

    public void deleteMember(Member member){
        return;
    }

    public List<Relation> getRelations(Member member){
        return relationManager.getRelations(member);
    }

    public void createRelation(Relation relation){
        return;
    }

    public void deleteRelation(Relation relation){
        return;
    }
}
