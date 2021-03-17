package com.sonidosdecochesymotores.sonidosdecochesymotores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MostrarCoche extends AppCompatActivity {

    Coche coche;
    ImageView imagen;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_coche);
        coche = (Coche) getIntent().getExtras().getSerializable("coche");
        boolean bloqueo = getIntent().getBooleanExtra("bloqueo", false);
        if(bloqueo) setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        pantallaCompleta();
        sonido();
        añadirBanner();
        imagen = findViewById(R.id.imagenMostrarCoche);
        imagen.setImageResource(coche.getImagen());
        imagen.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Funciona", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        imagen.setClickable(true);
        imagen.setOnClickListener(view -> pantallaCompleta());
    }

    private void sonido() {
        String nombre = coche.getNombre().toLowerCase().replaceAll(" ", "")
                .toLowerCase().replace("-", "").replace("_", "").replace("(", "").replace(")", "");
        int id = this.getResources().getIdentifier(nombre, "raw", this.getPackageName());
        mp = MediaPlayer.create(getApplicationContext(), id);
        mp.setLooping(true);
        mp.start();
    }

    private void añadirBanner() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                pantallaCompleta();
            }
        });
    }

    private void pantallaCompleta() {
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onPause() {
        mp.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mp.stop();
        mp.release();
        super.onDestroy();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        pantallaCompleta();
        sonido();
    }
}