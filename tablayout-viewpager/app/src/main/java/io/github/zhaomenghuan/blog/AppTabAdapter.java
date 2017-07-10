package io.github.zhaomenghuan.blog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AppTabAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[] {"首页", "博文", "笔记","我的"};

    public AppTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeFragment();
        } else if (position == 1) {
            return new BlogFragment();
        } else if (position == 2) {
            return new NoteFragment();
        }
        return new PersonFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}