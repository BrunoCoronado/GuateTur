package org.coronado.guatetur.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.coronado.guatetur.R;

/**
 * Created by TrexT on 04/06/2016.
 */
public class LoadingView extends Fragment {
    public LoadingView(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loading_view, container, false);
        return view;
    }
}
