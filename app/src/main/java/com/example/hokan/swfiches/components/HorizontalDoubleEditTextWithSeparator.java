package com.example.hokan.swfiches.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hokan.swfiches.R;

/**
 * Created by Ben on 20/04/2016.
 */
public class HorizontalDoubleEditTextWithSeparator extends LinearLayout {

    private EditText leftEditText;
    private EditText rightEditText;
    private TextView separatorTextView;


    public HorizontalDoubleEditTextWithSeparator(Context context) {
        super(context);
        init(context);
    }

    public HorizontalDoubleEditTextWithSeparator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorizontalDoubleEditTextWithSeparator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.horizontal_double_edit_text_with_separator, null, false);
        addView(view);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        leftEditText = (EditText) findViewById(R.id.horizontal_double_edit_text_slash_left);

        separatorTextView = (TextView) findViewById(R.id.horizontal_double_edit_text_slash_separator);
        separatorTextView.setText("/");

        rightEditText = (EditText) findViewById(R.id.horizontal_double_edit_text_slash_right);
    }


    public int getLeftValue() {
        return Math.max(Integer.parseInt(leftEditText.getText().toString()), 0);
    }

    public void setLeftValue(int leftValue) {
        leftEditText.setText(String.valueOf(leftValue));
    }

    public int getRightValue() {
        return Math.max(Integer.parseInt(rightEditText.getText().toString()), 0);
    }

    public void setRightValue(int rightValue) {
        rightEditText.setText(String.valueOf(rightValue));
    }

    public void setSeparator(String separator)
    {
        separatorTextView.setText(separator);
    }


}
