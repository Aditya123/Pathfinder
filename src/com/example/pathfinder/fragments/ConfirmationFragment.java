package com.example.pathfinder.fragments;

import com.example.pathfinder.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationFragment extends Fragment {
	
	private Button mAddCheckpoint;
	private Button mComplete;
	private TextView mStepDirection;
	private TextView mTotalSteps;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.confirmation_input, parent, false);
		mAddCheckpoint = (Button) v.findViewById(R.id.add_checkpoint);
		mAddCheckpoint.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		mComplete = (Button) v.findViewById(R.id.complete_button);
		mComplete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		mStepDirection = (TextView) v.findViewById(R.id.step_direction);
		mStepDirection.setText("idk");
		mTotalSteps = (TextView) v.findViewById(R.id.total_steps);
		mTotalSteps.setText("idc");
		return v;
	}
}
