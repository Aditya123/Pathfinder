package com.example.pathfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class FormFragment extends Fragment {
	
	private Button mNext;
	private ImageButton mLocation;
	private EditText mName;
	private EditText mStartLocation;
	private EditText mEndLocation;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.generate_form, parent, false);
		getActivity().getActionBar().hide();
		mNext = (Button) v.findViewById(R.id.next_button);
		mNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		mLocation = (ImageButton) v.findViewById(R.id.find_location_button);
		mLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mName = (EditText) v.findViewById(R.id.edit_name_text);
		mName.addTextChangedListener(new TextWatcher() {
            @Override
			public void onTextChanged(CharSequence c, int start, int before, int count) {

            }

            @Override
			public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
			public void afterTextChanged(Editable c) {
                // This one too
            }
        });
		mStartLocation = (EditText) v.findViewById(R.id.edit_start_text);
		mStartLocation.addTextChangedListener(new TextWatcher() {
            @Override
			public void onTextChanged(CharSequence c, int start, int before, int count) {

            }

            @Override
			public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
			public void afterTextChanged(Editable c) {
                // This one too
            }
        });
		mEndLocation = (EditText) v.findViewById(R.id.edit_name_text);
		mEndLocation.addTextChangedListener(new TextWatcher() {
            @Override
			public void onTextChanged(CharSequence c, int start, int before, int count) {

            }

            @Override
			public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
			public void afterTextChanged(Editable c) {
                // This one too
            }
        });
		return v;
	}
}
