package com.starnamu.projcet.memorize_card.fragment_folder;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.starnamu.projcet.memorize_card.FileNameInterface;
import com.starnamu.projcet.memorize_card.autosavesetting.*;

import com.starnamu.projcet.memorize_card.R;

public class SettingFragment extends Fragment implements FileNameInterface {

    private int ToDayWordCounter;
    AutoSaveSetting saveSetting;
    int DefaultsSeekBar = 0;
    SeekBar seekbar;
    EditText choicecount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            saveSetting = new AutoSaveSetting(SaveSettingFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        callProgressbar();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        seekbar = (SeekBar) getActivity().findViewById(R.id.seekbar);
        choicecount = (EditText) getActivity().findViewById(R.id.choicecount);
        seekbar.setProgress(DefaultsSeekBar);
        choicecount.setText(Integer.toString(DefaultsSeekBar));
        getSettingValue();
    }

    public void getSettingValue() {
        try {
            saveSetting = new AutoSaveSetting(SaveSettingFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveSetting.Ready();
        int i = saveSetting.ReadInt("ToDayWordCounter", 0);
        if (i >= 0) {
            seekbar.setProgress(i);
            choicecount.setText(Integer.toString(i));
        }
        saveSetting.EndReady();
    }

    //설정값을  txt파일로 저장
    private void setSettingValue() {
        saveSetting.Ready();
        saveSetting.WriteInt("ToDayWordCounter", ToDayWordCounter);
        saveSetting.CommitWrite();
        saveSetting.EndReady();
    }

    public void callProgressbar() {
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ToDayWordCounter = progress;
                String str = Integer.toString(progress);
                choicecount.setText(str);
                setSettingValue();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dd_fragment_setting, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}