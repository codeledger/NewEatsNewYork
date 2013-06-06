package org.skylight1.neny.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.skylight1.neny.android.database.dao.MealTimePreferences;
import org.skylight1.neny.android.database.dao.PreferencesDao;
import org.skylight1.neny.android.database.model.DayAndTime;
import org.skylight1.neny.android.database.model.MealDayTime;


public class SelectMealTimesFragment extends Fragment {
    private MealtimeAdapter mealAdapter;
    private PreferencesDao mealTimePreferences;

    public static SelectMealTimesFragment newInstance() {
        SelectMealTimesFragment fragment = new SelectMealTimesFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View aView;

        aView = inflater.inflate(R.layout.diningtimes_view, container, false);
        mealTimePreferences = new MealTimePreferences(getActivity());
        MealDayTime[] mealtimes = createSomeData(mealTimePreferences);

        mealAdapter = new MealtimeAdapter(getActivity(),mealtimes,mealTimePreferences);
        ListView mealList = (ListView)aView.findViewById(android.R.id.list);

        mealList.setAdapter(mealAdapter);

        return aView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser) {
            SharedPreferences preferences = getActivity().getSharedPreferences(PreferencesActivity.WizardState, Context.MODE_PRIVATE);
            if(!preferences.getBoolean(PreferencesActivity.DiningTimeState, false)) {
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean(PreferencesActivity.DiningTimeState, true);
                edit.commit();
            }
        }
    }
    private MealDayTime[] createSomeData(PreferencesDao preferences){
        MealDayTime[] times =  new MealDayTime[7];
        DayAndTime[] dayAndTimes = DayAndTime.values();
        for(int i=0,j=1;i<dayAndTimes.length;i=i+2,j++){
            DayAndTime lunch = dayAndTimes[i];
            DayAndTime dinner = dayAndTimes[i+1];
            MealDayTime mDt = new MealDayTime(j,preferences.getPreference(lunch.name(), true),preferences.getPreference(dinner.name(), true));
            times[j-1]= mDt;
        }

        return times;
    }

}
