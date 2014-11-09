package com.example.pathfinder.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pathfinder.R;
import com.example.pathfinder.route.Route;
import com.example.pathfinder.route.RouteList;

public class ConfirmationFragment extends Fragment implements SensorEventListener {
	SensorManager mSensorMan;
	Sensor mStepCounterSensor;
	Sensor mStepDetectorSensor;
    Sensor mAccelerometer;
    Sensor mMagnetoMeter;

    private float[] mLastAccelerometer = new float[3];
    private float[] mLastMagnetometer = new float[3];
    private boolean mLastAccelerometerSet = false;
    private boolean mLastMagnetometerSet = false;
    private float[] mR = new float[9];
    private float[] mOrientation = new float[3];
    private float mCurrentDegree = 0f;
	private ImageButton mAddCheckpoint;
	private Button mComplete;
	private Button mStep;
	private TextView mStepDirection;
	private TextView mTotalSteps;
	private Route mRoute;
	private int step = 0;
	private int direction = -1;
	private int lastDirection = 0;
	private int totalStepsTaken = 0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);		
		
		mSensorMan = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
		mStepCounterSensor = mSensorMan.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
		mStepDetectorSensor = mSensorMan.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        mAccelerometer = mSensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetoMeter = mSensorMan.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);		
        
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.confirmation_input, parent, false);
		Bundle extras = getActivity().getIntent().getExtras();
		mRoute = new Route(extras.getString(FormFragment.NAME), "Ash Ketchum", extras.getString(FormFragment.START), extras.getString(FormFragment.END));
		mAddCheckpoint = (ImageButton) v.findViewById(R.id.add_checkpoint);
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
				mRoute.addDirection(step, lastDirection);
				mRoute.finalize();
				RouteList.get(getActivity()).addRoute(mRoute);
				RouteList.get(getActivity()).saveRoutes();
				sendResult(Activity.RESULT_OK);
				getActivity().finish();
			}
		});
		mStep = (Button)v.findViewById(R.id.step_button);
		mStep.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int direction = getDirection();
				if (direction == lastDirection) {
					step++;
				} else {
					if (step > 0) {
						mRoute.addDirection(step, lastDirection);
					}
					lastDirection = direction;
					step = 1;
				}
				totalStepsTaken++;
				updateText();
			}
		});
		mTotalSteps = (TextView) v.findViewById(R.id.total_steps);
		mStepDirection = (TextView) v.findViewById(R.id.step_direction);
		

		return v;
	}
	
	private void updateText() {
		mStepDirection.setText(step + " taken in direction: " + direction);
		mTotalSteps.setText(totalStepsTaken + " total steps taken.");
	}
	private void sendResult(int resultCode) {
	    if (getTargetFragment() == null)
	        return;

	    Intent i = new Intent();	    

	    getTargetFragment()
	        .onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
//		Sensor sensor = event.sensor;
//	     float[] values = event.values;
//	     int value = -1;
//
//	    
//	     if (values.length > 0) {
//	        value = (int) values[0];
//             steps++;
//	     }

//
//	      if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
//	        //txtView.setText("Step Counter Detected : " + steps);
//
//	     } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
//	    	// txtView2.setText("Step Detector Detected : " + value);
//	     }

        if (event.sensor == mAccelerometer) {
            System.arraycopy(event.values, 0, mLastAccelerometer, 0, event.values.length);
            mLastAccelerometerSet = true;
        } else if (event.sensor == mMagnetoMeter) {
            System.arraycopy(event.values, 0, mLastMagnetometer, 0, event.values.length);
            mLastMagnetometerSet = true;
        }
        if (mLastAccelerometerSet && mLastMagnetometerSet) {
            SensorManager.getRotationMatrix(mR, null, mLastAccelerometer, mLastMagnetometer);
            SensorManager.getOrientation(mR, mOrientation);
            float azimuthInRadians = mOrientation[0];
            float azimuthInDegress = (float)(Math.toDegrees(azimuthInRadians)+360)%360;
            if(azimuthInDegress >= 337.5 || azimuthInDegress < 22.5) {
                 //Assign north 0

                direction = 0;
            }
            else if (azimuthInDegress >=22.5 && azimuthInDegress < 67.5) {
                //Assign northEast 1
                
                direction = 1;
            }
            else if(azimuthInDegress >= 67.5 && azimuthInDegress < 112.5) {
                //Assign East 2
                
                direction = 2;
            }
            else if( azimuthInDegress >= 112.5 && azimuthInDegress < 157.5) {
                //Assign southEast 3
                direction = 3;
            }
            else if( azimuthInDegress >= 157.5 && azimuthInDegress <  202.5) {
                //Assign south 4
                direction = 4;
            }
            else if( azimuthInDegress >= 202.5 && azimuthInDegress < 247.5) {
                //Assign SouthWest 5
                direction = 5;
            }
            else if( azimuthInDegress >= 247.5 && azimuthInDegress < 292.5) {
                //Assign WEst 6
                direction = 6;
            }
            else if( azimuthInDegress >= 292.5 && azimuthInDegress < 337.5) {
                //Assign NorthWest 7
                direction = 7;
            }

            mCurrentDegree = -azimuthInDegress;
        }



	}

    protected void setDirection(int i){direction = i;}
    protected int getDirection(){return direction;}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

		 
	public void onResume() {
		super.onResume();
//		
//		mSensorMan.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
//		mSensorMan.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);

        mSensorMan.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        mSensorMan.registerListener(this, mMagnetoMeter, SensorManager.SENSOR_DELAY_GAME);

	}
	
	public void onStop(){
	
		super.onStop();
//		mSensorMan.unregisterListener(this, mStepCounterSensor);
//		mSensorMan.unregisterListener(this, mStepDetectorSensor);
	
	    mSensorMan.unregisterListener(this, mAccelerometer);
	    mSensorMan.unregisterListener(this, mMagnetoMeter);
	}

}
