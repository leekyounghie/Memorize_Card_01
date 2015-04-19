package com.starnamu.projcet.memorize_card.fragment_folder;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.starnamu.projcet.memorize_card.R;

public class SideFragment extends Fragment {


    Button Study, Setting, Statistics, DBControl;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dc_sidefragment, container, true);

        return view;
    }

    public interface choiceFragmentListener {
        public void onClickChoice(int id);
    }

    choiceFragmentListener choiceListener;

    @Override
    public void onResume() {
        super.onResume();
        /**버튼 생성후 리스너 장착*/
        Study = (Button) view.findViewById(R.id.Study);
        Study.setOnClickListener(onClickListener);
        Setting = (Button) view.findViewById(R.id.Setting);
        Setting.setOnClickListener(onClickListener);
        Statistics = (Button) view.findViewById(R.id.Statistics);
        Statistics.setOnClickListener(onClickListener);
        DBControl = (Button) view.findViewById(R.id.DBControl);
        DBControl.setOnClickListener(onClickListener);
    }

    /**
     * 버튼에 장착된 리스너 액션 정의
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /**버튼이 크릭될때마다 choiceFragmentListener 리스너 호출
             * choiceFragmentListener이 호출되면 해당 Listener를 implement한 Class의
             * Overriding한 method가 실행*/
            choiceListener.onClickChoice(v.getId());
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        choiceListener = (choiceFragmentListener) activity;
    }
}
