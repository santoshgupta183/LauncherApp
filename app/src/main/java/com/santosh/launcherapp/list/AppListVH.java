package com.santosh.launcherapp.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.santosh.launcherapp.R;

public class AppListVH extends RecyclerView.ViewHolder {

    TextView appName;
    ImageView appIcon;
    TextView packageName;
    TextView versionName;
    TextView versionCode;
    TextView mainActivityClassName;

    public AppListVH(@NonNull View itemView) {
        super(itemView);
        appName = itemView.findViewById(R.id.appName);
        appIcon = itemView.findViewById(R.id.appIcon);
        packageName = itemView.findViewById(R.id.packageName);
        versionCode = itemView.findViewById(R.id.versionCode);
        versionName = itemView.findViewById(R.id.versionName);
        mainActivityClassName = itemView.findViewById(R.id.mainActivityClassName);
    }
}
