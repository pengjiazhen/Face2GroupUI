package com.isoftstone.facekeynum.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.isoftstone.facekeynum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiazhen on 17/12/15.
 * Desc:
 */

public class KeyShowLayout extends LinearLayout {

    public static final int MAX_KEY_NUM = 4;

    private EditText etKey1, etKey2, etKey3, etKey4;

    private TextWatcher mTextWatcher;

    private KeyListener mKeyListener;

    public KeyShowLayout(Context context) {
        this(context, null);
    }

    public KeyShowLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyShowLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_key_show, this, true);
        etKey1 = findViewById(R.id.key_1);
        etKey2 = findViewById(R.id.key_2);
        etKey3 = findViewById(R.id.key_3);
        etKey4 = findViewById(R.id.key_4);

        ArrayList<View> views = new ArrayList<>();
        views.add(etKey1);
        views.add(etKey2);
        views.add(etKey3);
        views.add(etKey4);
        addFocusables(views, FOCUS_RIGHT);

        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0)return;
                int nextFocusRightId = getFocusedChild().findFocus().getNextFocusRightId();
                View nextFocusView = findViewById(nextFocusRightId);
                if (nextFocusView != null) {
                    nextFocusView.requestFocus();
                }
            }
        };

        etKey1.addTextChangedListener(mTextWatcher);
        etKey2.addTextChangedListener(mTextWatcher);
        etKey3.addTextChangedListener(mTextWatcher);
        etKey4.addTextChangedListener(mTextWatcher);

       mKeyListener = new KeyListener() {
           @Override
           public int getInputType() {
               return InputType.TYPE_CLASS_NUMBER;
           }

           @Override
           public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
               if (event.getAction() == KeyEvent.KEYCODE_DEL) {
                   if (text.length() == 0) {
                       int nextFocusForwardId = view.getNextFocusForwardId();
                       View nextFocusForwardView = findViewById(nextFocusForwardId);
                       if (nextFocusForwardView != null) {
                           ((EditText) nextFocusForwardView).setText("");
                           nextFocusForwardView.requestFocus();
                           return true;
                       }
                   }
               }
               return false;
           }

           @Override
           public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
               return false;
           }

           @Override
           public boolean onKeyOther(View view, Editable text, KeyEvent event) {
               return false;
           }

           @Override
           public void clearMetaKeyState(View view, Editable content, int states) {

           }
       };

        etKey1.setKeyListener(DigitsKeyListener.getInstance());
        etKey2.setKeyListener(DigitsKeyListener.getInstance());
        etKey3.setKeyListener(DigitsKeyListener.getInstance());
        etKey4.setKeyListener(DigitsKeyListener.getInstance());

        etKey1.setFocusable(true);
        etKey2.setFocusable(true);
        etKey3.setFocusable(true);
        etKey4.setFocusable(true);

        etKey1.setNextFocusRightId(R.id.key_2);
        etKey2.setNextFocusRightId(R.id.key_3);
        etKey3.setNextFocusRightId(R.id.key_4);

        etKey4.setNextFocusForwardId(R.id.key_3);
        etKey3.setNextFocusForwardId(R.id.key_2);
        etKey2.setNextFocusForwardId(R.id.key_1);





    }


    /**
     * 设置
     *
     * @param keyText
     */
    public void setKeyText(List<String> keyText) {
        if (keyText == null) return;
        resetKeyShow();
        if (keyText.size() == 0) {
            etKey1.setEnabled(true);
            etKey1.requestFocus();
        }
        if (keyText.size() >= 1) {
            etKey1.setText(keyText.get(0));
            etKey1.setEnabled(false);
            etKey2.setEnabled(true);
            etKey2.requestFocus();
        }
        if (keyText.size() >= 2) {
            etKey2.setText(keyText.get(1));
            etKey2.setEnabled(false);
            etKey3.setEnabled(true);
            etKey3.requestFocus();
        }
        if (keyText.size() >= 3) {
            etKey3.setText(keyText.get(2));
            etKey3.setEnabled(false);
            etKey4.setEnabled(true);
            etKey4.requestFocus();
        }
        if (keyText.size() >= 4) {
            etKey4.setText(keyText.get(3));
            etKey4.setSelection(keyText.get(3).length());
        }
    }

    private void resetKeyShow() {
        etKey1.setText("");
        etKey2.setText("");
        etKey3.setText("");
        etKey4.setText("");

        etKey1.setEnabled(false);
        etKey2.setEnabled(false);
        etKey3.setEnabled(false);
        etKey4.setEnabled(false);
    }

    public boolean isFull(int size) {
        return MAX_KEY_NUM == size;
    }


}
