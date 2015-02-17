package com.ming.dayouxia.gateway;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ming.dayouxia.gateway.fragments.AboutFragment;
import com.ming.dayouxia.theaterapp.R;
import com.ming.dayouxia.gateway.fragments.ColumbusLandFragment;
import com.ming.dayouxia.gateway.fragments.InTheaterTabsFragment;
import com.ming.dayouxia.gateway.fragments.LoginDialogFragment;
import com.ming.dayouxia.gateway.fragments.NewsEventFragment;

// note that drawer image needs to be updated for different density screen.
public class TheaterDrawerMainActivity extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private LinearLayout mUserItem;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mOptionsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        mTitle = mDrawerTitle = getTitle();
        mOptionsArray = getResources().getStringArray(R.array.drawer_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mUserItem = (LinearLayout)getLayoutInflater().inflate(R.layout.user_list_item, null);

        mUserItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TheaterDrawerMainActivity.this);
                builder.create().show();
            }
        });
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mOptionsArray));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();


        Boolean firstTime = getSharedPreferences("Install", Context.MODE_PRIVATE).getBoolean("first", false);

        if(!firstTime){
            getSharedPreferences("Install",Context.MODE_PRIVATE).edit().putBoolean("first",true).commit();
            Intent intent= new Intent(this, TheaterWelcomeActivity.class);
            startActivity(intent);
        }
        addHeaderViewIfNeeded();
    }

    @Override
    protected void onPause() {
        super.onPause();
        removeHeaderViewIfNeeded();

    }

    public void addHeaderViewIfNeeded(){

        if(CurrentUserSession.getInstance().isLoggedIn()){
            mDrawerList.addHeaderView(mUserItem);
        }


    }

    public void removeHeaderViewIfNeeded(){
        mDrawerList.removeHeaderView(mUserItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        if(CurrentUserSession.getInstance().isLoggedIn()){
            MenuItem loginItem = menu.findItem(R.id.action_login);
            loginItem.setVisible(false);
            MenuItem logoutItem = menu.findItem(R.id.action_logout);
            logoutItem.setVisible(true);
        } else {
            MenuItem loginItem = menu.findItem(R.id.action_login);
            loginItem.setVisible(true);
            MenuItem logoutItem = menu.findItem(R.id.action_logout);
            logoutItem.setVisible(false);

        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()){
            case R.id.action_scanner:
                // open scanner activity.
                Intent i = new Intent(this, ScannerActivity.class);
                startActivity(i);
                return true;
            case R.id.action_login:
                new LoginDialogFragment().show(getSupportFragmentManager(), "login");
                return true;
            case R.id.action_logout:
                CurrentUserSession.getInstance().setLoggedIn(false);
                removeHeaderViewIfNeeded();
                return true;
        }
        // TODO put login here probably, then toggle to logout

        return super.onOptionsItemSelected(item);
    }



    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    public void selectItem(int position) {

        // update the main content by replacing fragments
        // TODO here we will use a switch statement

        Fragment fragment = new Fragment();
        switch (position){
            case 0:
                fragment = new InTheaterTabsFragment();
                break;
            case 1:
                fragment = new NewsEventFragment();
                break;
            case 2:
                fragment = new ColumbusLandFragment();
                break;
            case 3:
                fragment = new AboutFragment();
                break;
            case 4:
                Intent intent = new Intent(this, TheaterWelcomeActivity.class);
                startActivity(intent);
                break;
            }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mOptionsArray[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

        // update selected item and title, then close the drawer
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}