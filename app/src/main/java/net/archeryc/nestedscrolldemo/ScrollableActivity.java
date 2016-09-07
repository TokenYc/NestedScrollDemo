package net.archeryc.nestedscrolldemo;

import android.animation.ArgbEvaluator;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnScrollChangedListener;
import ru.noties.scrollable.ScrollableLayout;

public class ScrollableActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ScrollableLayout slRoot;
    private RecyclerView rvHot;
    private RecyclerView rvTopic;
    private TextView tvHeader;
    private TextView tvBlock;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;
    private LinearLayout llHeader;
    private LinearLayout llHeaderContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable);
        Fresco.initialize(this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        slRoot = (ScrollableLayout) findViewById(R.id.sl_root);
        rvHot = (RecyclerView) findViewById(R.id.rv_hot);
        tvHeader = (TextView) findViewById(R.id.tv_header);
        tvBlock = (TextView) findViewById(R.id.tv_block);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        rvTopic = (RecyclerView) findViewById(R.id.rv_topic);
        llHeader = (LinearLayout) findViewById(R.id.ll_header);
        llHeaderContainer = (LinearLayout) findViewById(R.id.ll_header_container);

        llHeaderContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getTotalHeight()));
//        rvHot.setVisibility(View.GONE);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.addTab(tabLayout.newTab().setText("第一"));
        tabLayout.addTab(tabLayout.newTab().setText("第二"));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        slRoot.setCanScrollVerticallyDelegate(new CanScrollVerticallyDelegate() {
            @Override
            public boolean canScrollVertically(int direction) {
                return myPagerAdapter.canScrollVertical(viewPager.getCurrentItem());
            }
        });
        slRoot.setOnScrollChangedListener(new OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int y, int oldY, int maxY) {
            }
        });
        swipeRefreshLayout.setNestedScrollingEnabled(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rvHot.setVisibility(View.GONE);
                        rvTopic.setVisibility(View.GONE);
                        llHeaderContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getTotalHeight()));
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });

        rvHot.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        rvHot.setAdapter(new ImageAdapter(this));

        rvTopic.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rvTopic.setAdapter(new ImageAdapter(this));

    }

    private int getTotalHeight(){
        int mHeight=0;
        if (tvHeader.getVisibility()==View.VISIBLE){
            mHeight+=tvHeader.getLayoutParams().height;
        }
        if (tvBlock.getVisibility()==View.VISIBLE){
            mHeight+=tvBlock.getLayoutParams().height;
        }
        if (rvHot.getVisibility()==View.VISIBLE){
            mHeight+=rvHot.getLayoutParams().height;
        }
        if (rvTopic.getVisibility()==View.VISIBLE){
            mHeight+=rvTopic.getLayoutParams().height;
        }
        if (tabLayout.getVisibility()==View.VISIBLE){
            mHeight+=tabLayout.getLayoutParams().height;
        }
        return mHeight;
    }

}
