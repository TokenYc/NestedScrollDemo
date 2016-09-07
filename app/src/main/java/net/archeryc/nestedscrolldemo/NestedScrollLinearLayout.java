package net.archeryc.nestedscrolldemo;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * 一句话功能简述
 * 功能详细描述
 *
 * @author 杨晨 on 2016/8/29 16:42
 * @e-mail 247067345@qq.com
 * @see [相关类/方法](可选)
 */

public class NestedScrollLinearLayout extends LinearLayout implements NestedScrollingParent {

    private int mTopViewHeight = dip2px(300);
    private OverScroller mScroller;

    public NestedScrollLinearLayout(Context context) {
        this(context,null);
    }

    public NestedScrollLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NestedScrollLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new OverScroller(context);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.d("nested","onStartNestedScroll");
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.d("nested","onNestedPreScroll");
        boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.d("nested","onNestedPreFling");
        if (getScrollY() >= mTopViewHeight) return false;
        fling((int) velocityY);
        return true;
    }

        public void fling(int velocityY) {
            mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
            invalidate();
        }

        @Override
        public void scrollTo(int x, int y) {
            if (y < 0) {
                y = 0;
            }
            if (y > mTopViewHeight) {
                y = mTopViewHeight;
            }
            if (y != getScrollY()) {
                super.scrollTo(x, y);
            }
        }

        @Override
        public void computeScroll() {

            //先判断mScroller滚动是否完成
            if (mScroller.computeScrollOffset()) {

                //这里调用View的scrollTo()完成实际的滚动
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

                //必须调用该方法，否则不一定能看到滚动效果
                postInvalidate();
            }
            super.computeScroll();
        }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public  int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
