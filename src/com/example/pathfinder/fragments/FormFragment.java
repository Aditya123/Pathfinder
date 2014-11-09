package com.example.pathfinder.fragments;

import android.app.Activity;
import android.content.Intent;
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

import com.example.pathfinder.R;

public class FormFragment extends Fragment {
	
	private Button mNext;
	private ImageButton mStartPic;
	private ImageButton mEndPic;
	private EditText mName;
	private EditText mStartLocation;
	private EditText mEndLocation;
	private String name;
	private String start;
	private String end;
	public static final String NAME = "name";
	public static final String START = "start";
	public static final String END = "end";
	
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
				 Intent i = new Intent(getActivity(), ConfirmationActivity.class);
				 i.putExtra(NAME, name);
				 i.putExtra(START, start);
				 i.putExtra(END, end);
				 startActivityForResult(i, 0);
			}
		});
		
		mStartPic= (ImageButton) v.findViewById(R.id.start_picture);
		mStartPic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		mEndPic= (ImageButton) v.findViewById(R.id.end_picture);
		mEndPic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		mName = (EditText) v.findViewById(R.id.edit_name_text);
		mName.addTextChangedListener(new TextWatcher() {
            @Override
			public void onTextChanged(CharSequence c, int start, int before, int count) {
            	FormFragment.this.name = c.toString();
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
            	FormFragment.this.start = c.toString();
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
		mEndLocation = (EditText) v.findViewById(R.id.edit_end_text);
		mEndLocation.addTextChangedListener(new TextWatcher() {
            @Override
			public void onTextChanged(CharSequence c, int start, int before, int count) {
            	FormFragment.this.end = c.toString();
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode != Activity.RESULT_OK) return;
	    if (resultCode == Activity.RESULT_OK) {
	        getActivity().finish();	        
	    }
	}
}
