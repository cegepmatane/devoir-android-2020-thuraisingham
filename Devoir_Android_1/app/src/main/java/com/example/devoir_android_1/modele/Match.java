package com.example.devoir_android_1.modele;

import java.util.HashMap;

public class Match {
    protected String rencontre;
    protected String date;
    protected int id;

    public Match(String rencontre, String date, int id) {
        this.rencontre = rencontre;
        this.date = date;
        this.id = id;
    }

    public String getRencontre() {
        return rencontre;
    }

    public void setRencontre(String rencontre) {
        this.rencontre = rencontre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, String> obtenirMatchPourAfficher()
    {
        HashMap<String, String> matchPourAfficher = new HashMap<String, String>();
        matchPourAfficher.put("rencontre", this.rencontre);
        matchPourAfficher.put("date", this.date);
        matchPourAfficher.put("id", "" + this.id);
        return matchPourAfficher;
    }
}
