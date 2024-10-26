/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_parcial02_java;

/**
 *
 * @author juras
 */
import java.util.*;


public class Proyecto_Parcial02_JAVA {

    /**
     * @param args the command line arguments
     */
    
    //Crea un scanner, y 3 arrayLists, explicadas abajo
    Scanner sc = new Scanner(System.in);
    ArrayList<medicamento> lista_med = new ArrayList<>(); // Lista de medicamentos en inventario
    ArrayList<venta> ventas = new ArrayList<>();  // Historial de ventas
    ArrayList<medicamento> carrito = new ArrayList<>(); // Carrito de medicamentos para compras
    
    //Módulo de Medicamentos
    class medicamento {
        String clave_med;
        String nombre;
        String descripcion;
        double precio;
        
        //Constructor de medicamento
        medicamento(String clave_med, String nombre, String descripcion, double precio) {
            this.clave_med = clave_med;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precio = precio;
        }
    }
    
    //Módulo de Inventario
    class inventario {
        String clave_inv;
        String tipo;
        int stock;
        double precio_uni;
        String fecha_mov;
        
        //Constructor de inventario
        inventario(String clave_inv, String tipo, int stock, double precio_uni, String fecha_mov) {
            this.clave_inv = clave_inv;
            this.tipo = tipo;
            this.stock = stock;
            this.precio_uni = precio_uni;
            this.fecha_mov = fecha_mov;
        }
        
        //Método para agregar un medicamento al inventario
        void agregar() {
            //Pide los datos del medicamento
            sc.nextLine();
            System.out.print("\nInserte la clave del medicamento: ");
            String clave_med_temp = sc.nextLine();
            System.out.print("Inserte el nombre del medicamento: ");
            String nombre_temp = sc.nextLine();
            System.out.print("Inserte la descripcion del medicamento: ");
            String descripcion_temp = sc.nextLine();
            System.out.print("Inserte el precio del medicamento: ");
            double precio_temp = sc.nextDouble();
            
            //Lo crea y lo añade al arrayList
            medicamento nuevo = new medicamento(clave_med_temp, nombre_temp, descripcion_temp, precio_temp);
            lista_med.add(nuevo);
            System.out.println("Medicamento agregado exitosamente");
        }
        
        //Método para modificar un medicamento
        void modificar() {
            //Pide el medicamento que desea modificar
            System.out.print("\nInserte la clave del medicamento que desea modificar: ");
            sc.nextLine();
            String busq = sc.nextLine();
            
            //Lo modifica si lo encuentra
            for(int i=0;i<lista_med.size();i++){
                if(lista_med.get(i).clave_med.equals(busq)){
                    //Pide los nuevos datos
                    System.out.print("Inserte el nuevo nombre del medicamento: ");
                    String nombre_temp = sc.nextLine();
                    System.out.print("Inserte la nueva descripcion del medicamento: ");
                    String descripcion_temp = sc.nextLine();
                    System.out.print("Inserte el nuevo precio del medicamento: ");
                    double precio_temp = sc.nextDouble();
                    
                    //Modifica el medicamento
                    medicamento nuevo = new medicamento(lista_med.get(i).clave_med,nombre_temp,descripcion_temp,precio_temp);
                    lista_med.set(i,nuevo);
                    System.out.println("Medicamento modificado exitosamente");
                    break;
                }
            }
        }
        
        //Método para eliminar un medicamento
        void eliminar() {
            //Lo busca
            System.out.print("\nInserte la clave del medicamento que desea eliminar: ");
            sc.nextLine();
            String busq = sc.nextLine();
            for(int i=0;i<lista_med.size();i++){
                //Si lo encuentra lo elimina
                if(lista_med.get(i).clave_med == null ? busq == null : lista_med.get(i).clave_med.equals(busq)){
                    lista_med.remove(i);
                    System.out.println("Medicamento eliminado exitosamente");
                    break;
                }
            }
        }
        
        //Método para consultar todos los medicamentos
        void consultar() {
            //Sirve por si no hay ningun medicamento
            if (lista_med.isEmpty()) {
                System.out.println("No hay medicamentos en el inventario.");
                return;
            }
            
            //Muestra la lista de medicamentos
            for (int i=0;i<lista_med.size();i++) {
                medicamento med = lista_med.get(i);
                System.out.println("\nMedicamento: " + (i + 1));
                System.out.println("Clave: " + med.clave_med);
                System.out.println("Nombre: " + med.nombre);
                System.out.println("Descripcion: " + med.descripcion);
                System.out.println("Precio: " + med.precio);
            }
        }
    }
    
//Módulo de Compra
class compra {
    String clave_com;
    String fecha_com;
    double total = 0;
    
    //Constructor de compra
    compra(String clave_com, String fecha_com) {
        this.clave_com = clave_com;
        this.fecha_com = fecha_com;
    }

    //Método para agregar un medicamento al carrito
    void agregarCar() {
        //Busca el medicamento y comprueba su existencia
        System.out.print("\nInserte la clave del medicamento que desea comprar: ");
        String busq = sc.nextLine();
        for (int i = 0; i < lista_med.size(); i++) {
            medicamento med = lista_med.get(i);
            //Lo añade al carrito y lo retira del inventario
            if (med.clave_med.equals(busq)) {
                carrito.add(med);
                lista_med.remove(i);
                System.out.println("Medicamento agregado al carrito.");
                return;
            }
        }
        System.out.println("Medicamento no encontrado en el inventario.");
    }

