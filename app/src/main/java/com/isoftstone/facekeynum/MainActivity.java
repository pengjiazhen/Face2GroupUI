package com.isoftstone.facekeynum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.isoftstone.facekeynum.widget.FaceGroupKeyLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FaceGroupKeyLayout mFaceGroupKeyLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFaceGroupKeyLayout = findViewById(R.id.face_key);
        mFaceGroupKeyLayout.setOnKeyInputFinish(new FaceGroupKeyLayout.OnKeyInputFinish() {
            @Override
            public void onFinish(List<String> keys) {
                Toast.makeText(MainActivity.this,"完成输入:"+keys.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
