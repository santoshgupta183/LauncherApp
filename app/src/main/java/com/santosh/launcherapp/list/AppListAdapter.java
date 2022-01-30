package com.santosh.launcherapp.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.santosh.app_launcher_sdk.InstalledApp;
import com.santosh.launcherapp.R;

import java.util.ArrayList;
import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListVH> implements Filterable {

    private List<InstalledApp> appList;
    private AppFilter appFilter;

    private onAppSelectListener onAppSelectListener;

    public AppListAdapter(List<InstalledApp> appList) {
        this.appList = appList;
        this.appFilter = new AppFilter(new ArrayList<>(appList), this);
    }

    @NonNull
    @Override
    public AppListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_app_list_item, parent, false);
        return new AppListVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppListVH holder, int position) {
        holder.appName.setText(appList.get(position).getAppName());
        holder.packageName.setText(appList.get(position).getPackageName());
        holder.versionName.setText(String.format(holder.itemView.getContext().getResources().getString(R.string.version_name),
                appList.get(position).getAppVersionName()));
        holder.versionCode.setText(String.format(holder.itemView.getContext().getResources().getString(R.string.version_code),
                appList.get(position).getAppVersionCode()));
        holder.mainActivityClassName.setText(appList.get(position).getMainActivityName());
        holder.appIcon.setImageDrawable(appList.get(position).getAppIcon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAppSelectListener.onAppSelected(appList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    @Override
    public Filter getFilter() {
        return appFilter;
    }

    public void updateList(List<InstalledApp> filteredList) {
        appList.clear();
        appList.addAll(filteredList);
        notifyDataSetChanged();
    }

    public void setOnAppSelectListener(onAppSelectListener onAppSelectListener) {
        this.onAppSelectListener = onAppSelectListener;
    }

}
