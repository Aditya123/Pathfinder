package com.example.pathfinder.route;

import java.util.ArrayList;
import java.util.UUID;
import android.content.Context;
import android.util.Log;

public class RouteList {
	private ArrayList<Route> mRoutes;
	private static RouteList sRouteList;
	private Context mAppContext;
	private static final String TAG = "RouteList";
	private static final String FILENAME = "routes.json";
	private JSONSerializer mSerializer;
	
	private RouteList(Context appContext) {
		mAppContext = appContext;
		try {
            mRoutes = mSerializer.loadRoutes();
        } catch (Exception e) {
            mRoutes = new ArrayList<Route>();
            Log.e(TAG, "Error loading crimes: ", e);
        }
	}
	
	public static RouteList get (Context c) {
		if (sRouteList == null) {
			sRouteList = new RouteList(c.getApplicationContext());
		}
		return sRouteList;
	}
	
	public ArrayList<Route> getRoutes() {
		return mRoutes;
	}
	
	public void addRoute(Route c) {
		mRoutes.add(c);
	}
	
	public void deleteRoute(Route c) {
		mRoutes.remove(c);
	}
	
	public Route getRoute(UUID id) {
		for (Route c : mRoutes) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}
	
	public boolean saveRoutes() {
		try {
            mSerializer.saveRoutes(mRoutes);
            Log.d(TAG, "routes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: ", e);
            return false;
        }
	}
}
