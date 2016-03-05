package com.edufi.sebuku.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edufi.sebuku.R;

/**
 * Created by habib on 04/03/16.
 * Semangat!!
 */
public class WhislistFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_whislist, container, false);
        return rootView;
    }

}
