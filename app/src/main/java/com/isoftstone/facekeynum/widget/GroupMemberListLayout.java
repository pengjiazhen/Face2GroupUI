package com.isoftstone.facekeynum.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.isoftstone.facekeynum.R;

/**
 * Created by jiazhen on 17/12/19.
 * Desc:
 */

public class GroupMemberListLayout extends RelativeLayout {

    private TextView mTvGroupKey;
    private RecyclerView mRvGroupMember;
    private TextView mTvGroupEnter;

    private OnEnterKeyClickListener mOnEnterKeyClickListener;

    public GroupMemberListLayout(Context context) {
        this(context,null);
    }

    public GroupMemberListLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GroupMemberListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_member_group,this,true);
        mTvGroupKey=findViewById(R.id.tv_group_key);
        mTvGroupEnter=findViewById(R.id.tv_group_enter);
        mRvGroupMember = findViewById(R.id.rl_group_member);

        mRvGroupMember.setLayoutManager(new GridLayoutManager(context,5));
        mTvGroupEnter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEnterKeyClickListener!=null){
                    mOnEnterKeyClickListener.onKeyClick("6326722377832783278");
                }
            }
        });
    }

    public void setGroupMemberAdapter(RecyclerView.Adapter adapter){
        mRvGroupMember.setAdapter(adapter);
    }

    public void setGroupKey(String keyText){
        if (keyText!=null){
            mTvGroupKey.setText(keyText);
        }
    }

   public void setOnEnterKeyClickListener(OnEnterKeyClickListener onEnterKeyClickListener){
        this.mOnEnterKeyClickListener = onEnterKeyClickListener;
   }

    public interface OnEnterKeyClickListener{
       void onKeyClick(String groupId);
    }
}
