package com.example.pathfinder.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pathfinder.R;
import com.example.pathfinder.route.Route;
import com.example.pathfinder.route.RouteList;

public class ConfirmationFragment extends Fragment {
	
	private Button mAddCheckpoint;
	private Button mComplete;
	private TextView mStepDirection;
	private TextView mTotalSteps;
	private Route mRoute;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.confirmation_input, parent, false);
		Bundle extras = getActivity().getIntent().getExtras();
		mRoute = new Route(extras.getString(FormFragment.NAME), "Ash Ketchum", extras.getString(FormFragment.START), extras.getString(FormFragment.END));
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
				 //update route somehow
				RouteList.get(getActivity()).addRoute(mRoute);
				RouteList.get(getActivity()).saveRoutes();
				sendResult(Activity.RESULT_OK);
				getActivity().finish();
			}
		});
		mStepDirection = (TextView) v.findViewById(R.id.step_direction);
		mStepDirection.setText("idk");
		mTotalSteps = (TextView) v.findViewById(R.id.total_steps);
		mTotalSteps.setText("idc");
		return v;
	}
	private void sendResult(int resultCode) {
	    if (getTargetFragment() == null)
	        return;

	    Intent i = new Intent();	    

	    getTargetFragment()
	        .onActivityResult(getTargetRequestCode(), resultCode, i);
	}
}
