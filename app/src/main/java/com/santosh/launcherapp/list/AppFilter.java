package com.santosh.launcherapp.list;

import android.widget.Filter;

import com.santosh.app_launcher_sdk.InstalledApp;

import java.util.ArrayList;
import java.util.List;

public class AppFilter extends Filter {
    private List<InstalledApp> appList;
    private AppListAdapter appListAdapter;

    public AppFilter(List<InstalledApp> appList, AppListAdapter appListAdapter) {
        this.appList = appList;
        this.appListAdapter = appListAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        List<InstalledApp> filteredAppList = new ArrayList<>();

        if (charSequence == null || charSequence.length() == 0){
            filteredAppList.addAll(appList);
        } else {
            for (InstalledApp app: appList) {
                if (app.getAppName().toLowerCase().contains(String.valueOf(charSequence).toLowerCase())){
                    filteredAppList.add(app);
                }
            }
        }

        FilterResults filterResults = new FilterResults();
        filterResults.values = filteredAppList;
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        appListAdapter.updateList((List<InstalledApp>) filterResults.values);
    }
}
