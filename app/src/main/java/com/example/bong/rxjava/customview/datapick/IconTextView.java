package com.example.bong.rxjava.customview.datapick;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.util.AttributeSet;

import com.example.bong.rxjava.R;


//import android.widget.TextView;

/**
 * @author rqg
 * @date 9/27/15.
 */
public class IconTextView extends AppCompatTextView {
    public IconTextView(Context context) {
        this(context, null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadFront();
    }


    public void setTextColorRes(int resId) {
        setTextColor(getResources().getColor(resId));
    }

    private void loadFront() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "icomoon.ttf");
            setTypeface(tf);
        }
    }

    public void setDefaultText(String s) {
        setTypeface(Typeface.DEFAULT);
        setText(s);
    }

    public static CharSequence convertNumbers(Context context, int n) {
        String s = String.valueOf(n);

        return convertNumbers(context, s);

    }

    public static CharSequence convertNumbers(Context context, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            switch (s.codePointAt(i)) {
                case 0x30:
                    sb.append(context.getString(R.string.icon_0));
                    break;
                case 0x31:
                    sb.append(context.getString(R.string.icon_1));
                    break;
                case 0x32:
                    sb.append(context.getString(R.string.icon_2));
                    break;
                case 0x33:
                    sb.append(context.getString(R.string.icon_3));
                    break;
                case 0x34:
                    sb.append(context.getString(R.string.icon_4));
                    break;
                case 0x35:
                    sb.append(context.getString(R.string.icon_5));
                    break;
                case 0x36:
                    sb.append(context.getString(R.string.icon_6));
                    break;
                case 0x37:
                    sb.append(context.getString(R.string.icon_7));
                    break;
                case 0x38:
                    sb.append(context.getString(R.string.icon_8));
                    break;
                case 0x39:
                    sb.append(context.getString(R.string.icon_9));
                    break;
                case 0x2e:
                    sb.append(context.getString(R.string.icon_dot));
                    break;
                default:
                    sb.append(s.charAt(i));
            }
        }
        return Html.fromHtml(sb.toString());
    }

}
