package com.example.appexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.appexamen.databinding.ActivityInterfaz2Binding;
import com.example.appexamen.databinding.ActivityInterfaz3Binding;

public class Interfaz3 extends AppCompatActivity {
    public static final String users_key ="usuarios";
    public static final String BITMAP_KEY = "bitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInterfaz3Binding binding= ActivityInterfaz3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Bundle extras= getIntent().getExtras();
        Usuarios users = extras.getParcelable(users_key);

        binding.setUsers(users);

        String clave=binding.getUsers().getClave();
        int c=contarCaracteres(clave);
        if(c>=4 && clave.length()>=12 ) {
            binding.rtbValoracion.setRating(5);
            binding.txtValcontraseA.setText("La clave de seguridad se considera Alta");
        }else if (c>=2 && clave.length()>=10){
            binding.rtbValoracion.setRating(4);
            binding.txtValcontraseA.setText("La clave de seguridad se considera Media Alta");
        }else if (c>=1 && clave.length()>=8){
            binding.rtbValoracion.setRating(3);
            binding.txtValcontraseA.setText("La clave de seguridad se considera media");
        }else if (clave.length()>=8){
            binding.rtbValoracion.setRating(2);
            binding.txtValcontraseA.setText("La clave de seguridad se considera baja");
        }else{
            binding.rtbValoracion.setRating(1);
            binding.txtValcontraseA.setText("La clave de seguridad se considera insegura");
        }


        Bitmap bitmap =extras.getParcelable(BITMAP_KEY);
        if(bitmap!=null){
            binding.imguser.setImageBitmap(bitmap);
            MainActivity.mapa=extras.getParcelable(BITMAP_KEY);
        }



    }

    public int contarCaracteres(String texto){
        int con=0;

        char[] car= texto.toCharArray();
        for (int i=0; i<car.length; i++){
            if(Character.isLetter(car[i])){
            }else if (Character.isDigit(car[i])){
            }else if (Character.isSpaceChar(car[i])){
            }else{
                ++con;
            }
        }

        return con;
    }
}