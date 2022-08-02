package com.example.appexamen;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.appexamen.databinding.ActivityInterfaz2Binding;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class interfaz2 extends AppCompatActivity {
    ActivityInterfaz2Binding binding1;
    Bitmap bitmap;
    ActivityResultLauncher<Intent> activitResultLauncher;
    public static ArrayList<Usuarios> lista_usuarios= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding1=ActivityInterfaz2Binding.inflate(getLayoutInflater());
        setContentView(binding1.getRoot());

        activitylauncher();
        binding1.imguser.setOnClickListener(v -> {
            abrircamara();
        });
        binding1.btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                if (binding1.txtusuario.getText().toString().length() > 0) {
                    if (binding1.txtcorreo.getText().toString().equalsIgnoreCase(binding1.txtcorreo.getText().toString())) {
                        Matcher mather = pattern.matcher(binding1.txtcorreo.getText().toString());
                        if(mather.find() == true) {
                            if (binding1.txtclve.getText().toString().length() < 5) {
                                binding1.txtvalidacion.setText("Clave minimo 5 digitos");
                            } else {
                                if (binding1.txtclve.getText().toString().equals(binding1.txtclve2.getText().toString())) {

                                        abrir(binding1.txtusuario.getText().toString(), binding1.txtclve.getText().toString(),binding1.txtcorreo.getText().toString());

                                } else {
                                    binding1.txtvalidacion.setText("Contraseñas no coinciden");
                                }
                            }
                        }else{
                            binding1.txtvalidacion.setText("Correo invalido");
                        }
                    } else{
                        binding1.txtvalidacion.setText("Correos no coinciden");
                    }
                }else {
                    binding1.txtvalidacion.setText("Llene todos los campos");
                }
            }

        });

        binding1.txtclve.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String clave=binding1.txtclve.getText().toString();
                int c=contarCaracteres(clave);
                if(c>=4 && clave.length()>=12 ) {
                    binding1.rtbValoracion.setRating(5);
                    binding1.txtValcontraseA.setText("La clave de seguridad se considera Alta");
                }else if (c>=2 && clave.length()>=10){
                    binding1.rtbValoracion.setRating(4);
                    binding1.txtValcontraseA.setText("La clave de seguridad se considera Media Alta");
                }else if (c>=1 && clave.length()>=8){
                    binding1.rtbValoracion.setRating(3);
                    binding1.txtValcontraseA.setText("La clave de seguridad se considera media");
                }else if (clave.length()>=8){
                    binding1.rtbValoracion.setRating(2);
                    binding1.txtValcontraseA.setText("La clave de seguridad se considera baja");
                }else{
                    binding1.rtbValoracion.setRating(1);
                    binding1.txtValcontraseA.setText("La clave de seguridad se considera insegura");
                }
            }
        });
    }

    private void abrir(String usuario, String clave, String correo){
        Intent intent = new Intent(this,Interfaz3.class);
        Usuarios sitio = new Usuarios(usuario,clave,correo,bitmap);
        lista_usuarios.add(sitio);

        intent.putExtra(Interfaz3.users_key,sitio);
        intent.putExtra(Interfaz3.BITMAP_KEY, bitmap);
        startActivity(intent);
    }

    private void abrircamara(){
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activitResultLauncher.launch(camaraIntent);
    }

    public void activitylauncher(){
        activitResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()==RESULT_OK){
                    if(result.getData() != null){
                        bitmap=result.getData().getExtras().getParcelable("data");
                        binding1.imguser.setImageBitmap(bitmap);
                    }
                }
            }
        });
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