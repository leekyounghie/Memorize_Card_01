package com.starnamu.projcet.memorize_card.card_viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.starnamu.projcet.memorize_card.R;

import java.util.ArrayList;

/**
 * Created by youmyeongsic on 15. 4. 11..
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<WordCard> wordCardArrayList = new ArrayList<WordCard>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        String Word = wordCardArrayList.get(position).getWord();
        int Value = wordCardArrayList.get(position).getLevel();
        String Translate = wordCardArrayList.get(position).getTranslate();

        PageFragment pageFragment = new PageFragment(Word, Value, Translate);

        return pageFragment;
    }

    @Override
    public int getCount() {
        return wordCardArrayList.size();
    }

    /*
    * 뷰페이져 안에 있는 Fragment입니다.  support.v4.frament입니다.
    * */
    public class PageFragment extends Fragment {

        String word, translate;
        int value;
        Animation animation;
        TextView viewPager_tranlate;
        TextView viewPager_level;
        TextView viewPager_word;

        public PageFragment(String word, int value, String translate) {
            this.word = word;
            this.value = value;
            this.translate = translate;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.viewpager_pagefragment, container, false);
            viewPager_word = (TextView) view.findViewById(R.id.main_viewpager_word);
            viewPager_level = (TextView) view.findViewById(R.id.main_viewpager_level);
            viewPager_tranlate = (TextView) view.findViewById(R.id.main_viewpager_translate);
            viewPager_tranlate.setVisibility(View.INVISIBLE);
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.flow);
            animation.setAnimationListener(new FlowAnimationListener());
            Button tranlate_button = (Button) view.findViewById(R.id.translate_button);

            tranlate_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager_tranlate.setVisibility(View.VISIBLE);
                    viewPager_tranlate.startAnimation(animation);
                }
            });
            viewPager_word.setText(word);
            viewPager_level.setText("Level : " + value);
            viewPager_tranlate.setText(translate);
            return view;
        }

        private class FlowAnimationListener implements Animation.AnimationListener {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        }
    }

}