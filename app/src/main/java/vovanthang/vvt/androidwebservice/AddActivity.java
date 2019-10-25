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

public class AddActivity extends AppCompatActivity {
    EditText edtTen , edtNamSinh , edtDiaChi;
    Button btnAdd , btnHuy;
    String url = "http://10.234.125.173/androidwebservice/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        AnhXa();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = edtTen.getText().toString().trim();
                String namsinh = edtNamSinh.getText().toString().trim();
                String diachi = edtDiaChi.getText().toString().trim();
                if(hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty()){
                    Toast.makeText(AddActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else {
                    ThemSinhVien(url);
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
    private void ThemSinhVien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(AddActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddActivity.this,MainActivity.class));
                }
                else {
                    Toast.makeText(AddActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("hotenSV",edtTen.getText().toString().trim());
                params.put("namsinhSV" , edtNamSinh.getText().toString().trim());
                params.put("diachiSV" , edtDiaChi.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void AnhXa() {
        edtTen = (EditText) findViewById(R.id.edtaddTensv);
        edtNamSinh = (EditText) findViewById(R.id.edtaddNamSinhsv);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChiSV);
        btnAdd = (Button) findViewById(R.id.btnAddThem);
        btnHuy = (Button) findViewById(R.id.btnAddHuy);
    }
}
