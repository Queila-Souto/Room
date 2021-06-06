package com.example.fragments;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = Questoes.class,version =2)
public abstract class Database extends RoomDatabase {
    //Recupera DAO
   public abstract DAO getDAO();

   //Instancia o para BD
    private static Database INSTANCE;

    //Modelo Singleton
    public static Database getBancodedados(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    //Cria o modelo do banco de dados
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "perguntas_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }


}