    //Método para calcular el total de la compra
    double calculoTotal() {
        total = 0;
        for (medicamento med : carrito) {
            total += med.precio;
        }
        return total;
    }
}

    
    //Módulo de Venta
    class venta {
        String clave_ven;
        String fecha_ven;
        String pago;
        
        //Método para elegir el método de pago
        void procesarPago() {
            System.out.print("Elija un metodo de pago: ");
            this.pago = sc.nextLine();
        }
        
        //Método para agregar una venta al historial, pidiendo la clave y fecha de la venta
        void agregarVenta() {
            System.out.print("Inserte la clave de venta: ");
            this.clave_ven = sc.nextLine();
            System.out.print("Inserte la fecha de venta: ");
            this.fecha_ven = sc.nextLine();
        }
        
        //Método para consultar el historial de ventas
        void consultarHistorial() {
            //Por si esta vacia
            if(ventas.isEmpty()){
                System.out.println("No hay ventas registradas.");
                return;
            }
            
            //Muestra la lista de ventas
            for(int i=0;i<ventas.size();i++) {
                venta ven = ventas.get(i);
                System.out.println("\nVenta : " + (i + 1));
                System.out.println("Clave: " + ven.clave_ven);
                System.out.println("Fecha: " + ven.fecha_ven);
                System.out.println("Forma de pago: " + ven.pago);
            }
        }
    }
    
    public static void main(String[] args) {
        //Se crea una instancia de la clase para acceder a métodos no estáticos (LO SACAMOS DE UN FORO DE INTERNET)
        Proyecto_Parcial02_JAVA proyecto = new Proyecto_Parcial02_JAVA();
        
        //Se crea la opción del menú principal, la del inventario y la de ventas
        int opp = 0, oppi = 0, oppv = 1;
        Scanner sc = new Scanner(System.in);
        
        //Se ingresan los datos del inventario y se crea
        System.out.print("\nInserte la clave del inventario: ");
        String clave_inv_temp = sc.nextLine();
        System.out.print("Inserte el tipo de entrada y salida: ");
        String tipo_temp = sc.nextLine();
        System.out.print("Inserte el precio base por unidad: ");
        double precio_uni_temp = sc.nextDouble();
        System.out.print("Inserte la fecha de movimiento: ");
        sc.nextLine();
        String fecha_mov_temp = sc.nextLine();
        Proyecto_Parcial02_JAVA.inventario uno = proyecto.new inventario(clave_inv_temp, tipo_temp, 0, precio_uni_temp, fecha_mov_temp);

        //Menú principal
        while (opp != 4) {
            oppi = 0;
            //Pide las opciones
            System.out.println("\n\t1. Inventario\n\t2. Realizar compra\n\t3. Historial de ventas\n\t4. Salir");
            opp = sc.nextInt();
            sc.nextLine();
            switch(opp){
                case 1:
                    while(oppi!= 5){
                        //Menú de inventario, usando todas los métodos de la clase inventario
                        System.out.println("\n\t1. Agregar un medicamento\n\t2. Modificar un medicamento\n\t3. Eliminar un medicamento\n\t4. Consultar medicamentos\n\t5. Salir");
                        oppi = sc.nextInt();
                        switch (oppi) {
                            case 1:
                                uno.agregar();
                                break;
                            case 2:
                                uno.modificar();
                                break;
                            case 3:
                                uno.eliminar();
                                break;
                            case 4:
                                uno.consultar();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Inserte una opcion valida");
                        }
                    }
                    break;
                case 2:
                    //Se hace una compra, pidiendo primero la clave y la fecha
                    System.out.print("\nInserte la clave de la compra: ");
                    String clave_com_temp = sc.nextLine();
                    System.out.print("Inserte la fecha de compra: ");
                    String fecha_com_temp = sc.nextLine();
                    Proyecto_Parcial02_JAVA.compra nueva = proyecto.new compra(clave_com_temp, fecha_com_temp);
                    oppv = 1;
                    //Menú de venta de medicamentos
                    while (oppv != 0) {
                        System.out.print("\nDesea agregar otro medicamento al carrito? (si = 1, no = 0): ");
                        oppv = sc.nextInt();
                        if (oppv == 0){
                            break;
                        }else{
                            sc.nextLine();
                            nueva.agregarCar();
                        }
                    }
                    //Se muestra el total y se agrega la venta, usando los métodos de la clase venta para obtener el método de pago y la fecha y clave de la venta
                    System.out.println("El total es: " + nueva.calculoTotal());
                    Proyecto_Parcial02_JAVA.venta vent = proyecto.new venta();
                    vent.agregarVenta();
                    vent.procesarPago();
                    proyecto.ventas.add(vent);
                    break;
                case 3:
                    //Se consulta el historial de ventas
                    Proyecto_Parcial02_JAVA.venta v = proyecto.new venta();
                    v.consultarHistorial();
                    break;
                case 4:
                    //Se sale del switch, cerrando el programa
                    System.out.println("\nGracias por venir");
                    break;
                default:
                    //Pide una opcion valida
                    System.out.println("Inserte una opcion valida");
            }
        }
        sc.close();
    }
}