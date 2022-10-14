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

    private ImageView gepHp1;
    private ImageView gepHp2;
    private ImageView gepHp3;
    private ImageView emberHp1;
    private ImageView emberHp2;
    private ImageView emberHp3;

    private ImageView valasztottImg;
    private ImageView generaltImg;
    private TextView dontetlenTextView;
    private Button btnKo;
    private Button btnPapir;
    private Button btnOllo;

    private Valasztas valasztott;
    private Valasztas generalt;
    private Random rnd;

    private int dontetlenekSzama;
    private int emberElet;
    private int gepElet;

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
                gepElet--;
            }else {
                Toast.makeText(this, "A számítógép nyert", Toast.LENGTH_SHORT).show();
                emberElet--;
            }
        }else {
            dontetlenekSzama++;
            dontetlenTextView.setText(String.format("Döntetlenek száma: %d", dontetlenekSzama));
            Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();
        }
        switch (gepElet){
            case 2:
                gepHp3.setImageResource(R.drawable.heart1);
                break;
            case 1:
                gepHp2.setImageResource(R.drawable.heart1);
                break;
            case 0:
                gepHp1.setImageResource(R.drawable.heart1);
                break;
        }
        switch (emberElet){
            case 2:
                emberHp3.setImageResource(R.drawable.heart1);
                break;
            case 1:
                emberHp2.setImageResource(R.drawable.heart1);
                break;
            case 0:
                emberHp1.setImageResource(R.drawable.heart1);
                break;
        }
        if (gepElet == 0){
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
        }else if (emberElet == 0){
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
        emberElet = 3;
        gepElet = 3;
        dontetlenekSzama = 0;
        gepHp1.setImageResource(R.drawable.heart2);
        gepHp2.setImageResource(R.drawable.heart2);
        gepHp3.setImageResource(R.drawable.heart2);
        emberHp1.setImageResource(R.drawable.heart2);
        emberHp2.setImageResource(R.drawable.heart2);
        emberHp3.setImageResource(R.drawable.heart2);
    }

    private void init(){
        gepHp1 = findViewById(R.id.gepHp1);
        gepHp2 = findViewById(R.id.gepHp2);
        gepHp3 = findViewById(R.id.gepHp3);
        emberHp1 = findViewById(R.id.emberHp1);
        emberHp2 = findViewById(R.id.emberHp2);
        emberHp3 = findViewById(R.id.emberHp3);

        valasztottImg = findViewById(R.id.valasztottImg);
        generaltImg = findViewById(R.id.generaltImg);
        dontetlenTextView = findViewById(R.id.dontetlenTextView);
        btnKo = findViewById(R.id.btnKo);
        btnPapir = findViewById(R.id.btnPapir);
        btnOllo = findViewById(R.id.btnOllo);

        ujJatek();
    }
}