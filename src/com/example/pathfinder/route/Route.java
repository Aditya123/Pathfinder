package com.example.pathfinder.route;

import java.util.ArrayList;
import java.util.Calendar;

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
	
	private String logTitle = "Route class";
	private String name;
	private String creator;
	private String date;
	private int totalSteps;
	private ArrayList<StepDirection> route;
	private Boolean finalized = false;
	
	public Route(String name, String creator) {
		this.name = name;
		this.creator = creator;
		Calendar today = Calendar.getInstance();
		date = today.get(Calendar.MONTH) + "/" + today.get(Calendar.DAY_OF_MONTH) + "/" + today.get(Calendar.YEAR);
		totalSteps = 0;
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
			route.add(new StepDirection(steps, direction));
			incrementSteps(steps);
		}
		Log.d(logTitle, "trying to add direction to finalized route");
	}
}
