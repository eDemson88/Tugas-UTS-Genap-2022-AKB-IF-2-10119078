package com.example.uts_10119078_adamfirdausdarmawan.ui.Notes;
/*
    Nama : Adam Firdaus Darmawan
    Kelas : IF-2
    NIM : 10119078
 */
import android.annotation.SuppressLint;
import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;




import com.example.uts_10119078_adamfirdausdarmawan.MainActivity;
import com.example.uts_10119078_adamfirdausdarmawan.R;
import com.example.uts_10119078_adamfirdausdarmawan.ui.Notes.NotesModel;



import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends BaseAdapter {

    private List<NotesModel> list;
    private Context context;
    private String id;
    private AlertDialog daftarCatatan;

    public NotesAdapter(List<NotesModel> list, Context context, AlertDialog daftarCatatan){
        this.list = list;
        this.context = context;
        this.daftarCatatan = daftarCatatan;
    }


    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return position;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @SuppressLint({"ViewHolder","InlfateParams"})
    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.note_activity,null);

        TextView title = view.findViewById(R.id.itemJudul);
        ImageView note = view.findViewById(R.id.editCatatan);
        ImageView delete = view.findViewById(R.id.hapusCatatan);

        final NotesModel model = list.get(position);
        title.setText(model.getTitle());

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("Title",model.getTitle());
                i.putExtra("Note",model.getNote());
                i.putExtra("lihat", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                daftarCatatan.dismiss();
            }
        });
        return view;
    }

}
