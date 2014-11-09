package com.example.pathfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainScreenFragment extends Fragment {
	
	private Button mGenerate;
	private Button mSaved;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.main_screen, parent, false);
		getActivity().getActionBar().hide();
		mGenerate = (Button) v.findViewById(R.id.generate);
		mGenerate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(),FormActivity.class);
				startActivity(i);
			}
		});
		mSaved = (Button) v.findViewById(R.id.saved);
		mSaved.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(),PathListActivity.class);
				startActivity(i);
			}
		});
		return v;
	}
}
