package com.example.pathfinder.route;

import org.json.JSONException;
import org.json.JSONObject;

public class StepDirection {
	
	private int steps;
	private int direction;
	public static final String JSON_STEPS = "steps";
	public static final String JSON_DIRECTION = "direction";
	
	public StepDirection(int step, int directions) {
		steps = step;
		direction = directions;
	}
	
	public StepDirection(JSONObject jsonObject) {
		 try {
			steps = jsonObject.getInt(JSON_STEPS);
			direction = jsonObject.getInt(JSON_DIRECTION);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

	public int getStep() {
		return steps;
	}
	
	public int direction() {
		return direction;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_STEPS, steps);
		json.put(JSON_DIRECTION, direction);
		return json;
	}
}
