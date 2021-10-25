package net.javaguides.hibernate.dao;

public class Transaccion{
    protected Integer Id;
    protected Integer Monto;
    protected Integer Cuenta;
    protected String Detalle;
    protected String TipoTransaccion;

    public Transaccion(int id, int monto, int cuenta, String detalle, String tipoTransaccion){
        Id = id;
        Monto = monto;
        Cuenta = cuenta;
        Detalle = detalle;
        TipoTransaccion = tipoTransaccion;
    }

    public Transaccion(int id, int monto, String detalle, String tipoTransaccion){
        Id = id;
        Monto = monto;
        Detalle = detalle;
        TipoTransaccion = tipoTransaccion;
    }

    public String ObtenerInfoTransaccion(){
        if(Cuenta==null){
            return "ID: "+Id+" Monto: "+Monto+" Detalle: "+Detalle+" Tipo de Transacción: "+TipoTransaccion;
        }
        else{
            return "ID: "+Id+" Monto: "+Monto+" Cuenta Destinatario: "+Cuenta+" Detalle: "+Detalle+" Tipo de Transacción: "+TipoTransaccion;
        }
    }
}