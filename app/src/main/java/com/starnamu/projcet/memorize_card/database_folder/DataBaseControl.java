package com.starnamu.projcet.memorize_card.database_folder;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.starnamu.projcet.memorize_card.FileNameInterface;
import com.starnamu.projcet.memorize_card.autosavesetting.AutoSaveSetting;
import com.starnamu.projcet.memorize_card.card_viewpager.WordCard;

import java.util.ArrayList;


public class DataBaseControl implements FileNameInterface {
    ArrayList<WordCard> Cards;
    DataBaseService database;
    Context mContext;
    AutoSaveSetting getSetting;
    int ToDayWordCounter;
    Datainterface datainterface;

    public DataBaseControl(Context context) {
        mContext = context;
        getSettingValue();
        initData();
        goLev1();
        datainterface.passArrayList(Cards);
    }

    public void initData() {
        if (database != null) {
            database.close();
            database = null;
        }

        database = DataBaseService.getInstance(mContext);
        database.open();
        database.initData();
        Cards = new ArrayList<WordCard>();

    }

    public void getSettingValue() {
        try {
            getSetting = new AutoSaveSetting(SaveSettingFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSetting.Ready();
        ToDayWordCounter = getSetting.ReadInt("ToDayWordCounter", 1);
        getSetting.EndReady();
    }

    public ArrayList<WordCard> AddCursorData(Cursor outCursor) {
        ArrayList<WordCard> parma = null;
        int recordCount = outCursor.getCount();

        //adapter.clear();

        // get column index
        int wordword = outCursor.getColumnIndex("WORD");
        int wordlevel = outCursor.getColumnIndex("LEV");
        int wordtranslate = outCursor.getColumnIndex("MEANING");


        if (recordCount > 0) {
            //seekbar의 설정값 필요
            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();
                String wordCode = outCursor.getString(wordword);
                int level = Integer.parseInt(outCursor.getString(wordlevel));
                String translate = outCursor.getString(wordtranslate);

                //adapter.set(i, wordCode);
                parma.add(new WordCard(wordCode, level, translate));
            }
        }

        outCursor.close();
        return parma;
    }

    public void goLev1() {
        Cursor cursor = database.queryWordsTable(1, ToDayWordCounter);
        Cards = AddCursorData(cursor);
    }

    public void upWord1(View v) {
        database.queryWordsUpdate("UP", "when");
    }

}

/*

    public void goLev2(View v) {
        String queryData = "";
        Cursor cursor = database.queryWordsTable(2, 15);
        queryData = AddCursorData(cursor);
    }

    public void goLev3(View v) {
        String queryData = "";
        Cursor cursor = database.queryWordsTable(3, 15);
        queryData = AddCursorData(cursor);
    }
*/

