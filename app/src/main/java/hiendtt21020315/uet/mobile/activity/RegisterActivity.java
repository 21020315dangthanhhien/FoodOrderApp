package hiendtt21020315.uet.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hiendtt21020315.uet.mobile.R;
import hiendtt21020315.uet.mobile.setting.UserDAO;
import hiendtt21020315.uet.mobile.setting.User;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername_regis,edUserpass_regis,edUserRePass;
    Button btn_register, btn_register_back;
    UserDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername_regis=findViewById(R.id.edUsername_regis);
        edUserpass_regis=findViewById(R.id.edUserpass_regis);
        edUserRePass=findViewById(R.id.edUserRePass);
        btn_register=findViewById(R.id.btn_Register);
        btn_register_back = findViewById(R.id.btn_Register_back);
        dao=new UserDAO(this);
        btn_register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setUser_name(edUsername_regis.getText().toString());
                user.setUser_pass(edUserpass_regis.getText().toString());
                user.setUser_role("user");
                if(validate()>0){
                    if(dao.insert(user)>0){
                        Toast.makeText(getApplicationContext(), "thêm tai khoan thanh cong", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                        finish();
                        startActivity(i);
                    }else {
                        Toast.makeText(getApplicationContext(), "Tài Khoản Đã Tồn Tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public int validate(){
        int check=1;
        if(edUsername_regis.getText().length()==0||edUserpass_regis.getText().length()==0||edUserRePass.getText().length()==0
                ){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
            check=-1;
        }else{
            String pass=edUserpass_regis.getText().toString();
            String repass=edUserRePass.getText().toString();
            if(!pass.equals(repass)){
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }else{
                check=1;
            }

        }
        return check;
    }
}