package com.example.appexamen;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Usuarios implements Parcelable {
    private String usuario;
    private String clave;
    private String correo;
    private Bitmap bitmap;

    public Usuarios(String usuario, String clave, String correo, Bitmap bitmap) {
        this.usuario = usuario;
        this.clave = clave;
        this.correo = correo;
        this.bitmap = bitmap;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    protected Usuarios(Parcel in) {
        usuario = in.readString();
        clave = in.readString();
        correo = in.readString();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(usuario);
        dest.writeString(clave);
        dest.writeString(correo);
        dest.writeParcelable(bitmap, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Usuarios> CREATOR = new Creator<Usuarios>() {
        @Override
        public Usuarios createFromParcel(Parcel in) {
            return new Usuarios(in);
        }

        @Override
        public Usuarios[] newArray(int size) {
            return new Usuarios[size];
        }
    };
}
