package com.tradex.trader;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ferick on 5/29/2016.
 */
public class SettingsFragment extends Fragment
{
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.layout_settings, container, false);
        return v;
    }
}
