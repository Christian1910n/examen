package com.example.appexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.example.appexamen.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    public static Bitmap mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int con=0;
                for (int i=0; i<interfaz2.lista_usuarios.size(); i++){
                    if(interfaz2.lista_usuarios.get(i).getUsuario().equalsIgnoreCase(binding.txtnombre.getText().toString()) && interfaz2.lista_usuarios.get(i).getClave().equalsIgnoreCase(binding.txtclave.getText().toString())){
                        abrir1(binding.txtnombre.getText().toString(),binding.txtclave.getText().toString(),"");
                        con=1;
                    }
                }

                if(con!=1){
                    binding.lbMensaje.setText("DATOS INCORRECTOS");
                }


            }
        });

        binding.lbBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrir();
            }
        });
    }
    public void abrir1(String usuario, String clave, String correo){
        Intent intent=new Intent(this,Interfaz3.class);

        Usuarios sitio = new Usuarios(usuario,clave,correo,null);
        intent.putExtra(Interfaz3.users_key,sitio);
        intent.putExtra(Interfaz3.BITMAP_KEY, mapa);
        startActivity(intent);
    }

    public void abrir(){
        Intent intent=new Intent(this,interfaz2.class);

        startActivity(intent);
    }


}

