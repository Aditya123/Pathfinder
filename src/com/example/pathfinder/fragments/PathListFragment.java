package com.example.pathfinder.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pathfinder.R;
import com.example.pathfinder.route.Route;
import com.example.pathfinder.route.RouteList;

public class PathListFragment extends ListFragment {
	private ArrayList<Route> mRoutes;
	private boolean mSubtitleVisible;
	private static final String TAG = "PathListFragment";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().setTitle(R.string.route);
		mRoutes = RouteList.get(getActivity()).getRoutes();
		RouteAdapter adapter = new RouteAdapter(mRoutes);
		setListAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater,  parent,  savedInstanceState);
		ListView listView = (ListView)v.findViewById(android.R.id.list);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

            public void onItemCheckedStateChanged(ActionMode mode, int position,
                    long id, boolean checked) {
                // Required, but not used in this implementation
            }

            // ActionMode.Callback methods
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.route_context, menu);
                return true;
            }

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
                // Required, but not used in this implementation
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_delete_route:
                        RouteAdapter adapter = (RouteAdapter)getListAdapter();
                        RouteList crimeLab = RouteList.get(getActivity());
                        for (int i = adapter.getCount() - 1; i >= 0; i--) {
                            if (getListView().isItemChecked(i)) {
                                crimeLab.deleteRoute(adapter.getItem(i));
                            }
                        }
                        mode.finish();
                        adapter.notifyDataSetChanged();
                        return true;
                    default:
                        return false;
                }
            }

            public void onDestroyActionMode(ActionMode mode) {
                // Required, but not used in this implementation
            }
        });
		return v;
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		int position = info.position;
		RouteAdapter adapter = (RouteAdapter)getListAdapter();
		Route route = adapter.getItem(position);
		
		switch(item.getItemId()) {
		case R.id.menu_item_delete_route:
			RouteList.get(getActivity()).deleteRoute(route);
			adapter.notifyDataSetChanged();
			return true;
		}
		return super.onContextItemSelected(item);
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Route c = ((RouteAdapter)getListAdapter()).getItem(position);
		Log.d(TAG, c.getName() + " was clicked");
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getActivity().getMenuInflater().inflate(R.menu.route_context, menu);
	}
	
	private class RouteAdapter extends ArrayAdapter<Route> {
		public RouteAdapter(ArrayList<Route> routes) {
			super(getActivity(), 0, routes);
		}
		@Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.saved_path_item, null);
            }

            // Configure the view for this Crime
            Route c = getItem(position);

            TextView titleTextView =
                (TextView)convertView.findViewById(R.id.path_name);
            titleTextView.setText(c.getName());
            TextView dateTextView =
                (TextView)convertView.findViewById(R.id.path_date);
            dateTextView.setText(c.getDate());
            TextView creatorTextView =
                (TextView)convertView.findViewById(R.id.path_creator);
            creatorTextView.setText(c.getCreator());
            return convertView;
        }
		
	}
}
