package net.javaguides.hibernate.dao;

import java.util.ArrayList;
import java.util.HashMap;

public class CuentaDeBanco {
    protected Integer noCuenta;
    protected String Titular;
    protected Integer Balance;
    protected Boolean Activa;
    protected String Moneda;
    protected ArrayList<String> Transaccion= new ArrayList<String>();
    protected HashMap<Integer, Transaccion> Transacciones = new HashMap<Integer, Transaccion>();
    protected Integer nTransaccion;

    public Integer getNoCuenta() {
        return noCuenta;
    }

    public String getTitular() {
        return Titular;
    }

    public Integer getBalance() {
        return Balance;
    }

    public Boolean getActiva() {
        return Activa;
    }

    public String getMoneda() {
        return Moneda;
    }

    public CuentaDeBanco(Integer nCuenta, String titular, Integer balance, Boolean activa, String moneda) {
        noCuenta = nCuenta;
        Titular = titular;
        Balance = balance;
        Activa = activa;
        Moneda = moneda;
        nTransaccion = 0;
    }

    public void mostrarEstadoDeCuenta() {
        System.out.println("Numero de cuenta: " + noCuenta);
        System.out.println("Nombre del titular: " + Titular);
        System.out.println("Balance de la cuenta: " + Balance);
        System.out.println("Moneda: " + Moneda);
        Boolean activa=false;
        String estadoCuenta="";
        if(Activa!=activa){
            estadoCuenta="Activa";
        }
        else{
            estadoCuenta="Inactiva";
        }
        System.out.println("Estado de la cuenta: " + Activa);
    }

    public void depositarDinero(int deposito, String detalle) {
        nTransaccion++;
        int nBalance = Balance + deposito;
        Balance = nBalance;
        System.out.println("Deposito realizado");
        System.out.println("Nuevo balance: " + Balance);
        Transaccion Deposito = new Transaccion(nTransaccion, deposito, detalle,"Deposito");
        Transacciones.put(nTransaccion, Deposito);
        Transaccion.add(Deposito.ObtenerInfoTransaccion());
    }

    public void retirarDinero(int retiro, String detalle) {
        if (Balance - retiro < 0) {
            System.out.println("Transaccion no realizada");
        } else {
            nTransaccion++;
            int nBalance = Balance - retiro;
            Balance = nBalance;
            System.out.println("Retiro realizado");
            System.out.println("Nuevo balance: " + Balance);
            Transaccion Retiro = new Transaccion(nTransaccion, retiro, detalle,"Retiro");
            Transacciones.put(nTransaccion, Retiro);
            Transaccion.add(Retiro.ObtenerInfoTransaccion());
        }
    }

    public void enviarTransferencia(Integer nCuenta, String Destinatario, Integer Monto, String Detalle) {
        if (Balance - Monto < 0) {
            System.out.println("Transaccion no realizada");
        } else {
            nTransaccion++;
            int nBalance = Balance - Monto;
            Balance = nBalance;
            System.out.println("Transferencia enviada");
            System.out.println("Nuevo balance: " + Balance);
            Transaccion EnvioTransferencia = new Transaccion(nTransaccion, Monto, nCuenta, Detalle,"Envio de Transferencia");
            Transacciones.put(nTransaccion, EnvioTransferencia);
            Transaccion.add(EnvioTransferencia.ObtenerInfoTransaccion());
        }
    }

    public void recibirTransferencia(Integer nCuentaRemitente, Integer Monto, String Detalle) {
        nTransaccion++;
        int nBalance = Balance + Monto;
        Balance = nBalance;
        System.out.println("Transferencia recibida");
        System.out.println("Nuevo balance: " + Balance);
        Transaccion ReciboTransferencia = new Transaccion(nTransaccion, Monto, nCuentaRemitente, Detalle,"Recepcion de Transferencia");
        Transacciones.put(nTransaccion, ReciboTransferencia);
        Transaccion.add(ReciboTransferencia.ObtenerInfoTransaccion());
    }

    public void verHistorialTransacciones() {
        if (nTransaccion == 0) {
            System.out.println("No se han realizado transacciones");
        } else {
            System.out.println("Historial de transacciones de la cuenta: " + noCuenta);
            System.out.println("Realizado por: " + Titular);
            for (int x = 0; x < Transaccion.size(); x++) {
                System.out.println(Transaccion.get(x));
            }
        }
    }

}

class CuentaCorriente extends CuentaDeBanco {
    private Integer BalanceMaximo;
    private Boolean TieneSeguro;

    public CuentaCorriente(Integer nCuenta, String titular, Integer balance, Boolean activa, String moneda,
                           Integer balanceMaximo, Boolean tieneSeguro) {
        super(nCuenta, titular, balance, activa, moneda);
        BalanceMaximo = balanceMaximo;
        TieneSeguro = tieneSeguro;
    }

    public Boolean getTieneSeguro() {
        return TieneSeguro;
    }

    public Integer getBalanceMaximo() {
        return BalanceMaximo;
    }

    @Override public void depositarDinero(int deposito, String detalle) {
        if (deposito + getBalance() > BalanceMaximo) {
            System.out.println("Deposito no realizado");
        } else {
            nTransaccion++;
            int nBalance = Balance + deposito;
            Balance = nBalance;
            System.out.println("Deposito realizado");
            System.out.println("Nuevo balance: " + Balance);
            Transaccion DepositoCC = new Transaccion(nTransaccion, deposito, detalle,"Deposito");
            Transacciones.put(nTransaccion, DepositoCC);
            Transaccion.add(DepositoCC.ObtenerInfoTransaccion());
        }
    }

    @Override
    public void recibirTransferencia(Integer nCuentaRemitente, Integer Monto, String Detalle) {
        if (Monto + getBalance() > BalanceMaximo) {
            System.out.println("Transferencia no realizada");
        } else {
            nTransaccion++;
            int nBalance = Balance + Monto;
            Balance = nBalance;
            System.out.println("Transferencia recibida");
            System.out.println("Nuevo balance: " + Balance);
            Transaccion ReciboTransferenciaCC = new Transaccion(nTransaccion, Monto, nCuentaRemitente, Detalle,"Recepcion de Transferencia");
            Transacciones.put(nTransaccion, ReciboTransferenciaCC);
            Transaccion.add(ReciboTransferenciaCC.ObtenerInfoTransaccion());
        }
    }
}

class CuentaAhorro extends CuentaDeBanco {
    private Integer Intereses;

    public CuentaAhorro(Integer nCuenta, String titular, Integer balance, Boolean activa, String moneda,
                        Integer intereses) {
        super(nCuenta, titular, balance, activa, moneda);
        Intereses = intereses;
    }

    public Integer getIntereses() {
        return Intereses;
    }

    @Override public void retirarDinero(int retiro, String detalle) {
        System.out.println("Esta cuenta no puede realizar retiros");
    }

    public void actualizarIntereses(int intereses) {
        Intereses = intereses;
    }
}
