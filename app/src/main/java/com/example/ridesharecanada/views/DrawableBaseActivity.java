package com.example.ridesharecanada.views;

import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ridesharecanada.R;
import com.google.android.material.navigation.NavigationView;

public class DrawableBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    @Override
    public void setContentView(View view){
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawable_base,null);
        FrameLayout container = drawerLayout.findViewById(R.id.ActivityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        toggle.syncState();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }
    protected void allocateActivityTitle(String s){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(s);
        }
    }

}