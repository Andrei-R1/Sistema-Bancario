package net.javaguides.hibernate;

import java.util.ArrayList;
import net.javaguides.hibernate.dao.Transaccion;
import net.javaguides.hibernate.dao.CuentasDeBanco;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
    ArrayList<CuentaDeBanco> Banco = new ArrayList<CuentaDeBanco>();

    CuentaCorriente C1 = new CuentaCorriente(1, "Andrei", 500, true, "Dolares", 1000, true);
    CuentaCorriente C2 = new CuentaCorriente(2, "Dodanim", 500, true, "Dolares", 1000, true);
    CuentaCorriente C3 = new CuentaCorriente(3, "Jeremy", 500, true, "Dolares", 1000, true);

    CuentaAhorro A1 = new CuentaAhorro(4, "Pablo", 2500, true, "Dolares", 5);
    CuentaAhorro A2 = new CuentaAhorro(5, "Eduardo", 2500, true, "Dolares", 5);
    CuentaAhorro A3 = new CuentaAhorro(6, "William", 2500, true, "Dolares", 5);

    Banco.add(C1);
    Banco.add(C2);
    Banco.add(C3);
    Banco.add(A1);
    Banco.add(A2);
    Banco.add(A3);
    
    while (true) {
        MenuPrincipal();
        String MP = Leer.nextLine();
        Boolean Salir = false;

        switch (MP) {
            case "1": {
                System.out.println("Cuentas elegibles: ");
                for(int x = 0; x<Banco.size(); x++){
                    System.out.println(Banco.get(x).getNoCuenta()+"- "+ Banco.get(x).getTitular() +" $"+Banco.get(x).getBalance());
                }
                try {
                    System.out.println("¿Qué cuenta desea utilizar?");
                    String numCuenta = Leer.nextLine();
                    Integer CuentaIndex = Integer.valueOf(numCuenta);
                    CuentaDeBanco TCuenta = getCuentaValida(Banco, Banco.get(CuentaIndex).noCuenta);
                    if(TCuenta != null){
                        System.out.println("Bienvenido "+Banco.get(CuentaIndex).Titular);
                        System.out.println("Indique el monto a depositar");
                        Integer CantidadDeposito = Integer.valueOf(Leer.nextLine());
                        System.out.println("Detalle del deposito");
                        String DetalleDeposito = Leer.nextLine();
                        Banco.get(CuentaIndex).depositarDinero(CantidadDeposito, DetalleDeposito);
                    }
                    break;
                }
                catch (java.lang.NumberFormatException er){
                    System.out.println("Volviendo al menu");
                    break;
                }
                catch (java.lang.IndexOutOfBoundsException e){
                    System.out.println("Dato ingresado no valido");
                    break;
                }
            }
            case "2": {
                System.out.println("Cuentas elegibles: ");
                for(int x = 0; x<Banco.size(); x++){
                    System.out.println(Banco.get(x).getNoCuenta()+"- "+ Banco.get(x).getTitular() +" $"+Banco.get(x).getBalance());
                }
                try {
                    System.out.println("¿Qué cuenta desea utilizar?");
                    String numCuenta = Leer.nextLine();
                    Integer CuentaIndex = Integer.valueOf(numCuenta);
                    CuentaDeBanco TCuenta = getCuentaValida(Banco, Banco.get(CuentaIndex).noCuenta);
                    if(TCuenta != null){
                        System.out.println("Bienvenido "+Banco.get(CuentaIndex).Titular);
                        System.out.println("Indique el monto a retirar");
                        Integer CantidadRetiro = Integer.valueOf(Leer.nextLine());
                        System.out.println("Detalle del retiro");
                        String DetalleRetiro = Leer.nextLine();
                        Banco.get(CuentaIndex).retirarDinero(CantidadRetiro, DetalleRetiro);
                    }
                    break;
                }
                catch (java.lang.NumberFormatException er){
                    System.out.println("Volviendo al menu");
                    break;
                }
                catch (java.lang.IndexOutOfBoundsException e){
                    System.out.println("Dato ingresado no valido");
                    break;
                }
            }
            case "3": {
                System.out.println("Cuentas elegibles: ");
                Boolean SalirLocal = false;
                for(int x = 0; x<Banco.size(); x++){
                    System.out.println(Banco.get(x).getNoCuenta()+"- "+ Banco.get(x).getTitular() +" $"+Banco.get(x).getBalance());
                }
                try {
                    if(SalirLocal == true){
                        break;
                    }
                    System.out.println("¿Qué cuenta desea utilizar?");
                    String numCuenta = Leer.nextLine();
                    Integer CuentaIndex = Integer.valueOf(numCuenta);
                    CuentaDeBanco TCuenta = getCuentaValida(Banco, Banco.get(CuentaIndex).noCuenta);
                    if(TCuenta != null){
                        System.out.println("Bienvenido "+Banco.get(CuentaIndex).Titular);
                        System.out.println("Indique la cuenta a realizar la transferencia");
                        ArrayList<CuentaDeBanco> BancoSinCuenta = new ArrayList<CuentaDeBanco>();
                        for(int x = 0; x<Banco.size(); x++){
                            if(x == CuentaIndex){
                                continue;
                            }
                            else{
                                BancoSinCuenta.add(Banco.get(x));
                            }
                        }
                        for(int x = 0; x<BancoSinCuenta.size(); x++) {
                            System.out.println(BancoSinCuenta.get(x).getNoCuenta()+"- "+ BancoSinCuenta.get(x).getTitular() +" $"+BancoSinCuenta.get(x).getBalance());
                        }
                        Integer CuentaEnvio = Integer.valueOf(Leer.nextLine());
                        CuentaDeBanco CuentaRecibo = getCuentaValida(BancoSinCuenta, BancoSinCuenta.get(CuentaEnvio).noCuenta);
                        if(CuentaRecibo != null){
                            break;
                        }
                        System.out.println("Indique el monto a transferir a "+BancoSinCuenta.get(CuentaEnvio).Titular);
                        Integer CantidadTransaccion = Integer.valueOf(Leer.nextLine());
                        System.out.println("Detalle de la transferencia");
                        String DetalleTransaccion = Leer.nextLine();
                        Banco.get(CuentaIndex).enviarTransferencia(CuentaEnvio, BancoSinCuenta.get(CuentaEnvio).Titular, CantidadTransaccion, DetalleTransaccion);
                        if(Banco.get(CuentaIndex).Balance - CantidadTransaccion > 0){
                            BancoSinCuenta.get(CuentaEnvio).recibirTransferencia(Banco.get(CuentaIndex).noCuenta, CantidadTransaccion, DetalleTransaccion);
                        }
                    }
                    break;
                }
                catch (java.lang.NumberFormatException er){
                    System.out.println("Volviendo al menu");
                    break;
                }
                catch (java.lang.IndexOutOfBoundsException e){
                    System.out.println("Dato ingresado no valido");
                    break;
                }
            }
            case "4": {
                Salir = true;
                System.out.println("Saliendo del sistema");
                break;
            }
            case "Historial":{
                for(int x = 0; x<Banco.size(); x++){
                    Banco.get(x).verHistorialTransacciones();
                }
            }
        }
        if(Salir == true){
            break;
        }
    }
    try {
        Leer.close();
        System.out.println("Saliendo del sistema");
    }
    catch (java.lang.IllegalStateException error){
        System.out.println("Saliendo del sistema...");
    }
	}
}
