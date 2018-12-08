package com.negativ.javabasics.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.negativ.javabasics.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicDataFragment extends Fragment {

    private String header, data;

    private TextView txtHeader, txtData;


    public static TopicDataFragment newInstance(final String header, final String data) {
        final TopicDataFragment fragment = new TopicDataFragment();
        final Bundle arguments = new Bundle();
        arguments.putString("header", header);
        arguments.putString("data", data);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if (getArguments() != null && getArguments().containsKey("header") && getArguments().containsKey("data")) {
            header = getArguments().getString("header");
            data = getArguments().getString("data");
        } else {
            throw new IllegalArgumentException("Must be created through newInstance(...)");
        }
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        header = header;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        data = data;
    }

    public TopicDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_topic_data, null);

        txtHeader = view.findViewById(R.id.txtHeader);
        txtHeader.setText(header);
        txtData = view.findViewById(R.id.txtData);
        txtData.setText(data);
        return view;
    }

}
