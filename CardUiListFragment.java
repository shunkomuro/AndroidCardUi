package com.example.komuroshun.carduiapp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardUiListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CardUiListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardUiListFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CardUiListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardUiListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardUiListFragment newInstance(String param1, String param2) {
        CardUiListFragment fragment = new CardUiListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_ui_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // インストールされているアプリを取得
        PackageManager packageManager = getActivity().getPackageManager();
        List<PackageInfo> packageInfoList = packageManager
                .getInstalledPackages(PackageManager.GET_ACTIVITIES);

        // アダプターを作成
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1);
        CardListAdapter adapter = new CardListAdapter(getActivity());

        // アダプターにアプリ情報を追加
        if (packageInfoList != null) {
            for (PackageInfo info : packageInfoList) {
                adapter.add(info);
            }
        }
        setListAdapter(adapter);

        // 8dp
        int padding = (int) (getResources().getDisplayMetrics().density * 8);
        ListView listView = getListView();
        listView.setPadding(padding, 0, padding, 0);
        listView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
        listView.setDivider(null);

        // 余白用のヘッダー、フッターを追加
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View header = layoutInflater.inflate(R.layout.list_header_footer, listView, false);
        View footer = layoutInflater.inflate(R.layout.list_header_footer, listView, false);
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);
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
