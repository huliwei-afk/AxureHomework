package com.example.axurehomework;

import android.content.Intent;
import android.graphics.Picture;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView topBar;
    private TextView tabMessages;
    private TextView tabFriends;
    private TextView tabEvents;
    private TextView tabChanges;

    private FrameLayout ly_content;

    private FirstFragment f1,f2,f3,f4;
    private FragmentManager fragmentManager;

    private String[] mStrs = {"aaa","bbb","ccc","airsaid"};
    private SearchView mSearchView;
    private ListView mListView;

    private List<Msg> msgList = new ArrayList<>();



    public MainActivity() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.create_group:
                Toast.makeText(this, "You Clicked ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_friend_or_group:
                Intent intent = new Intent(MainActivity.this, AddFriendsActivity.class);
                startActivity(intent);
                break;
            case R.id.match_group:
                Toast.makeText(this, "You Clicked ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.party_together:
                Toast.makeText(this, "You Clicked ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scan:
                Toast.makeText(this, "You Clicked ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pay:
                Toast.makeText(this, "You Clicked ", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        bindView();

    }

    //UI组件初始化与事件绑定
    private void bindView() {
        topBar = (TextView)this.findViewById(R.id.txt_top);
        tabMessages = (TextView)this.findViewById(R.id.txt_messages);
        tabFriends = (TextView)this.findViewById(R.id.txt_friends);
        tabEvents = (TextView)this.findViewById(R.id.txt_events);
        tabChanges = (TextView)this.findViewById(R.id.txt_changes);
        ly_content = (FrameLayout) findViewById(R.id.fragment_container);

        tabMessages.setOnClickListener(this);
        tabFriends.setOnClickListener(this);
        tabEvents.setOnClickListener(this);
        tabChanges.setOnClickListener(this);

    }

    //重置所有文本的选中状态
    public void selected(){
        tabMessages.setSelected(false);
        tabFriends.setSelected(false);
        tabEvents.setSelected(false);
        tabChanges.setSelected(false);
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }
        if(f4!=null){
            transaction.hide(f4);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch(v.getId()){
            case R.id.txt_messages:
                selected();
                tabMessages.setSelected(true);
                if(f1==null){
                    f1 = new FirstFragment("");
                    transaction.add(R.id.fragment_container,f1);
                }else{
                    transaction.show(f1);
                }
                break;

            case R.id.txt_friends:
                selected();
                tabFriends.setSelected(true);
                if(f2==null){
                    f2 = new FirstFragment("这些是联系人");
                    transaction.add(R.id.fragment_container,f2);
                }else{
                    transaction.show(f2);
                }
                break;

            case R.id.txt_events:
                selected();
                tabEvents.setSelected(true);
                if(f3==null){
                    f3 = new FirstFragment("这些是看点");
                    transaction.add(R.id.fragment_container,f3);
                }else{
                    transaction.show(f3);
                }
                break;

            case R.id.txt_changes:
                selected();
                tabChanges.setSelected(true);
                if(f4==null){
                    f4 = new FirstFragment("这些是动态");
                    transaction.add(R.id.fragment_container,f4);
                }else{
                    transaction.show(f4);
                }
                break;
        }

        transaction.commit();

        mSearchView = (SearchView) findViewById(R.id.searchView);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mStrs));
        mListView.setTextFilterEnabled(true);

        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    mListView.setFilterText(newText);
                }else{
                    mListView.clearTextFilter();
                }
                return false;
            }
        });

    }
}