package com.example.ahar.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.ahar.Adaptar.TabViewPagerAdapter;
import com.example.ahar.Fragment.ChatFragment;
import com.example.ahar.Fragment.DonateFragment;
import com.example.ahar.Fragment.HomeFragment;
import com.example.ahar.Fragment.ProfileFragment;
import com.example.ahar.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
/**
 * Created by Istiak Saif on 14/03/21.
 */
public class RestaurantHomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager tabviewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_home);

        int displayWidth = getWindowManager().getDefaultDisplay().getHeight();

        tabLayout = (TabLayout)findViewById(R.id.tab);
        tabviewPager = (ViewPager)findViewById(R.id.tabviewpager);
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        tabViewPagerAdapter.AddFragment(new HomeFragment(),null);
        tabViewPagerAdapter.AddFragment(new DonateFragment(),"Donation");
        tabViewPagerAdapter.AddFragment(new ChatFragment(),"Chat");
        tabViewPagerAdapter.AddFragment(new ProfileFragment(),"Profile");
        tabviewPager.setAdapter(tabViewPagerAdapter);
        tabLayout.setupWithViewPager(tabviewPager);
        LinearLayout layout = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = 0.5f;
        layout.setLayoutParams(layoutParams);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
        }return true;
    }
}