package com.example.a13120240038_karima;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtAngka1, edtAngka2;
    RadioGroup radioGroup;
    TextView txtHasil;
    Button btnClear;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAngka1 = findViewById(R.id.edtAngka1);
        edtAngka2 = findViewById(R.id.edtAngka2);

        radioGroup = findViewById(R.id.radioGroup);

        txtHasil = findViewById(R.id.txtHasil);

        btnClear = findViewById(R.id.btnClear);

        // Event saat text berubah
        edtAngka1.addTextChangedListener(textWatcher);
        edtAngka2.addTextChangedListener(textWatcher);

        // Event saat radio button dipilih
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> hitung());

        // Tombol reset
        btnClear.setOnClickListener(v -> {
            edtAngka1.setText("");
            edtAngka2.setText("");
            radioGroup.clearCheck();
            txtHasil.setText("Hasil : 0");
        });
    }

    // TextWatcher
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            hitung();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    // Method hitung
    private void hitung() {

        String angka1Str = edtAngka1.getText().toString();
        String angka2Str = edtAngka2.getText().toString();

        if (angka1Str.isEmpty() || angka2Str.isEmpty()) {
            txtHasil.setText("Hasil : 0");
            return;
        }

        double angka1 = Double.parseDouble(angka1Str);
        double angka2 = Double.parseDouble(angka2Str);

        double hasil = 0;

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.rbTambah) {

            hasil = angka1 + angka2;

        } else if (selectedId == R.id.rbKurang) {

            hasil = angka1 - angka2;

        } else if (selectedId == R.id.rbKali) {

            hasil = angka1 * angka2;

        } else if (selectedId == R.id.rbBagi) {

            if (angka2 != 0) {
                hasil = angka1 / angka2;
            } else {
                txtHasil.setText("Tidak bisa dibagi 0");
                return;
            }
        }

        txtHasil.setText("Hasil : " + hasil);
    }
}