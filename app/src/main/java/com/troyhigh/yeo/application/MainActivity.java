package com.troyhigh.yeo.application;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

  private DrawerLayout mDrawerLayout;
  private ListView mDrawerList;
  private ActionBarDrawerToggle mDrawerToggle;

  private CharSequence mDrawerTitle;
  private CharSequence mTitle;
  CustomDrawerAdapter adapter;

  List<DrawerItem> dataList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Initializing
    dataList = new ArrayList<DrawerItem>();
    mTitle = mDrawerTitle = getTitle();
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mDrawerList = (ListView) findViewById(R.id.left_drawer);

    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
    GravityCompat.START);

    // Add Drawer Item to dataList

    dataList.add(new DrawerItem("About Troy")); // adding a header to the list
    dataList.add(new DrawerItem("News and Announcements", R.drawable.ic_action_read)); //1
    dataList.add(new DrawerItem("Principal's Message", R.drawable.ic_action_copy)); //2
    dataList.add(new DrawerItem("Faculty and Staff", R.drawable.ic_action_group)); //3

    dataList.add(new DrawerItem("Resources"));// adding a header to the list
    dataList.add(new DrawerItem("Bell Schedule", R.drawable.ic_action_alarms)); //5
    dataList.add(new DrawerItem("Campus Map", R.drawable.ic_action_map)); //6
    dataList.add(new DrawerItem("Calendar", R.drawable.ic_action_event)); //7


    dataList.add(new DrawerItem("Policies")); // adding a header to the list
    dataList.add(new DrawerItem("Attendance", R.drawable.ic_action_attachment)); //9
    dataList.add(new DrawerItem("Academic Honesty", R.drawable.ic_action_attachment)); //10
    dataList.add(new DrawerItem("Electronic Device", R.drawable.ic_action_attachment)); //11
    dataList.add(new DrawerItem("Internet Access", R.drawable.ic_action_attachment)); //12
    dataList.add(new DrawerItem("School Drop Off / Pick Up", R.drawable.ic_action_attachment)); //13

    dataList.add(new DrawerItem("Other")); // adding a header to the list
    dataList.add(new DrawerItem("Aeries", R.drawable.ic_action_important)); //15

    adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,dataList);

    mDrawerList.setAdapter(adapter);

    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);

    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
    R.drawable.ic_drawer, R.string.drawer_open,
    R.string.drawer_close) {
      public void onDrawerClosed(View view) {
        getActionBar().setTitle(mTitle);
        invalidateOptionsMenu(); // creates call to
        // onPrepareOptionsMenu()
      }

      public void onDrawerOpened(View drawerView) {
        getActionBar().setTitle(mDrawerTitle);
        invalidateOptionsMenu(); // creates call to
        // onPrepareOptionsMenu()
      }
    };

    mDrawerLayout.setDrawerListener(mDrawerToggle);

    if (savedInstanceState == null) {

      SelectItem(1);
    }

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  public void SelectItem(int position) {

    String webUrl;
    //String texttry;

    Fragment fragment = null;
    Bundle args = new Bundle();
    switch (position) {

      case 1:
      fragment = new FragWebProgBar();
      args.putString(FragWebProgBar.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWebProgBar.IMAGE_RESOURCE_ID, dataList
      .get(position).getImgResID());

      //added code
      webUrl="http://www.troyhigh.com/apps/news/";
      args.putString(FragWebProgBar.WEB_CONTENT_NAME, webUrl);

      break;
      case 2:
      fragment = new FragWebProgBar();
      args.putString(FragWebProgBar.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWebProgBar.IMAGE_RESOURCE_ID, dataList.get(position)
      .getImgResID());

      //added code
      webUrl="http://www.troyhigh.com/apps/pages/index.jsp?uREC_ID=432683&type=u";
      //texttry = String.valueOf(new Description().execute(webUrl));
      args.putString(FragWebProgBar.WEB_CONTENT_NAME, webUrl);

      break;
      case 3:
      fragment = new FragWebProgBar();
      args.putString(FragWebProgBar.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWebProgBar.IMAGE_RESOURCE_ID, dataList.get(position)
      .getImgResID());

      //added code
      webUrl="http://www.troyhigh.com/apps/staff/";
      args.putString(FragWebProgBar.WEB_CONTENT_NAME, webUrl);x

      break;
      case 5:
      fragment = new FragWebProgBar();
      args.putString(FragWebProgBar.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWebProgBar.IMAGE_RESOURCE_ID, dataList
      .get(position).getImgResID());

      //added code
      webUrl="http://www.troyhigh.com/apps/bell_schedules/";
      args.putString(FragWebProgBar.WEB_CONTENT_NAME, webUrl);

      break;
      case 6:
      fragment = new FragImage();
      args.putString(FragImage.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragImage.IMAGE_RESOURCE_ID, dataList.get(position)
      .getImgResID());

      //added code for Campus Map
      String jpgName = "campus_map";
      int jpgID = getResources().getIdentifier(jpgName,"drawable",getPackageName());
      args.putInt(FragImage.WEB_IMAGE_ID,jpgID);


      break;
      case 7:
      fragment = new FragWebProgBar();
      args.putString(FragWebProgBar.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWebProgBar.IMAGE_RESOURCE_ID, dataList
      .get(position).getImgResID());

      //added code
      webUrl="http://www.troyhigh.com/apps/pages/index.jsp?uREC_ID=263390&type=d&pREC_ID=597010";
      args.putString(FragWebProgBar.WEB_CONTENT_NAME, webUrl);

      break;
      case 9:
      fragment = new FragWebJSoup();
      args.putString(FragWebJSoup.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWebJSoup.IMAGE_RESOURCE_ID, dataList.get(position)
      .getImgResID());

      //added code
      webUrl="http://www.troyhigh.com/apps/pages/index.jsp?title=" +
      "Attendance%20News&userGroupREC_ID=35670&uREC_ID=35670&type=d&un=SEC-attendance&rn=8081368";
      args.putString(FragWebJSoup.WEB_CONTENT_NAME, webUrl);
      break;
      case 10:
      fragment = new FragWebJSoup();
      args.putString(FragWebJSoup.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWebJSoup.IMAGE_RESOURCE_ID, dataList.get(position)
      .getImgResID());

      //added code
      webUrl="http://www.troyhigh.com/apps/pages/index.jsp?uREC_ID=81219&type=d&pREC_ID=150016";
      args.putString(FragWebJSoup.WEB_CONTENT_NAME, webUrl);
      break;
      case 11:
      fragment = new FragWebJSoup();
      args.putString(FragWebJSoup.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWebJSoup.IMAGE_RESOURCE_ID, dataList.get(position)
      .getImgResID());

      //added code
      webUrl="http://www.troyhigh.com/apps/pages/index.jsp?uREC_ID=81219&type=d&pREC_ID=150065";
      args.putString(FragWebJSoup.WEB_CONTENT_NAME, webUrl);
      break;
      case 12:
      fragment = new FragWebJSoup();
      args.putString(FragWebJSoup.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWebJSoup.IMAGE_RESOURCE_ID, dataList.get(position)
      .getImgResID());

      //added code
      webUrl="http://www.troyhigh.com/apps/pages/index.jsp?uREC_ID=81219&type=d&pREC_ID=150020";
      args.putString(FragWebJSoup.WEB_CONTENT_NAME, webUrl);
      break;
      case 13:
      fragment = new FragWebJSoup();
      args.putString(FragWebJSoup.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWebJSoup.IMAGE_RESOURCE_ID, dataList.get(position)
      .getImgResID());

      //added code
      webUrl="http://www.troyhigh.com/apps/pages/index.jsp?uREC_ID=81219&type=d&pREC_ID=383949";
      args.putString(FragWebJSoup.WEB_CONTENT_NAME, webUrl);
      break;
      case 15:
      fragment = new FragWeb();
      args.putString(FragWeb.ITEM_NAME, dataList.get(position)
      .getItemName());
      args.putInt(FragWeb.IMAGE_RESOURCE_ID, dataList
      .get(position).getImgResID());

      //added code
      webUrl="https://mystudent.fjuhsd.net/Parent/LoginParent.aspx?page=default.aspx";
      //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
      //startActivity(browserIntent);
      args.putString(FragWeb.WEB_CONTENT_NAME, webUrl);

      default:
      break;
    }

    fragment.setArguments(args);
    FragmentManager frgManager = getFragmentManager();
    frgManager.beginTransaction().replace(R.id.content_frame, fragment)
    .commit();

    mDrawerList.setItemChecked(position, true);
    setTitle(dataList.get(position).getItemName());
    mDrawerLayout.closeDrawer(mDrawerList);

  }

  @Override
  public void setTitle(CharSequence title) {
    mTitle = title;
    getActionBar().setTitle(mTitle);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    // Sync the toggle state after onRestoreInstanceState has occurred.
    mDrawerToggle.syncState();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    // Pass any configuration change to the drawer toggles
    mDrawerToggle.onConfigurationChanged(newConfig);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // The action bar home/up action should open or close the drawer.
    // ActionBarDrawerToggle will take care of this.
    if (mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }

    if(item.getItemId()== R.id.action_exit ){
      this.finish();
      return true;
    }

    return false;
  }

  private class DrawerItemClickListener implements
  ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
    long id) {
      if (dataList.get(position).getTitle() == null) {
        SelectItem(position);
      }

    }
  }
  public static class AdFragment extends Fragment {

    private AdView mAdView;

    public AdFragment() {
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
      super.onActivityCreated(bundle);

      // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
      // values/strings.xml.
      mAdView = (AdView) getView().findViewById(R.id.adView);

      // Create an ad request. Check logcat output for the hashed device ID to
      // get test ads on a physical device. e.g.
      // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
      AdRequest adRequest = new AdRequest.Builder()
      .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
      .build();

      // Start loading the ad in the background.
      mAdView.loadAd(adRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_ad, container, false);
    }

    /** Called when leaving the activity */
    @Override
    public void onPause() {
      if (mAdView != null) {
        mAdView.pause();
      }
      super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
      super.onResume();
      if (mAdView != null) {
        mAdView.resume();
      }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
      if (mAdView != null) {
        mAdView.destroy();
      }
      super.onDestroy();
    }

  }
}
