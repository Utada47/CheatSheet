package com.example.konversisuhu;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtSuhu;
    RadioGroup radioGroup;

    RadioButton rbCelciusFahrenheit;
    RadioButton rbCelciusKelvin;
    RadioButton rbFahrenheitCelcius;

    TextView txtHasil;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSuhu = findViewById(R.id.edtSuhu);

        radioGroup = findViewById(R.id.radioGroup);

        rbCelciusFahrenheit = findViewById(R.id.rbCelciusFahrenheit);
        rbCelciusKelvin = findViewById(R.id.rbCelciusKelvin);
        rbFahrenheitCelcius = findViewById(R.id.rbFahrenheitCelcius);

        txtHasil = findViewById(R.id.txtHasil);
        btnReset = findViewById(R.id.btnReset);

        // Event saat text berubah
        edtSuhu.addTextChangedListener(textWatcher);

        // Event radio button
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> hitungKonversi());

        // Tombol reset
        btnReset.setOnClickListener(v -> {
            edtSuhu.setText("");
            radioGroup.clearCheck();
            txtHasil.setText("Hasil : 0");
        });
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            hitungKonversi();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void hitungKonversi() {

        String suhuStr = edtSuhu.getText().toString();

        if (suhuStr.isEmpty()) {
            txtHasil.setText("Hasil : 0");
            return;
        }

        double suhu = Double.parseDouble(suhuStr);

        double hasil = 0;
        String satuan = "";

        if (rbCelciusFahrenheit.isChecked()) {

            hasil = (suhu * 9/5) + 32;
            satuan = "°F";

        }
        else if (rbCelciusKelvin.isChecked()) {

            hasil = suhu + 273.15;
            satuan = "K";

        }
        else if (rbFahrenheitCelcius.isChecked()) {

            hasil = (suhu - 32) * 5/9;
            satuan = "°C";
        }

        txtHasil.setText("Hasil : " + String.format("%.2f", hasil) + " " + satuan);
    }
}