package com.santosh.launcherapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.santosh.app_launcher_sdk.AppStatusChangeListener;
import com.santosh.app_launcher_sdk.InstalledApp;
import com.santosh.app_launcher_sdk.LauncherSDK;
import com.santosh.launcherapp.list.AppListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<InstalledApp> appList = new ArrayList<>();
    private AppListAdapter appListAdapter;
    private final LauncherSDK launcherSDK = LauncherSDK.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView appListView = findViewById(R.id.appList);

        appList.addAll(launcherSDK.getAllInstalledApps());

        appListAdapter = new AppListAdapter(appList);
        appListAdapter.setOnAppSelectListener(launcherSDK::launchApp);
        appListView.setLayoutManager(new LinearLayoutManager(this));
        appListView.setAdapter(appListAdapter);

        launcherSDK.addAppStatusListener(new AppStatusChangeListener() {
            @Override
            public void onAppInstalled(String packageName) {
                if (packageName != null) {
                    Toast.makeText(MainActivity.this, packageName + " installed!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "New app installed!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAppUninstalled(String packageName) {
                if (packageName != null) {
                    Toast.makeText(MainActivity.this, packageName + " uninstalled!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "An app uninstalled!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                appListAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onDestroy() {
        launcherSDK.removeAppStatusListener();
        super.onDestroy();
    }
}