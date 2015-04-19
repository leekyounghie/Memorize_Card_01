package note.co.kr.wordsnote09;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class DataBaseControl {
    DataBaseService database;
    Context mContext;

    public DataBaseControl(Context context) {
        mContext = context;
        initData();
    }

    public void initData() {
        if (database != null) {
            database.close();
            database = null;
        }

        database = DataBaseService.getInstance(mContext);
        database.open();
        database.initData();
    }

    public String AddCursorData(Cursor outCursor) {
        String parma = "";
        int recordCount = outCursor.getCount();

        //adapter.clear();

        // get column index
        int wordCodeCol = outCursor.getColumnIndex("WORD");


        if (recordCount > 0) {
            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();
                String wordCode = outCursor.getString(wordCodeCol);

                //adapter.set(i, wordCode);
                parma += wordCode + ", ";
                if (i == 10) {
                    break;
                }
            }
        }

        outCursor.close();
        return parma;
    }

    public void goLev1(View v) {
        String queryData = "";
        Cursor cursor = database.queryWordsTable(1, 15);
        queryData = AddCursorData(cursor);
    }

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

    public void upWord1(View v) {
        database.queryWordsUpdate("UP", "when");
    }

    public void upWord2(View v) {
        database.queryWordsUpdate("UP", "id");
    }

    public void upWord3(View v) {
        database.queryWordsUpdate("UP", "sure");
    }
}
