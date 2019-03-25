package com.example.tabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TableRow;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3 extends Fragment {
    public Context thiscontext;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab3() {
        // Required empty public constructor


    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.fragment_tab3);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


      //  TableLayout table = new TableLayout(this);
//Таблица ГГГ как делать я не знаю
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        Context context = getActivity().getApplicationContext();
        LinearLayout layout = new LinearLayout(context);






        TableLayout tableLayout = new TableLayout(context);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        tableLayout.setStretchAllColumns(true);


        ImageView imageView1 = new ImageView(context);
        imageView1.setImageResource(R.drawable.alexey);
        TextView textView2 = new TextView(context);
        textView2.setText(R.string.Alexey1);
        TextView textView3 = new TextView(context);
        textView3.setText(R.string.Alexey2);
        textView2.setTextSize(18);
        textView3.setTextSize(18);
        textView2.setTextColor(getResources().getColor(R.color.texttable));
        textView3.setTextColor(getResources().getColor(R.color.texttable));


        ImageView imageView4 = new ImageView(context);
        imageView4.setImageResource(R.drawable.vlad);
        TextView textView5 = new TextView(context);
        textView5.setText(R.string.Vlad1);
        TextView textView6 = new TextView(context);
        textView6.setText(R.string.Vlad2);
        textView5.setTextSize(18);
        textView6.setTextSize(18);
        textView5.setTextColor(getResources().getColor(R.color.texttable));
        textView6.setTextColor(getResources().getColor(R.color.texttable));


        ImageView imageView7 = new ImageView(context);
        imageView7.setImageResource(R.drawable.artyom);
        TextView textView8 = new TextView(context);
        textView8.setText(R.string.Artyom1);
        TextView textView9 = new TextView(context);
        textView9.setText(R.string.Artyom2);
        textView8.setTextSize(18);
        textView9.setTextSize(18);
        textView9.setTextColor(getResources().getColor(R.color.texttable));
        textView8.setTextColor(getResources().getColor(R.color.texttable));



        ImageView imageView10 = new ImageView(context);
        imageView10.setImageResource(R.drawable.nikita);
        TextView textView11 = new TextView(context);
        textView11.setText(R.string.Nikita1);
        TextView textView12 = new TextView(context);
        textView12.setText(R.string.Nikita2);
        textView11.setTextSize(18);
        textView12.setTextSize(18);
        textView11.setTextColor(getResources().getColor(R.color.texttable));
        textView12.setTextColor(getResources().getColor(R.color.texttable));



        TableRow tableRow1 = new TableRow(context);
        TableRow tableRow2 = new TableRow(context);
        TableRow tableRow3 = new TableRow(context);
        TableRow tableRow4 = new TableRow(context);

        tableRow1.addView(imageView1);
        tableRow1.addView(textView2);
        tableRow1.addView(textView3);



        //tableRow2.setBackgroundColor(0xffcccccc);
        tableRow2.addView(imageView4);
        tableRow2.addView(textView5);
        tableRow2.addView(textView6);

        tableRow3.addView(imageView7);
        tableRow3.addView(textView8);
        tableRow3.addView(textView9);

        tableRow4.addView(imageView10);
        tableRow4.addView(textView11);
        tableRow4.addView(textView12);

        tableLayout.addView(tableRow1);
        tableLayout.addView(tableRow2);
        tableLayout.addView(tableRow3);
        tableLayout.addView(tableRow4);

        layout.addView(tableLayout);
        return layout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
