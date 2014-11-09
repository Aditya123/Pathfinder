package com.example.pathfinder.route;

public class StepDirection {
	
	private int steps;
	private int direction;
	
	public StepDirection(int step, int directions) {
		steps = step;
		direction = directions;
	}
	
	public int getStep() {
		return steps;
	}
	
	public int direction() {
		return direction;
	}
}
