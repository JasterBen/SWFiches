package com.example.hokan.swfiches.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.hokan.swfiches.R;

/**
 * Created by Utilisateur on 21/05/2016.
 */
public class DicePoolView extends View {

    private Paint paint;
    private int greenDice;
    private int yellowDice;

    private int width;
    private int height;

    private int skillLevel;
    private int characLevel;

    public DicePoolView(Context context) {
        super(context);
        init(context);
    }

    public DicePoolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        greenDice = context.getResources().getColor(R.color.dice_green);
        yellowDice = context.getResources().getColor(R.color.dice_yellow);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawDicePool(canvas);
    }

    private void drawHexagone(Canvas canvas, int minX, int maxX)
    {
        Path path = new Path();
        int w = maxX - minX;

        int quarterWidth = w / 4;
        int thirdWidth = w / 3;
        int halfHeight = height / 2;
        int thirdHeight = height / 3;

        path.moveTo(quarterWidth - (thirdWidth / 3) + minX, halfHeight);
        path.lineTo(thirdWidth + minX, thirdHeight);
        path.lineTo(thirdWidth + quarterWidth + minX, thirdHeight);
        path.lineTo(3 * quarterWidth + minX, halfHeight);
        path.lineTo(thirdWidth + quarterWidth + minX, 2 * thirdHeight);
        path.lineTo(thirdWidth + minX, 2 * thirdHeight);
        path.close();

        canvas.drawPath(path, paint);
    }


    private void drawDiamond(Canvas canvas, int minX, int maxX)
    {
        Path path = new Path();
        int w = maxX - minX;

        int quarterWidth = w / 4;
        int halfWidth = w / 2 + minX;
        int quarterHeight = height / 4;
        int halfHeight = height / 2;

        path.moveTo(quarterWidth + minX, halfHeight);
        path.lineTo(halfWidth, quarterHeight);
        path.lineTo(3 * quarterWidth + minX, halfHeight);
        path.lineTo(halfWidth, 3 * quarterHeight);
        path.close();

        canvas.drawPath(path, paint);
    }


    private void drawDicePool(Canvas canvas)
    {
        int sixthWidth = width / 6;

        int hexagoneNumber = Math.min(skillLevel, characLevel);
        int diamondNumber = Math.max(skillLevel, characLevel) - hexagoneNumber;

        int i = 0;


        paint.setColor(yellowDice);
        while (i < hexagoneNumber)
        {
            int minX = i * sixthWidth;
            int maxX = (i + 1) * sixthWidth;
            drawHexagone(canvas, minX, maxX);
            i++;
        }

        diamondNumber += i;
        paint.setColor(greenDice);
        while (i < diamondNumber)
        {
            int minX = i * sixthWidth;
            int maxX = (i + 1) * sixthWidth;
            drawDiamond(canvas, minX, maxX);
            i++;
        }
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setCharacLevel(int characLevel) {
        this.characLevel = characLevel;
    }
}
