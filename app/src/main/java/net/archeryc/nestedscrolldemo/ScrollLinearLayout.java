package net.archeryc.nestedscrolldemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 一句话功能简述
 * 功能详细描述
 *
 * @author 杨晨 on 2016/9/7 17:20
 * @e-mail 247067345@qq.com
 * @see [相关类/方法](可选)
 */

public class ScrollLinearLayout extends LinearLayout {
    public ScrollLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScrollLinearLayout(Context context) {
        super(context);
    }

    public ScrollLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
