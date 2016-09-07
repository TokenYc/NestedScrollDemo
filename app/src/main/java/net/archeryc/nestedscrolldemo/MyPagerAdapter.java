package net.archeryc.nestedscrolldemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 一句话功能简述
 * 功能详细描述
 *
 * @author 杨晨 on 2016/8/30 10:06
 * @e-mail 247067345@qq.com
 * @see [相关类/方法](可选)
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    List<BlankFragment> fragments = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public boolean canScrollVertical(int position) {
        return ((BlankFragment) getItem(position)).canScrollVertical();
    }
}
