package com.iitmandi.vendors;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class VendorsListVegetables extends Fragment {

    private boolean viewIsAtHome;
    //private Fragment fragment = new EventListTechnicalList();

    public VendorsListVegetables() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_vendors_list_vegetables, container, false);

        /*RelativeLayout rl_tech_events_event1 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event1);
        RelativeLayout rl_tech_events_event2 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event2);
        RelativeLayout rl_tech_events_event3 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event3);
        RelativeLayout rl_tech_events_event4 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event4);
        RelativeLayout rl_tech_events_event5 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event5);
        RelativeLayout rl_tech_events_event6 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event6);
        RelativeLayout rl_tech_events_event7 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event7);
        RelativeLayout rl_tech_events_event8 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event8);
        RelativeLayout rl_tech_events_event9 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event9);
        RelativeLayout rl_tech_events_event10 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event10);
        RelativeLayout rl_tech_events_event11 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event11);
        //RelativeLayout rl_tech_events_event12 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event12);
        RelativeLayout rl_tech_events_event13 = (RelativeLayout) rootView.findViewById(R.id.rl_tech_events_event13);

        TextView tv_event1 = (TextView) rootView.findViewById(R.id.tv_event1);
        TextView tv_event2 = (TextView) rootView.findViewById(R.id.tv_event2);
        TextView tv_event3 = (TextView) rootView.findViewById(R.id.tv_event3);
        TextView tv_event4 = (TextView) rootView.findViewById(R.id.tv_event4);
        TextView tv_event5 = (TextView) rootView.findViewById(R.id.tv_event5);
        TextView tv_event6 = (TextView) rootView.findViewById(R.id.tv_event6);
        TextView tv_event7 = (TextView) rootView.findViewById(R.id.tv_event7);
        TextView tv_event8 = (TextView) rootView.findViewById(R.id.tv_event8);
        TextView tv_event9 = (TextView) rootView.findViewById(R.id.tv_event9);
        TextView tv_event10 = (TextView) rootView.findViewById(R.id.tv_event10);
        TextView tv_event11 = (TextView) rootView.findViewById(R.id.tv_event11);
        //TextView tv_event12 = (TextView) rootView.findViewById(R.id.tv_event12);
        TextView tv_event13 = (TextView) rootView.findViewById(R.id.tv_event13);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/nexa.otf");
        tv_event1.setTypeface(custom_font);
        tv_event2.setTypeface(custom_font);
        tv_event3.setTypeface(custom_font);
        tv_event4.setTypeface(custom_font);
        tv_event5.setTypeface(custom_font);
        tv_event6.setTypeface(custom_font);
        tv_event7.setTypeface(custom_font);
        tv_event8.setTypeface(custom_font);
        tv_event9.setTypeface(custom_font);
        tv_event10.setTypeface(custom_font);
        tv_event11.setTypeface(custom_font);
        //tv_event12.setTypeface(custom_font);
        tv_event13.setTypeface(custom_font);

        rl_tech_events_event1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event1);
            }
        });
        rl_tech_events_event2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event2);
            }
        });
        rl_tech_events_event3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event3);
            }
        });
        rl_tech_events_event4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event4);
            }
        });
        rl_tech_events_event5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event5);
            }
        });
        rl_tech_events_event6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event6);
            }
        });
        rl_tech_events_event7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event7);
            }
        });
        rl_tech_events_event8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event8);
            }
        });
        rl_tech_events_event9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event9);
            }
        });
        rl_tech_events_event10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event10);
            }
        });
        rl_tech_events_event11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event11);
            }
        });
        *//*rl_tech_events_event12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event12);
            }
        });*//*
        rl_tech_events_event13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_tech_events_event13);
            }
        });*/
        return rootView;
    }
    /*public void displayView(int viewId) {

        String title = getString(R.string.app_name);
        Bundle bundle = new Bundle();
        switch (viewId) {

            case R.id.rl_tech_events_event1:
                bundle.putInt("key",0);
                break;
            case R.id.rl_tech_events_event2:
                bundle.putInt("key",1);
                break;
            case R.id.rl_tech_events_event3:
                bundle.putInt("key",2);
                break;
            case R.id.rl_tech_events_event4:
                bundle.putInt("key",3);
                break;
            case R.id.rl_tech_events_event5:
                bundle.putInt("key",4);
                break;
            case R.id.rl_tech_events_event6:
                bundle.putInt("key",5);
                break;
            case R.id.rl_tech_events_event7:
                bundle.putInt("key",6);
                break;
            case R.id.rl_tech_events_event8:
                bundle.putInt("key",7);
                break;
            case R.id.rl_tech_events_event9:
                bundle.putInt("key",8);
                break;
            case R.id.rl_tech_events_event10:
                bundle.putInt("key",9);
                break;
            case R.id.rl_tech_events_event11:
                bundle.putInt("key",10);
                break;
            *//*case R.id.rl_tech_events_event12:
                bundle.putInt("key",11);
                break;*//*
            case R.id.rl_tech_events_event13:
                bundle.putInt("key",11);
                break;


        }
        fragment.setArguments(bundle);
        viewIsAtHome = true;
        title  = "Events";
        if (fragment != null) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

    }*/
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
