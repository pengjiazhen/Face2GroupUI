package com.isoftstone.facekeynum.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.isoftstone.facekeynum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiazhen on 17/12/15.
 * Desc:
 */

public class FaceGroupKeyLayout extends RelativeLayout {

    private InputKeyBoardsLayout mKeyBoardLayout;
    private KeyShowLayout mKeyShowLayout;
    private OnKeyInputFinish mOnKeyInputFinish;


    private List<String> mKeyTexts = new ArrayList<>();

    public FaceGroupKeyLayout(Context context) {
        this(context, null);
    }

    public FaceGroupKeyLayout(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public FaceGroupKeyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initData();
    }

    private void initData() {

    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_face_group_key, this, true);
        mKeyShowLayout = findViewById(R.id.key_show);
        mKeyBoardLayout = findViewById(R.id.key_board);
        mKeyBoardLayout.setKeyData("1", "2", "3",
                "4", "5", "6",
                "7", "8", "9",
                " ", "0", "x");
        mKeyBoardLayout.setOnBoardKeyClickListener(new InputKeyBoardsLayout.OnBoardKeyClickListener() {
            @Override
            public void onClick(String keyNum) {
                if (" ".equals(keyNum)) return;
                if ("x".equals(keyNum)) {
                    //删除
                    if (mKeyTexts.size() >= 1) {
                        mKeyTexts.remove(mKeyTexts.size() - 1);
                    }
                } else {
                    mKeyTexts.add(keyNum);
                }
                mKeyShowLayout.setKeyText(mKeyTexts);
                if (mKeyShowLayout.isFull(mKeyTexts.size())) {
                    if (mOnKeyInputFinish != null) {
                        mOnKeyInputFinish.onFinish(mKeyTexts);
                    }
                }
            }
        });
    }

    public void setOnKeyInputFinish(OnKeyInputFinish onKeyInputFinish) {
        this.mOnKeyInputFinish = onKeyInputFinish;
    }

    public interface OnKeyInputFinish {
        void onFinish(List<String> keys);
    }

}
