package vovanthang.vvt.androidwebservice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SinhVienAdapter  extends BaseAdapter {
    private MainActivity context;
    private int layout;
    ArrayList <SinhVien> sinhVienArrayList;

    public SinhVienAdapter(MainActivity context, int layout, ArrayList<SinhVien> sinhVienArrayList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienArrayList = sinhVienArrayList;
    }

    @Override
    public int getCount() {
        return sinhVienArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtHoTen , txtDiaChi ,txtNamSinh;
        ImageView imgEdit , imgDelete;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout,null);
            holder.txtHoTen = (TextView) view.findViewById(R.id.textviewHoTenCusTom);
            holder.txtDiaChi = (TextView)view.findViewById(R.id.textviewDiaChiCusTom);
            holder.txtNamSinh = (TextView) view.findViewById(R.id.textviewNamSinhCustom);
            holder.imgDelete = (ImageView)view.findViewById(R.id.imageviewDeletecustom);
            holder.imgEdit = (ImageView)view.findViewById(R.id.imageviewEditcustom);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        final SinhVien sinhVien = sinhVienArrayList.get(i);

        holder.txtHoTen.setText(sinhVien.getHoTen());
        holder.txtNamSinh.setText("Năm Sinh : " + sinhVien.getNamSinh());
        holder.txtDiaChi.setText(sinhVien.getDiaChi());
        //bat su kien xoa
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , UpdateActivity.class);
                intent.putExtra("dataSinhVien" , sinhVien);
                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanXoa(sinhVien.getHoTen() , sinhVien.getId());
            }
        });

        return view;
    }

    private void XacNhanXoa(String ten , final int id){
        AlertDialog.Builder dialogXoa  = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Ban có muốn xóa Sinh Vien" + ten + "Không ? " );
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.DeleteStuden(id);
            }
        });

        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogXoa.show();
    }
}
