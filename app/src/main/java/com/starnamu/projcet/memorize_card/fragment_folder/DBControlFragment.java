package com.starnamu.projcet.memorize_card.fragment_folder;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.starnamu.projcet.memorize_card.R;
import com.starnamu.projcet.memorize_card.database_folder.DataBaseService;

public class DBControlFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "MainActivity";
    DataBaseService database;

    public DBControlFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (database != null) {
            database.close();
            database = null;
        }

        database = DataBaseService.getInstance(getActivity());
        database.open();
//        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Button initData = (Button) getActivity().findViewById(R.id.initData);
        initData.setOnClickListener(this);

        Button lev1 = (Button) getActivity().findViewById(R.id.lev1);
        lev1.setOnClickListener(this);

        Button lev2 = (Button) getActivity().findViewById(R.id.lev2);
        lev2.setOnClickListener(this);

        Button lev3 = (Button) getActivity().findViewById(R.id.lev3);
        lev3.setOnClickListener(this);

        return inflater.inflate(R.layout.dd_fragment_dbcontrol, container, false);
    }

    @Override
    public void onClick(View v) {

        String queryData = "";
        Cursor cursor;
        switch (v.getId()) {
            case R.id.initData:
                database.initData();
                break;

            case R.id.lev1:
                cursor = database.queryWordsTable(1, 30);
                queryData = AddCursorData(cursor);
                Toast.makeText(getActivity(), queryData, Toast.LENGTH_LONG).show();
                break;

            case R.id.lev2:
                cursor = database.queryWordsTable(2, 15);
                queryData = AddCursorData(cursor);
                Toast.makeText(getActivity(), queryData, Toast.LENGTH_LONG).show();
                break;

            case R.id.lev3:
                break;
        }

    }

/*
    public void initData() {
        database.initData();
    }*/

    public String AddCursorData(Cursor outCursor) {
        String parma = "";
        int recordCount = outCursor.getCount();
        println("cursor count : " + recordCount + "\n");

        //adapter.clear();

        // get column index
        int wordCodeCol = outCursor.getColumnIndex("WORD");

        // get reaources
        Resources res = getResources();

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

/*
    public void goLev1(View v) {
        String queryData = "";
        Cursor cursor = database.queryWordsTable(1, 30);
        queryData = AddCursorData(cursor);
        Toast.makeText(getActivity(), queryData, Toast.LENGTH_LONG).show();
    }

    public void goLev2(View v) {
        String queryData = "";
        Cursor cursor = database.queryWordsTable(2, 15);
        queryData = AddCursorData(cursor);
        Toast.makeText(getActivity(), queryData, Toast.LENGTH_LONG).show();
    }

    public void goLev3(View v) {
        String queryData = "";
        Cursor cursor = database.queryWordsTable(3, 15);
        queryData = AddCursorData(cursor);
        Toast.makeText(getActivity(), queryData, Toast.LENGTH_LONG).show();
    }*/

    public void upWord1(View v) {
        database.queryWordsUpdate("UP", "when");
    }

    public void upWord2(View v) {
        database.queryWordsUpdate("UP", "id");
    }

    public void upWord3(View v) {
        database.queryWordsUpdate("UP", "sure");
    }

    public static void println(String msg) {
        Log.d(TAG, msg);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


}
