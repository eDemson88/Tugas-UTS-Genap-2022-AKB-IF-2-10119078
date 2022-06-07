package com.example.uts_10119078_adamfirdausdarmawan.ui.Notes;
/*
    Nama : Adam Firdaus Darmawan
    Kelas : IF-2
    NIM : 10119078
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uts_10119078_adamfirdausdarmawan.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


public class NotesActivity extends AppCompatActivity {
    private TextView simpanCatatan;
    private EditText title;
    private EditText content;
    private Button cancel;
    private boolean edit = false;
    private int id = 0;
    private RelativeLayout layoutCatatan;
    private LinearLayout layoutUtama;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);

        title = findViewById(R.id.judulCatatan);
        content = findViewById(R.id.isiCatatan);
        simpanCatatan = findViewById(R.id.simpanCatatan);
        cancel = findViewById(R.id.batal);
        layoutCatatan = findViewById(R.id.layoutCatatan);
        layoutUtama = findViewById(R.id.layoutUtama);

        TextView buatCatatanBaru = findViewById(R.id.buatCatatanBaru);
        TextView daftarCatatan = findViewById(R.id.daftarCatatan);

        Intent i = getIntent();
        edit = i.getBooleanExtra("edit", false);
        if (edit) {
            layoutUtama.setVisibility(View.GONE);
            layoutCatatan.setVisibility(View.VISIBLE);
        }

        boolean lihat = i.getBooleanExtra("lihat", false);
        if (lihat) {
            simpanCatatan.setVisibility(View.INVISIBLE);
            cancel.setText("Kembali");
            layoutUtama.setVisibility(View.GONE);
            layoutCatatan.setVisibility(View.VISIBLE);
        }
        title.setText(i.getStringExtra("judul"));
        content.setText(i.getStringExtra("catatan"));
        id = i.getIntExtra("id", 0);

        buatCatatanBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanCatatan.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                cancel.setText("Batal");
                layoutUtama.setVisibility(View.GONE);
                layoutCatatan.setVisibility(View.VISIBLE);
            }
        });

        simpanCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Judul Tidak Boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (content.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Catatan tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBThing dbThing = new DBThing(getApplicationContext());
                NotesModel model = new NotesModel();
                model.setTitle(title.getText().toString());
                model.setNote(content.getText().toString());

                boolean masukanCatatan;
                if (edit) {
                    masukanCatatan = dbThing.updateNote(model);
                } else {
                    masukanCatatan = dbThing.addNote(model);
                }
                if (masukanCatatan) {
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                }
                dbThing.close();

                title.getText().clear();
                content.getText().clear();
                layoutUtama.setVisibility(View.VISIBLE);
                layoutCatatan.setVisibility(View.GONE);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.getText().clear();
                content.getText().clear();
                layoutUtama.setVisibility(View.VISIBLE);
                layoutCatatan.setVisibility(View.GONE);
                edit = false;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void dialogDaftarCatatan (){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.row_layout,null);
        TextView delete = v.findViewById(R.id.hapusSemuaCatatan);
        TextView judulDaftar = v.findViewById(R.id.judulDaftar);
        b.setView(v);

        ArrayList<NotesModel> notesModels = new ArrayList<>();
        ListView listDaftar = v.findViewById(R.id.listDaftar);
        final DBThing db = new DBThing(getApplicationContext());
        Cursor cursor = db.getAllData();
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            while (!cursor.isAfterLast()){
                NotesModel notes = new NotesModel();
                notes.setId((cursor.getString(cursor.getColumnIndexOrThrow("id"))));
                notes.setTitle((cursor.getString(cursor.getColumnIndexOrThrow("title"))));
                notes.setNote((cursor.getString(cursor.getColumnIndexOrThrow("note"))));
                notesModels.add(notes);
                cursor.moveToNext();
            }
            db.close();
        }
        final AlertDialog daftarCatatan = b.create();if (daftarCatatan.getWindow() !=null) {
            daftarCatatan.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        NotesAdapter notesAdapter = new NotesAdapter(notesModels, getApplicationContext(),daftarCatatan);
        listDaftar.setAdapter(notesAdapter);

        if (listDaftar.getAdapter().getCount() < 2) {
            delete.setVisibility(View.INVISIBLE);
        } else {
            delete.setVisibility(View.VISIBLE);
        }

        if (listDaftar.getAdapter().getCount() < 1) {
            judulDaftar.setText("Kosong");
        } else {
            judulDaftar.setText("Daftar Catatan");
        }



        daftarCatatan.show();
    }

    public void onBackPressed () {
        if (layoutCatatan.getVisibility() == View.VISIBLE) {
            layoutCatatan.setVisibility(View.GONE);
            layoutUtama.setVisibility(View.VISIBLE);
        } else {
            finishAffinity();
        }
    }
}