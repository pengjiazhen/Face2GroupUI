package com.isoftstone.facekeynum.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.isoftstone.facekeynum.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by jiazhen on 17/12/15.
 * Desc:
 */

public class KeyShowLayout extends LinearLayout {

    public static final int MAX_KEY_NUM = 4;

    private EditText etKey1, etKey2, etKey3, etKey4;

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

        resetKeyShow();
        etKey1.setEnabled(true);

        modifyCursorDrawable(etKey1, R.drawable.edit_cursor_color);
        modifyCursorDrawable(etKey2, R.drawable.edit_cursor_color);
        modifyCursorDrawable(etKey3, R.drawable.edit_cursor_color);
        modifyCursorDrawable(etKey4, R.drawable.edit_cursor_color);

        disableShowSoftInput(etKey1);
        disableShowSoftInput(etKey2);
        disableShowSoftInput(etKey3);
        disableShowSoftInput(etKey4);
    }

    /**
     * 设置
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

    /**
     * 修改光标颜色
     *
     * @param obj
     * @param drawable
     */
    private void modifyCursorDrawable(EditText obj, int drawable) {
        try {
            Field setCursor = TextView.class.getDeclaredField("mCursorDrawableRes");
            setCursor.setAccessible(true);
            setCursor.set(obj, drawable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 禁止Edittext弹出软件盘，光标依然正常显示。
     */
    public void disableShowSoftInput(EditText obj) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            obj.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(obj, false);
            } catch (Exception e) {
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(obj, false);
            } catch (Exception e) {
            }
        }
    }

}
