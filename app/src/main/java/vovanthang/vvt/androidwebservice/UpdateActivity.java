package vovanthang.vvt.androidwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
    EditText edtTen, edtNamsinh,edtDiaChi;
    Button btnUpdate , btnHuy;
    int id  = 0 ;
    String url = "http://10.234.125.173/androidwebservice/update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("dataSinhVien");

        AnhXa();
        id = sinhVien.getId();

        edtTen.setText(sinhVien.getHoTen());
        edtNamsinh.setText(sinhVien.getNamSinh() + "");
        edtDiaChi.setText(sinhVien.getDiaChi());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = edtTen.getText().toString().trim();
                String namsinh = edtNamsinh.getText().toString().trim();
                String diachi = edtDiaChi.getText().toString().trim();

                if(hoten.isEmpty() || namsinh.isEmpty()|| diachi.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else {
                    CapNhatSinhVien(url);
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void CapNhatSinhVien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(UpdateActivity.this, "Thành Công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateActivity.this,MainActivity.class));
                }
                else {
                    Toast.makeText(UpdateActivity.this, "Lỗi cập nhật!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idSV" ,String.valueOf(id));
                params.put("hotenSV" , edtTen.getText().toString().trim());
                params.put("namsinhSV" , edtNamsinh.getText().toString().trim());
                params.put("diachiSV" , edtDiaChi.getText().toString().trim());

                return params;
        }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        edtTen = (EditText) findViewById(R.id.edtupdateHoten);
        edtDiaChi = (EditText) findViewById(R.id.edtupdateDiaChi);
        edtNamsinh = (EditText) findViewById(R.id.edtupdateNamsinh);
        btnUpdate = (Button) findViewById(R.id.btnUpdateAdd);
        btnHuy = (Button) findViewById(R.id.btnUpdateHuy);
    }
}
