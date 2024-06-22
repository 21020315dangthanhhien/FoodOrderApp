package hiendtt21020315.uet.mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

import hiendtt21020315.uet.mobile.admin.list_history.Quanly_StatusFragment;
import hiendtt21020315.uet.mobile.setting.UserFragment;
import hiendtt21020315.uet.mobile.admin.food.FoodFragment;
import hiendtt21020315.uet.mobile.admin.list_history.ListHistoryFragment;
import hiendtt21020315.uet.mobile.admin.list_request.ListRequestFragment;
import hiendtt21020315.uet.mobile.admin.statis.StatisFragment;
import hiendtt21020315.uet.mobile.user.cart.Cart_Fragment;
import hiendtt21020315.uet.mobile.user.history.HistoryFragment;
import hiendtt21020315.uet.mobile.user.history.History_QuanLyFragment;
import hiendtt21020315.uet.mobile.user.home.HomeFragment;
import hiendtt21020315.uet.mobile.user.request.RequestFragment;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationBarView view = findViewById(R.id.bottom_navi);
        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String role = sharedPreferences.getString("ROLE", "");
        if(role.equalsIgnoreCase("admin")){
            view.getMenu().clear();
            view.inflateMenu(R.menu.bottom_navigation_menu_admin);
                replaceFragment(new FoodFragment());
        }else{
            view.getMenu().clear();
            view.inflateMenu(R.menu.bottom_navigation_menu_user);
            replaceFragment(new HomeFragment());
        }
        view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_Home) {
                    replaceFragment(new HomeFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_Cart) {
                    replaceFragment(new Cart_Fragment());
                    return true;
                } else if (item.getItemId() == R.id.action_Request) {
                    replaceFragment(new RequestFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_History) {
                    replaceFragment(new History_QuanLyFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_User) {
                    replaceFragment(new UserFragment());
                    return true;
                }else if(item.getItemId()==R.id.action_ListFood){
                    replaceFragment(new FoodFragment());
                    return true;
                }
                else if(item.getItemId()==R.id.action_ListRequestt){
                    replaceFragment(new ListRequestFragment());
                    return true;
                }else if(item.getItemId()==R.id.action_List_Invo){
                    replaceFragment(new Quanly_StatusFragment());
                    return true;
                }
                else if(item.getItemId()==R.id.action_Statis){
                    replaceFragment(new StatisFragment());
                    return true;
                }
                else {
                    return false;
                }
            }
        });


    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }
}