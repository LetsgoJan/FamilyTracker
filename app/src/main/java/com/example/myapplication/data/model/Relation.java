package com.example.myapplication.data.model;

import java.util.Enumeration;

public class Relation {
    private Member fromRelation;
    private Member setRelation;
    public enum RelationTypes{HasAsChild, HasAsParent, HasAsPartner};
    private RelationTypes relationType;

    public Relation(Member fromRelation, Member setRelation, RelationTypes relationType) {
        this.fromRelation = fromRelation;
        this.setRelation = setRelation;
        this.relationType = relationType;
    }

    public Member getFromRelation() {
        return fromRelation;
    }

    public void setFromRelation(Member fromRelation) {
        this.fromRelation = fromRelation;
    }

    public Member getSetRelation() {
        return setRelation;
    }

    public void setSetRelation(Member setRelation) {
        this.setRelation = setRelation;
    }

    public RelationTypes getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationTypes relationType) {
        this.relationType = relationType;
    }
}
