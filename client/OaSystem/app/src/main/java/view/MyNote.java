package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

import com.oasystem.R;

/**
 * Created by lqs on 2016/4/7.
 */
public class MyNote extends EditText {
    private static final String TAG ="MyNote";
    private final String PACKAGE_NAME ="com.ick.testnote";
    private int color;

    public MyNote(Context context, AttributeSet attrs) {
        super(context, attrs);

        //获得自定义属性资源id

        //第一个参数：spaceName

        //第二个参数：属性名

        //第三个参数：如果属性不存在则要使用的默认值
        int resourceId = attrs.getAttributeResourceValue(PACKAGE_NAME, "backgroud", R.color.green);

        //得到id对应的颜色值
        color = getResources().getColor(resourceId);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int lineHeight =  this.getLineHeight();
        Paint mPaint = getPaint();
        mPaint.setColor(color);
        int topPadding =this.getPaddingTop();
        int leftPadding = this.getPaddingLeft();
        float textSize = getTextSize();
        setGravity(Gravity.LEFT|Gravity.TOP);
        int y = (int) (topPadding + textSize);
        for(int i=0; i<getLineCount(); i++) {
            canvas.drawLine(leftPadding, y+2, getRight()-leftPadding, y+2, mPaint);
            y+=lineHeight;
        }
        canvas.translate(0, 0);
        super.onDraw(canvas);
    }

    /**
     * 设置记事本的编辑框背景线条颜色
     * @param color int type【代表颜色的整数】
     */
    public void setBGColor(int color) {
        this.color = color;
        invalidate();
    }

    /**
     * 设置记事本的编辑框背景线条颜色
     * @param colorId int type【代表颜色的资源id】
     */
    public void setBGColorId(int colorId) {
        this.color = getResources().getColor(colorId);
        invalidate();
    }

}
