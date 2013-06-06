package org.skylight1.neny.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.skylight1.neny.android.database.dao.CuisinePreferences;
import org.skylight1.neny.android.database.dao.PreferencesDao;
import org.skylight1.neny.android.database.model.Cuisine;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class SelectCuisinesFragment extends Fragment {

    public static final List<Integer> CUISINE_ACTIVE_IMAGES = asList(R.drawable.china_active, R.drawable.africa_active, R.drawable.italian_active, R.drawable.mayan_active, R.drawable.comfort_active, R.drawable.eclectic_active, R.drawable.eu_active, R.drawable.indian_active, R.drawable.middle_eastern_active, R.drawable.north_america_active, R.drawable.pacifica_active, R.drawable.vege_active);

    private List<Boolean> listOfSelectedCuisines = new ArrayList<Boolean>();
    private PreferencesDao preferencesDao;
    GridView grid;

    public static SelectCuisinesFragment newInstance() {
        SelectCuisinesFragment fragment = new SelectCuisinesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle aSavedInstanceState) {

        super.onCreate(aSavedInstanceState);

        preferencesDao = new CuisinePreferences(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView;

        mView = inflater.inflate(R.layout.cuisines_view, container, false);

        final GridView grid = (GridView) mView.findViewById(R.id.cuisinesGrid);
        final List<Integer> cuisinesActiveImageResources =
                CUISINE_ACTIVE_IMAGES;
        final List<Integer> cuisinesInactiveImageResources =
                asList(R.drawable.china_inactive, R.drawable.africa_inactive, R.drawable.italian_inactive, R.drawable.mayan_inactive, R.drawable.comfort_inactive, R.drawable.eclectic_inactive, R.drawable.eu_inactive, R.drawable.indian_inactive, R.drawable.middle_eastern_inactive, R.drawable.north_america_inactive, R.drawable.pacifica_inactive, R.drawable.vege_inactive);
        for (final int dummy : cuisinesActiveImageResources) {
            listOfSelectedCuisines.add(false);
        }

        // load preferences
        listOfSelectedCuisines = new ArrayList<Boolean>();
        for (int i = 0; i < cuisinesActiveImageResources.size(); i++) {
            listOfSelectedCuisines.add(preferencesDao.getPreference(mapImagePositionsToEnums(i).getLabel(), true));
        }

        grid.setAdapter(new CuisineAdapter(this.getActivity(), cuisinesActiveImageResources, listOfSelectedCuisines, cuisinesInactiveImageResources));

        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser) {
            SharedPreferences preferences = getActivity().getSharedPreferences(PreferencesActivity.WizardState, Context.MODE_PRIVATE);
            if(!preferences.getBoolean(PreferencesActivity.CuisineState, false)) {
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean(PreferencesActivity.CuisineState, true);
                edit.commit();
            }
        }
    }

    public static Cuisine mapImagePositionsToEnums(int position) {
        Cuisine cuisine = null;
        switch (position) {
            case 0: {
                cuisine = Cuisine.ASIAN;
                break;
            }
            case 1: {
                cuisine = Cuisine.AFRICAN;
                break;
            }
            case 2: {
                cuisine = Cuisine.ITALIAN;
                break;
            }
            case 3: {
                cuisine = Cuisine.CENTRAL_AND_SOUTH_AMERICAN;
                break;
            }
            case 4: {
                cuisine = Cuisine.COMFORT_AND_SNACKS;
                break;
            }
            case 5: {
                cuisine = Cuisine.ECLECTIC;
                break;
            }
            case 6: {
                cuisine = Cuisine.EUROPEAN;
                break;
            }
            case 7: {
                cuisine = Cuisine.INDIAN_SUBCONTINENT;
                break;
            }
            case 8: {
                cuisine = Cuisine.MIDDLE_EASTERN;
                break;
            }
            case 9: {
                cuisine = Cuisine.US_NORTH_AMERICAN;
                break;
            }
            case 10: {
                cuisine = Cuisine.PACIFICA;
                break;
            }
            case 11: {
                cuisine = Cuisine.VEGETARIAN;
                break;
            }
        }
        return cuisine;
    }
}