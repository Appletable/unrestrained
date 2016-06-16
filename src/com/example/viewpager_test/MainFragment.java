package com.example.viewpager_test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment {
	
	private int newsType = 0;

	public MainFragment(int newsType) {
		// TODO Auto-generated constructor stub
		this.newsType = newsType;
	}
	/**
	 * Called when the fragment's activity has been created and this fragment's view hierarchy instantiated.
	 * 
	 * void onActivityCreated (Bundle savedInstanceState)
	 * Called when the fragment's activity has been created 
	 * and this fragment's view hierarchy instantiated. 
	 * It can be used to do final initialization once these pieces are in place, 
	 * such as retrieving views or restoring state. 
	 * It is also useful for fragments that use setRetainInstance(boolean) to retain their instance, 
	 * as this callback tells the fragment when it is fully associated with the new activity instance. 
	 * This is called after onCreateView(LayoutInflater, ViewGroup, Bundle) and before onViewStateRestored(Bundle).
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	/**
	 * Called to have the fragment instantiate its user interface view.
	 */
	@Override
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.tab_item_fragment_main, null);
		TextView tip = (TextView) view.findViewById(R.id.id_tip);
		tip.setText(TabAdapter.TITLES[newsType]);
		return view;
	}

}
