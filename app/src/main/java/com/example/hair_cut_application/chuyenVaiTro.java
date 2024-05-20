package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hair_cut_application.DAO.AdminDAO;
import com.example.hair_cut_application.DAO.UsersDAO;
import com.example.hair_cut_application.DTO.AdminDTO;
import com.example.hair_cut_application.DTO.UserDTO;

import java.util.ArrayList;

public class chuyenVaiTro extends AppCompatActivity {
    EditText eTxtSDT;
    Button btnQueryAdmin, btnQueryUser, btnToUser, btnToAdmin;
    ListView lv;
    UsersDAO usersDAO;
    AdminDAO adminDAO;
    UserDTO userDTO;
    AdminDTO adminDTO;
    ArrayList myList;
    ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_vai_tro);

        addConTrol();
        xuLySKClick();
    }

    private void xuLySKClick() {
        //click button có text "To admin" sẽ chuyển user có trong TABLE_USERS sang TABLE_ADMIN
        btnToAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = eTxtSDT.getText().toString();

                if(sdt.equals("")){
                    Toast.makeText(chuyenVaiTro.this, "Vui lòng điền số điện thoại", Toast.LENGTH_SHORT).show();
                }else {
                    if (usersDAO.kiemTraSDT(sdt)) {
                        //lấy đối tượng userDTO để ứng hàm usersDAO.layDuLieu(sdt) có KDL trả về là 1 userDTO
                        userDTO = usersDAO.layDuLieu(sdt);
                        //gán userDTO cho adminDTO để thực hiện thêm vào bảng TABLE_ADMIN
                        adminDTO = new AdminDTO(userDTO.getUsername(), userDTO.getPhoneNumber(), userDTO.getPassword());
                        //Thêm dữ liệu vào bảng TABLE_ADMIN
                        adminDAO.themUser(adminDTO);
                        //Đã thêm dữ liệu vào bảng TABLE_ADMIN thì xóa ở bảng TABLE_USERS
                        usersDAO.xoaUser(userDTO);

                        Toast.makeText(chuyenVaiTro.this, "Đã thêm quản trị viên ".concat(sdt), Toast.LENGTH_SHORT).show();
                    } else {
                        if (adminDAO.kiemTraSDT(sdt))
                            Toast.makeText(chuyenVaiTro.this, "SDT đã tồn tại bên phía quản trị viên", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(chuyenVaiTro.this, "SDT không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //click button có text "To admin" sẽ chuyển user có trong TABLE_ADMIN sang TABLE_USERS
        btnToUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = eTxtSDT.getText().toString();

                if(sdt.equals("")){
                    Toast.makeText(chuyenVaiTro.this, "Vui lòng điền số điện thoại", Toast.LENGTH_SHORT).show();
                }else {
                    if (adminDAO.kiemTraSDT(sdt)) {
                        //lấy đối tượng adminDTO để ứng hàm adminDAO.layDuLieu(sdt) có KDL trả về là 1 adminDTO
                        adminDTO = adminDAO.layDuLieu(sdt);
                        //gán adminDTO cho userDTO để thực hiện thêm vào bảng TABLE_USERS
                        userDTO = new UserDTO(adminDTO.getUsername(), adminDTO.getPhoneNumber(), adminDTO.getPassword());
                        //Thêm dữ liệu vào bảng TABLE_USERS
                        usersDAO.themUser(userDTO);
                        //Đã thêm dữ liệu vào bảng TABLE_ADMIN thì xóa ở bảng TABLE_USERS
                        adminDAO.xoaUser(adminDTO);

                        Toast.makeText(chuyenVaiTro.this, "Đã thêm user ".concat(sdt), Toast.LENGTH_SHORT).show();
                    } else {
                        if (usersDAO.kiemTraSDT(sdt))
                            Toast.makeText(chuyenVaiTro.this, "SDT đã tồn tại bên phía USER", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(chuyenVaiTro.this, "SDT không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnQueryUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList = new ArrayList();
                myList = usersDAO.hienDuLieu();
                myAdapter = new ArrayAdapter(chuyenVaiTro.this, android.R.layout.simple_list_item_1, myList);
                lv.setAdapter(myAdapter);
            }
        });

        btnQueryAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList = new ArrayList();
                myList = adminDAO.hienDuLieu();
                myAdapter = new ArrayAdapter(chuyenVaiTro.this, android.R.layout.simple_list_item_1, myList);
                lv.setAdapter(myAdapter);
            }
        });
    }

    private void addConTrol() {
        eTxtSDT = findViewById(R.id.eTxtSDT);
        btnToAdmin = findViewById(R.id.btnToAdmin);
        btnToUser= findViewById(R.id.btnToUser);
        btnQueryAdmin = findViewById(R.id.btnQueryAdmin);
        btnQueryUser = findViewById(R.id.btnQueryUser);
        lv = findViewById(R.id.lvStylist);
        usersDAO = new UsersDAO(this);
        adminDAO = new AdminDAO(this);
    }
}