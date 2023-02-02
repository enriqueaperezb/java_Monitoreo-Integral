package com.ingeniandolo.monitoreointegral.estructura;

public class Finca {
    private long _id;
    private String id;
    private String nombre;

    public long get_Id() {
        return _id;
    }

    public void set_Id(long _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return nombre;
    }
}