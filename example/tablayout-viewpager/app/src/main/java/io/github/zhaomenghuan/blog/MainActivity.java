package io.github.zhaomenghuan.blog;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int mIcons[][] = new int[][] {
            {R.drawable.tab_home_normal, R.drawable.tab_home_pressed},
            {R.drawable.tab_blog_normal, R.drawable.tab_blog_pressed},
            {R.drawable.tab_note_normal, R.drawable.tab_note_pressed},
            {R.drawable.tab_person_normal, R.drawable.tab_person_pressed}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvent();
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager.setAdapter(new AppTabAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        // 设置图标
        setTabLayoutIcons();
    }

    private void setTabLayoutIcons() {
        int tabCounts = tabLayout.getTabCount();
        int selectedPostion = tabLayout.getSelectedTabPosition();
        for (int i = 0; i < tabCounts; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (i == selectedPostion) {
                tab.setIcon(mIcons[i][1]);
            } else {
                tab.setIcon(mIcons[i][0]);
            }
        }
    }

    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setTabLayoutIcons();
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
