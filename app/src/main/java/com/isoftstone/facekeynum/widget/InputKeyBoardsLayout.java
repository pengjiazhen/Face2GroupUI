package com.isoftstone.facekeynum.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.isoftstone.facekeynum.R;

/**
 * Created by jiazhen on 17/12/15.
 * Desc:
 */

public class InputKeyBoardsLayout extends LinearLayout {


    private GridView mGridView;
    private ArrayAdapter<String> mAdapter;
    private OnBoardKeyClickListener mOnBoardKeyClickListener;

    public InputKeyBoardsLayout(Context context) {
        this(context, null);
    }

    public InputKeyBoardsLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputKeyBoardsLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_key_board, this, true);

        mGridView = findViewById(R.id.grid_view);
        mAdapter = new ArrayAdapter<>(context, R.layout.item_key_board);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String textNum = parent.getAdapter().getItem(position).toString();
                if (mOnBoardKeyClickListener != null) {
                    mOnBoardKeyClickListener.onClick(textNum);
                }
            }
        });
    }

    public void setKeyData(String... keyData) {
        mAdapter.addAll(keyData);
    }

    public void setOnBoardKeyClickListener(OnBoardKeyClickListener onBoardKeyClickListener) {
        this.mOnBoardKeyClickListener = onBoardKeyClickListener;
    }

    public interface OnBoardKeyClickListener {
        void onClick(String keyNum);
    }
}
