package hu.petrik.javakopapirollo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView valasztottImg;
    private ImageView generaltImg;
    private TextView resultTextView;
    private Button btnKo;
    private Button btnPapir;
    private Button btnOllo;

    private Valasztas valasztott;
    private Valasztas generalt;
    private Random rnd;

    private int emberGyozelem;
    private int gepGyozelem;

    private enum Valasztas {
        KO,
        PAPIR,
        OLLO
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addEventListeners();
    }

    private void addEventListeners() {
        btnKo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valasztott = Valasztas.KO;
                valasztottImg.setImageResource(R.drawable.rock);
                jatek();
            }
        });
        btnPapir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valasztott = Valasztas.PAPIR;
                valasztottImg.setImageResource(R.drawable.paper);
                jatek();
            }
        });
        btnOllo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valasztott = Valasztas.OLLO;
                valasztottImg.setImageResource(R.drawable.scissors);
                jatek();
            }
        });
    }

    private void jatek() {
        rnd = new Random();
        int rInt = rnd.nextInt(3);
        switch (rInt){
            case 0:
                generalt = Valasztas.KO;
                generaltImg.setImageResource(R.drawable.rock);
                break;
            case 1:
                generalt = Valasztas.PAPIR;
                generaltImg.setImageResource(R.drawable.paper);
                break;
            case 2:
                generalt = Valasztas.OLLO;
                generaltImg.setImageResource(R.drawable.scissors);
                break;
        }
        if (valasztott != generalt){
            if ((valasztott == Valasztas.KO && generalt == Valasztas.OLLO) || (valasztott == Valasztas.PAPIR && generalt == Valasztas.KO) || (valasztott == Valasztas.OLLO && generalt == Valasztas.PAPIR)){
                Toast.makeText(this, "Te nyertél", Toast.LENGTH_SHORT).show();
                emberGyozelem++;
            }else {
                Toast.makeText(this, "A számítógép nyert", Toast.LENGTH_SHORT).show();
                gepGyozelem++;
            }
        }else {
            Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();
        }
        resultTextView.setText(String.format("Eredmény: Ember: %d - Computer: %d", emberGyozelem, gepGyozelem));
        if (emberGyozelem == 3){
            new AlertDialog.Builder(MainActivity.this).setTitle("Győzelem")
                    .setMessage("Szeretne új játékot játszani?")
                    .setPositiveButton("IGEN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ujJatek();
                        }
                    }).setNegativeButton("NEM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).setCancelable(false).show();
        }else if (gepGyozelem == 3){
            new AlertDialog.Builder(MainActivity.this).setTitle("Vereség")
                    .setMessage("Szeretne új játékot játszani?")
                    .setPositiveButton("IGEN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ujJatek();
                        }
                    }).setNegativeButton("NEM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).setCancelable(false).show();
        }
    }

    private void ujJatek() {
        emberGyozelem = 0;
        gepGyozelem = 0;
        resultTextView.setText(String.format("Eredmény: Ember: %d - Computer: %d", emberGyozelem, gepGyozelem));

    }

    private void init(){
        valasztottImg = findViewById(R.id.valasztottImg);
        generaltImg = findViewById(R.id.generaltImg);
        resultTextView = findViewById(R.id.resultTextView);
        btnKo = findViewById(R.id.btnKo);
        btnPapir = findViewById(R.id.btnPapir);
        btnOllo = findViewById(R.id.btnOllo);

        emberGyozelem = 0;
        gepGyozelem = 0;
    }
}