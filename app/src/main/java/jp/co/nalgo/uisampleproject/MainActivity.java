package jp.co.nalgo.uisampleproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {
    TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i, this));
        }

        mTabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 4;
        final String[] mTabTitles = { "ライブ", "ビデオ", "見たい！", "設定" };
        int[] mTabIcons = {
                R.drawable.ic_live_tv_black_24dp,
                R.drawable.ic_movie_creation_black_24dp,
                R.drawable.ic_favorite_black_24dp,
                R.drawable.ic_settings_black_24dp
        };

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        View getTabView(int position, Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
            TextView titleView = (TextView) view.findViewById(R.id.title);
            titleView.setText(mTabTitles[position]);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);
            iconView.setImageResource(mTabIcons[position]);
            return view;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return BlankFragment.newInstance("test1", "test1");
                case 1:
                    return BlankFragment.newInstance("test2", "test2");
                case 2:
                    return BlankFragment.newInstance("test3", "test3");
                case 3:
                    return BlankFragment.newInstance("test4", "test4");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }

    }
}
