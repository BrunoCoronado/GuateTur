package org.coronado.guatetur.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.coronado.guatetur.R;

/**
 * Created by informatica on 03/06/2016.
 */
public class FragmentHoteles extends Fragment{

    public FragmentHoteles(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sitios, container, false);
        return view;
    }
}
