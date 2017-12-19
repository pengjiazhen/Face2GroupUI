package com.isoftstone.facekeynum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.isoftstone.facekeynum.widget.FaceGroupKeyLayout;
import com.isoftstone.facekeynum.widget.GroupMemberListLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FaceGroupKeyLayout mFaceGroupKeyLayout;
    private GroupMemberListLayout mGroupMemberListLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGroupMemberListLayout = findViewById(R.id.face_group_member);
        mGroupMemberListLayout.setOnEnterKeyClickListener(new GroupMemberListLayout.OnEnterKeyClickListener() {
            @Override
            public void onKeyClick(String groupId) {
                Toast.makeText(MainActivity.this,"进入群:"+groupId, Toast.LENGTH_SHORT).show();
            }
        });
        mFaceGroupKeyLayout = findViewById(R.id.face_key);
        mFaceGroupKeyLayout.setOnKeyInputFinish(new FaceGroupKeyLayout.OnKeyInputFinish() {
            @Override
            public void onFinish(List<String> keys) {
                mFaceGroupKeyLayout.setVisibility(View.GONE);
                mGroupMemberListLayout.setVisibility(View.VISIBLE);
                mGroupMemberListLayout.setGroupKey(keys.get(0)+keys.get(1)+keys.get(2)+keys.get(3));
                //Toast.makeText(MainActivity.this,"完成输入:"+keys.toString(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
