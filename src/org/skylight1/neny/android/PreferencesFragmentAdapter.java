package org.skylight1.neny.android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PreferencesFragmentAdapter extends FragmentPagerAdapter {

    protected static final String[] CONTENT = new String[] { "Cuisine", "Neighborhoods", "Schedule" };
    private int mCount = CONTENT.length;
    boolean hasSeenCuisines = false;
    boolean hasSeenNeighborhoods = false;
    boolean hasSeenMealTimes = false;

    public PreferencesFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            return new SelectCuisinesFragment();
        } else if(position == 1) {
            return new SelectNeighborhoodsFragment();
        } else if(position == 2) {
            return new SelectMealTimesFragment();
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PreferencesFragmentAdapter.CONTENT[position % CONTENT.length];
    }

}
