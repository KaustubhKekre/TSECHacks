package com.phantomorion.tsechacks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class pagerAdapter extends FragmentStatePagerAdapter {



    public pagerAdapter(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                events events=new events();
                return events;
            case 1:
                money money=new money();
                return money;
            case 2:
                items items=new items();
                return items;
            case 3:
                profile profile=new profile();
                return profile;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
