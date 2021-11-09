package com.integration.gardenjetpackdemo;

import android.os.Bundle;

import com.integration.gardenjetpackdemo.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

//import com.integration.gardenjetpackdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    // androidx 的 抽屉布局
    private DrawerLayout mDrawerLayout;
    // androidx. Navigation 查询(AppBarConfiguration)：https://zhuanlan.zhihu.com/p/136479775
    private AppBarConfiguration mAppBarConfiguration;

    private NavController mNavController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 由于我们是把整个布局交给了-->DataBinding去管理，那么就需要使用DataBinding初始化布局
        // DataBinding初始化
        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);


        // 拿到布局的drawer_layout 赋值 给成员drawerLayout
        mDrawerLayout = binding.drawerLayout;
        // 拿到布局的garden_nav_fragment(首页布局Fragment画面区域) 赋值 给成员navController
        mNavController = Navigation.findNavController(this, R.id.garden_nav_fragment);

        // NavigationUI使用AppBarConfiguration
        // 对象来管理应用程序显示区域左上角的“导航”按钮的行为
        mAppBarConfiguration = new AppBarConfiguration.Builder(mNavController.getGraph())
                .setDrawerLayout(mDrawerLayout)
                .build();
        Toolbar toolbar = binding.toolbar;
        // 标题栏 == toolbar
        // Set up ActionBar
        setSupportActionBar(toolbar);
        // 查询 setupActionBarWithNavController：
        // https://zhuanlan.zhihu.com/p/69653419?from_voters_page=true
        // 在标题 左上角菜单 1
        // 通过NavigationUI.setupActionBarWithNavController()方法，将App bar与NavController绑定
        NavigationUI.setupActionBarWithNavController(this, mNavController,
                mAppBarConfiguration);
        // 当我们点击 左侧栏 [我的花园 按钮] [植物目录 按钮] 就能触发事件 执行...
        // 左侧栏 于此 绑定  （左侧栏--> [我的花园 按钮] [植物目录 按钮]）
        // Set up navigation menu
        NavigationUI.setupWithNavController(binding.navigationView, mNavController);

    }


    @Override
    public boolean onSupportNavigateUp() {

        return NavigationUI.navigateUp(mNavController,mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}