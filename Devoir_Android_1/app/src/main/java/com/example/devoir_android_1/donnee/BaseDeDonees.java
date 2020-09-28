package com.example.devoir_android_1.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonees extends SQLiteOpenHelper {

    private static BaseDeDonees instance = null;

    public static synchronized BaseDeDonees getInstance(Context contexte) {

        instance = new BaseDeDonees(contexte);
        return instance;
    }

    public static BaseDeDonees getInstance() {

        return instance;
    }

    public BaseDeDonees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BaseDeDonees(Context contexte) {
        super(contexte, "calendrier", null, 1);
    }


    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "create table matchSoccer(id INTEGER PRIMARY KEY, rencontre TEXT, date TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    public void onOpen(SQLiteDatabase db)
    {
/*
        String DELETE = "delete from matchSoccer where 1 = 1";
        db.execSQL(DELETE);

        String INSERT_1 = "insert into matchSoccer(rencontre, date) VALUES('Lyon (OL) contre Paris (PSG)', '01/09/2020')";
        String INSERT_2 = "insert into matchSoccer(rencontre, date) VALUES('Marseille (OM) contre Lille (LOSC)', '05/09/2020')";
        String INSERT_3 = "insert into matchSoccer(rencontre, date) VALUES('Barcelone(FC) contre Real Madrid (CF)', '10/09/2020')";
        String INSERT_4 = "insert into matchSoccer(rencontre, date) VALUES('Barcelone(FC) contre Real Madrid (CF)', '10/09/2020')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);
        db.execSQL(INSERT_3);
        db.execSQL(INSERT_4);
*/
    }

    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2)
    {
        //String DETRUIRE_TABLE = "drop table livre";
        //db.execSQL(DETRUIRE_TABLE);
        String CREER_TABLE = "create table matchSoccer(id INTEGER PRIMARY KEY, rencontre TEXT, date TEXT)";
        db.execSQL(CREER_TABLE);
    }

}
