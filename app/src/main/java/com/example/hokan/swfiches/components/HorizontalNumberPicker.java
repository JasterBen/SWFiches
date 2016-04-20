package com.example.hokan.swfiches.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hokan.swfiches.R;

/**
 * Created by Ben on 19/04/2016.
 */
public class HorizontalNumberPicker extends LinearLayout implements View.OnClickListener {

    private int minValue;
    private int maxValue;
    private int actualValue;

    private Button minus;
    private TextView value;
    private Button plus;


    public HorizontalNumberPicker(Context context) {
        super(context);
        init(context);
    }

    public HorizontalNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorizontalNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.horizontal_number_picker, null, false);
        addView(view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        minus = (Button) findViewById(R.id.horizontal_number_picker_minus_button);
        minus.setOnClickListener(this);

        value = (TextView) findViewById(R.id.horizontal_number_picker_number_container);
        value.setText(String.valueOf(actualValue));

        plus = (Button) findViewById(R.id.horizontal_number_picker_plus_button);
        plus.setOnClickListener(this);
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;

        if (actualValue <= minValue)
            minus.setEnabled(false);
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;

        if (actualValue >= maxValue)
            plus.setEnabled(false);
    }

    public void setActualValue(int actualValue) {
        this.actualValue = actualValue;
        value.setText(String.valueOf(actualValue));
    }

    public int getActualValue() {
        return actualValue;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.horizontal_number_picker_minus_button)
        {
            actualValue--;

            if (actualValue <= minValue)
                minus.setEnabled(false);

            if (actualValue < maxValue && !plus.isEnabled())
                plus.setEnabled(true);

            value.setText(String.valueOf(actualValue));
        }
        if (id == R.id.horizontal_number_picker_plus_button)
        {
            actualValue++;

            if (actualValue >= maxValue)
                plus.setEnabled(false);

            if (actualValue > minValue && !minus.isEnabled())
                minus.setEnabled(true);

            value.setText(String.valueOf(actualValue));
        }

    }
}
