package com.starnamu.projcet.memorize_card.expandable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.starnamu.projcet.memorize_card.R;


/**
 * Created by starnamu on 2015-03-31.
 */
public class ToolBarState extends LinearLayout {

    Context mContext;

    public ToolBarState(Context context) {
        super(context);
        mContext = context;
    }

    public ToolBarState(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void init() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.toolbarstat, this);
    }
}
