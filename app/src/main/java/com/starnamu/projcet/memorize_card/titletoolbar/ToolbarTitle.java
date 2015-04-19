package com.starnamu.projcet.memorize_card.titletoolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.starnamu.projcet.memorize_card.R;

/**
 * Created by starnamu on 2015-04-08.
 * Toolbar의 Title을 구성하기 위한 Class
 */
public class ToolbarTitle extends LinearLayout {

    TextView titletext;
    TextView total, completion, progress;

    Context mContext;

    public ToolbarTitle(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ToolbarTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void init() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.0F);

        setLayoutParams(params);
        setOrientation(LinearLayout.HORIZONTAL);


        LayoutInflater inflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View root = inflater.inflate(R.layout.toolbarstat, this, true);

        /**titletext를 이용하여 Title의 이름을 바꿀수 있다.*/
        titletext = (TextView) root.findViewById(R.id.titletext);

        total = (TextView) root.findViewById(R.id.total);
        completion = (TextView) root.findViewById(R.id.completion);
        progress = (TextView) root.findViewById(R.id.progress);

    }
}