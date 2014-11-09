package com.example.pathfinder.route;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

public class Route {
	public final int N = 0;
	public final int NE = 1;
	public final int E = 2;
	public final int SE = 3;
	public final int S = 4;
	public final int SW = 5;
	public final int W = 6;
	public final int NW = 7;
	
	private static final String JSON_NAME = "name";
	private static final String JSON_ID = "id";
	private static final String JSON_CREATOR = "creator";
	private static final String JSON_DATE = "date";
	private static final String JSON_STEPS = "steps";
	private static final String JSON_ROUTE = "route";
	private static final String JSON_START = "start";
	private static final String JSON_END = "end";
	
	private String logTitle = "Route class";
	private String name;
	private UUID mId;
	private String creator;
	private String date;
	private String end;
	private String start;
	private int totalSteps;
	private ArrayList<StepDirection> route = new ArrayList<StepDirection>();
	private Boolean finalized = false;
	
	public Route(String name, String creator, String start, String end) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.creator = creator;
		Calendar today = Calendar.getInstance();
		date = today.get(Calendar.MONTH) + "/" + today.get(Calendar.DAY_OF_MONTH) + "/" + today.get(Calendar.YEAR);
		totalSteps = 0;
		mId = UUID.randomUUID();
	}
	
	@SuppressWarnings("unchecked")
	public Route(JSONObject json) throws JSONException {
		 name = json.getString(JSON_NAME);
		 mId = UUID.fromString(json.getString(JSON_ID));
		 creator = json.getString(JSON_CREATOR);
		 totalSteps = json.getInt(JSON_STEPS);
		 JSONArray temproute = new JSONArray(json.getString(JSON_ROUTE));	
		 for (int i = 0; i < temproute.length(); i++) {
             addDirection(temproute.getJSONObject(i).getInt(StepDirection.JSON_STEPS), temproute.getJSONObject(i).getInt(StepDirection.JSON_DIRECTION));
         }
		 date = json.getString(JSON_DATE);
		 start = json.getString(JSON_START);
		 end = json.getString(JSON_END);
		 finalized = true;
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_NAME, name);
		json.put(JSON_CREATOR, creator);
		json.put(JSON_ID, mId.toString());
		json.put(JSON_DATE, date);
		
		JSONArray array = new JSONArray();
        for (StepDirection c : route)
            array.put(c.toJSON());
        
		json.put(JSON_ROUTE, array);
		json.put(JSON_ROUTE, route);
		json.put(JSON_STEPS, totalSteps);
		json.put(JSON_START, start);
		json.put(JSON_END, end);
		return json;
	}
	
	public String getStart() {
		return start;
	}
	
	public String getEnd() {
		return end;
	}
	
	public UUID getId() {
        return mId;
    }
	public void finalize() {
		finalized = true;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public String getDate() {
		return date;
	}
	
	public void incrementSteps(int steps) {
		if (!finalized) {
			totalSteps += steps;
		}
	}
	
	public int totalSteps() {
		return totalSteps;
	}
	
	public ArrayList<StepDirection> getRoute() {
		if (finalized) {
			return route;
		}
		Log.d(logTitle,"Route not finalized");
		return null;
	}
	
	public void addDirection(int steps, int direction) {
		if (!finalized) {
			StepDirection test = new StepDirection(steps, direction);
			route.add(test);
			incrementSteps(steps);
		}
		Log.d(logTitle, "trying to add direction to finalized route");
	}
	
//	public void test(ArrayList<Integer> directions) {
//		int lastDirection = 0;
//		int step = 0;
//		for(int i = 0; i < directions.size(); i++) {
//			if (directions.get(i) == lastDirection) {
//				step++;
//			} else {
//				if (step > 0) {
//					addDirection(step, lastDirection);
//					lastDirection = directions.get(i);
//					step = 1;
//				}
//			}
//		}
//		addDirection(step, lastDirection);
//	}
}
