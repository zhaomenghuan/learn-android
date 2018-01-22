package io.github.zhaomenghuan.blog;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DataGenerator {
    public static final int[] mTabRes = new int[]{
            R.drawable.tab_home_normal,
            R.drawable.tab_blog_normal,
            R.drawable.tab_note_normal,
            R.drawable.tab_person_normal
    };
    public static final int[] mTabResPressed = new int[]{
            R.drawable.tab_home_pressed,
            R.drawable.tab_blog_pressed,
            R.drawable.tab_note_pressed,
            R.drawable.tab_person_pressed
    };
    public static final String[] mTabTitle = new String[]{"首页", "博客", "笔记", "我的"};

    public static Fragment[] getFragments(String from) {
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = BlogFragment.newInstance(from);
        fragments[2] = NoteFragment.newInstance(from);
        fragments[3] = PersonFragment.newInstance(from);
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     *
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabbar_item_content, null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
