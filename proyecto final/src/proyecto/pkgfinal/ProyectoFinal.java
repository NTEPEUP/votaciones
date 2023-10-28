/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.pkgfinal;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nelson
 */
public class ProyectoFinal {

    
    public static void main(String[] args) {
        // CONFIGURACION INICAL DEL SISTEMA
         
        Scanner scan = new Scanner(System.in);
        
 
        //CONFIGURACION INCIAL DE SISTEMA DE VOTACIONES 
        
         // Verificar si el archivo de configuración ya existe  
        File archivoConfiguracion = new File("admin.txt");

            if (archivoConfiguracion.exists()) {
            // Si el archivo de configuración ya existe, cargar la contraseña del administrador
            
            String administrador= cargarContraseñaAdmin(archivoConfiguracion);

            // Solicitar inicio de sesión
            System.out.println("Ingrese la contrasenia del administrador: ");
            String contraseñaIngresada = scan.nextLine();
            if (contraseñaIngresada.equals(administrador)) {
                // Inicio de sesión exitoso, mostrar el menú del sistema
               MenuGeneral();
               
               
            } else {
                System.out.println("Contrasenia incorrecta. Saliendo del programa.");
            }
            } else {
              
            // Solicitar una nueva contraseña para el administrador
            System.out.println("Configuracion inicial: Ingrese una contrasenia para el usuario administrador: ");
            String administrador= scan.nextLine();

            // Guardar la contraseña en el archivo creado  de configuración       
            guardarContraseñaAdmin(archivoConfiguracion, administrador);
            System.out.println("La contrasenia del administrador se ha configurado correctamente.");
        }
    }

    private static String cargarContraseñaAdmin(File archivoConfiguracion) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoConfiguracion))) {
            return lector.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void guardarContraseñaAdmin(File archivoConfiguracion, String contraseña) {
        try (PrintWriter escritor = new PrintWriter(archivoConfiguracion)) {
            escritor.println(contraseña);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     //MENU GENERAL
        private static void MenuGeneral() {
        Scanner scan=new Scanner(System.in);
        
        System.out.println("****BIENVENIDO  AL SISTEMA DE VOTACIONES****");
        System.out.println("___________________________________________");
        

        int opcion=0;
        
        System.out.println("1. Registro de usuarios  ");
        System.out.println("2. Administracion de elecciones");
        System.out.println("3. Acceso de votante al sistema");
        System.out.println("4. Conteo de votos");
        System.out.println("5. Registro de votantes");
        System.out.println("");
        System.out.print("Seleccione operacion a utilizar:");
        opcion=scan.nextInt();
        
        switch (opcion) {
            case 1: menuRegistroUsuarios();
                break;
            case 2: adminElecciones();
                break;
                
            case 3: accesoVotante();
            break;
            
            case 4:conteoVotos();
            break;
            
            case 5: registroVotante();
            break;
                
               
            default:System.out.println("Ingrese una opcion valida");
                
        }
      
        }
   
    
    
    // REGISTRO DE USUARIOS
    static void menuRegistroUsuarios(){
         int opcion;
        
         
         Scanner scan=new Scanner(System.in);
          System.out.println("****BIENVENIDO AL REGISTRO DE USUARIO****");
                System.out.println("_____________________________________");
                System.out.println("1. Crear usuario del sistema");
                System.out.println("2. Modificar usuario");
                System.out.println("3. Reiniciar contrasenia");
                System.out.println("ingrese la opcion: ");
                opcion=scan.nextInt();     
           switch (opcion) { 
           case 1: 
               crearUsuario();
        
        
           break;
           case 2: 
               modificarUsuario();
               
           break;
           
           case 3: System.out.println("****REINICIAR CONTRASEÑA****");
           break;
           default:
                throw new AssertionError();
        }
           
          
         //METODO PARA CREAR USUARIOS
        }
   
    static void crearUsuario(){System.out.println("***CREAR USUARIO***");
               Scanner scan=new Scanner(System.in);
               File f=new File("usuarios.txt"); 
               System.out.println("Ingrese el correo electronico");
               String correo=scan.next();
                       
                System.out.println("Ingrese su nombre: ");
                String nombre= scan.next();

                System.out.println("Ingrese su apellido: ");
                String apellido= scan.next();
                System.out.println("**ROLES***");
                System.out.println("1. Administrador");
                System.out.println("2. Registrador de votante");
                System.out.println("3. Votante ");
                System.out.println("4. Auditor");
                String rol=scan.next();
             // Cerrar el objeto Scanner
               scan.close();

             // Crear una cadena que contenga la informacion de los usuarios
              String nombreCompleto = correo+"|"+nombre + " |" + apellido+"|"+rol;

        try {
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Escribir los datos del usuario  en el archivo
            bw.write(nombreCompleto+"\n");
            
            // Cerrar el archivo
            bw.close();

            System.out.println("Los datos del usuario se han guardado en el archivo.");
            } catch (IOException e) {
            System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
           }
    }
    
    
    
    //METODO PARA MODIFICAR USUARIOS
         static void modificarUsuario(){
             Scanner scan=new Scanner(System.in);
        
                    File f=new File("candidatos.txt");
                    try {
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);
                        
                        File fc = new File("candidatos_copia.txt");
                        FileWriter fw = new FileWriter(fc);
                        BufferedWriter bw = new BufferedWriter(fw);
                        
                        String linea= "";
                        System.out.println("correo del usuario a modificar ");
                        String m=scan.next();
                      
                        while((linea = br.readLine()) != null) {
                           
                        String [] info = linea.split("\\|");
                            
                         if (info[0].compareTo(m) == 0) {
                            System.out.println("***INGRESE NUEVOS DATOS***");
                            System.out.println();
                            System.out.println("Ingrese el correo electronico");
                            String correo=scan.next();
                       
                            System.out.println("Ingrese su nombre: ");
                            String nombre= scan.next();

                            System.out.println("Ingrese su apellido: ");
                            String apellido= scan.next();
                            System.out.println("****ROLES***");
                            System.out.println("1. Administrador");
                            System.out.println("2. Registrador de votante");
                            System.out.println("3. Votante ");
                            System.out.println("4. Auditor");
                            String rol=scan.next();
                            
                                String datos=correo+"|"+nombre+"|"+apellido+"|"+rol;
                                linea = datos;
                                  }
                            
                                 bw.write(linea+"\n");
                                 } 
                        
                                 bw.close();
                                 br.close();
                        
                    Files.move(fc.toPath(), f.toPath(), REPLACE_EXISTING);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                    Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } 
             
         }
    
    
     //ADMINISTRACION DE ELECCIONES
     static void adminElecciones(){
         int opcion;
         Scanner scan=new Scanner(System.in);
         System.out.println("1. Gestion de elecciones");
         System.out.println("2. Gestion de candidatos");
         System.out.println("3. configuracion de gestion de elecciones");
         System.out.println("");
         System.out.println("Seleccione una opcion: ");
         opcion=scan.nextInt();
         
         
         
         switch (opcion) {
             case 1:
                    
                 
                 break;
             case 2:
                    System.out.println("1. agregar candidato");
                    System.out.println("2. modificar candidato");
                   int c=scan.nextInt();
                   if (c==1) {        
                   AgregarCandidato();
                     }
                        if (c==2) {       
                   ModificarCandidato(); 
                 }
             break;
                 
             default:
                 throw new AssertionError();
         }
         
         
         
    }
     
     static void AgregarCandidato(){
         Scanner scan=new Scanner(System.in);
         
         System.out.println("Ingrese el codigo del candidato");
         String codigo=scan.nextLine();
         System.out.println("Nombre completo del candidato: ");
         String nombre=scan.next();
         System.out.println("Ingrese la Formacion academica del candidato: ");
         String formacion=scan.next();
         System.out.println("Ingrese la experiencia profesional del candidato");
         String experiencia=scan.next();
         
         String datos=codigo+"|"+nombre+"|"+formacion+"|"+experiencia;
         File f=new File("candidatos.txt");
         
          try {
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Escribir los datos en el archivo
            bw.write(datos+"\n");
            
            // Cerrar el archivo
            bw.close();

            System.out.println("Los los datos del candidato se han guardado en el archivo correctamente.");
            } catch (IOException  e) {
          
        }
     }
     
     
     
     
         
     static  void ModificarCandidato(){
        Scanner scan=new Scanner(System.in);
        
        File f=new File("candidatos.txt");
        try {
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);
                        
                        File fc = new File("candidatos_copia.txt");
                        FileWriter fw = new FileWriter(fc);
                        BufferedWriter bw = new BufferedWriter(fw);
                        
                        String linea= "";
                        System.out.println("codigo de candidato a modificar ");
                        String m=scan.next();
                      
                         while((linea = br.readLine()) != null) {
                           
                         String [] info = linea.split("\\|");
                            
                         if (info[0].compareTo(m) == 0) {
                                System.out.println("***INGRESE NUEVOS DATOS***");
                                System.out.println();
                                System.out.println("Codigo de candidato");
                                String codigo=scan.next();
                                System.out.println("Nombre completo del candidato: ");
                                String nombre=scan.next();
                                System.out.println("Ingrese la Formacion academica del candidato: ");
                                String formacion=scan.next();
                                System.out.println("Ingrese la experiencia profesional del candidato");
                                String experiencia=scan.next();
                            
                                String datos=codigo+"|"+nombre+"|"+formacion+"|"+experiencia;
                                linea = datos;
                                  }
                            
                                 bw.write(linea+"\n");
                                 } 
                        
                                 bw.close();
                                 br.close();
                        
                    Files.move(fc.toPath(), f.toPath(), REPLACE_EXISTING);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                    Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } 
    }
    
    
     
     //REGISTRO DE VOTANTE
     static void registroVotante(){
         Scanner scan=new Scanner (System.in);
         System.out.println("1. Agregar votante");
         System.out.println("2. Desabilitar votante");
         System.out.println("3. Modificar votante");
         System.out.println("4. Reiniciar contrasenia");
         System.out.println("5. Regresar a Menu general:");
         System.out.println("Seleccione una opcion");
         int  opcion=scan.nextInt();
         switch (opcion) {
             case 1:
                 agregarVotante();
                 
                 break;
             case 2:
                 break;
             case 3:
                 ModificarVotante();
                 break;
             case 4:
                 break;
             case 5:
                MenuGeneral();
                 break;
             default:
                 throw new AssertionError();
         }
         
     }
     
     static void agregarVotante(){
        
         
         Scanner scan=new Scanner(System.in);
         System.out.print("ingrese correo: ");
         String correo=scan.next();
         System.out.print("Ingrese el CUI:  ");
         String dpi=scan.next();
         System.out.print("Ingrese el nombre completo : ");
         String nombre=scan.next();
         System.out.print("Ingrese los apellidos : ");
         String apellido=scan.next();
         System.out.print("Ingrese el sexo: ");
         String sexo=scan.next();
         System.out.print("ingrese fecha de nacimiento:");
         String fecha=scan.next();
         System.out.print("Direccion de residencia: ");
         String dir=scan.next();
         System.out.print("Municipio de residencia:  ");
         String municipio=scan.next();
         System.out.print("Departamento de residencia: ");
         String departamento=scan.next();
         System.out.print("Pais de residencia: ");
         String pais=scan.next();
         String pas="votante123456789";
         String datos=correo+"|"+pas+"|"+dpi+"|"+nombre+"|"+apellido+"|"+sexo+"|"+fecha+"|"+dir+"|"+municipio+"|"+departamento+"|"+pais;
         File f=new File("votantes.txt");
         
         
         
          try {
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Escribir los datos en el archivo
            bw.write(datos+"\n");
            
            // Cerrar el archivo
            bw.close();

            System.out.println("Los los datos del votante se han guardado en el archivo correctamente, su contrasenia es :"+pas);
            } catch (IOException  e) {
          
        }
     }
     
     
    
     
    static void desabilitarVotante(){
    
}
    
    static void ModificarVotante(){
 Scanner scan=new Scanner(System.in);
        
        File f=new File("candidatos.txt");
        try {
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);
                        
                        File fc = new File("candidatos_copia.txt");
                        FileWriter fw = new FileWriter(fc);
                        BufferedWriter bw = new BufferedWriter(fw);
                        
                        String linea= "";
                        System.out.println("codigo de candidato a modificar ");
                        String m=scan.next();
                      
                         while((linea = br.readLine()) != null) {
                           
                         String [] info = linea.split("\\|");
                            
                         if (info[0].compareTo(m) == 0) {
                                System.out.println("***INGRESE NUEVOS DATOS***");
                                System.out.println();
                                System.out.println("ingrese correo: ");
                                String correo=scan.next();
                                System.out.println("Ingrese el CUI:  ");
                                String dpi=scan.next();
                                System.out.println("Ingrese el nombre completo : ");
                                String nombre=scan.next();
                                System.out.println("Ingrese los apellidos: ");
                                String apellido=scan.next();
                                System.out.println("Ingrese el sexo: ");
                                String sexo=scan.next();
                                System.out.println("ingrese fecha de nacimiento:");
                                String fecha=scan.next();
                                System.out.println("Direccion de residencia: ");
                                String dir=scan.next();
                                System.out.println("Municipio de residencia: ");
                                String municipio=scan.next();
                                System.out.print("Departamento de residencia: ");
                                String departamento=scan.next();
                                System.out.println("Pais de residencia: ");
                                String pais=scan.next();
                                String password="votante123456789";
                            
                                String datos=correo+"|"+password+"|"+dpi+"|"+nombre+"|"+apellido+"|"+sexo+"|"+fecha+"|"+dir+"|"+municipio+"|"+departamento+"|"+pais;
                                linea = datos;
                                  }
                            
                                 bw.write(linea+"\n");
                                 } 
                        
                                 bw.close();
                                 br.close();
                        
                    Files.move(fc.toPath(), f.toPath(), REPLACE_EXISTING);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                    Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } 
 }
    static void reiniciarContraseñaVotante(){
        
    }
    
    
    
    
     //ACCESO DE VOTANTE AL SISTEMA
     static void accesoVotante(){
         Scanner scan=new Scanner(System.in);
      
         
         /* boolean accesoValido=false;
         
         System.out.println("ingrese su correo: ");
         String correo=scan.next(); 
         
         System.out.println("ingrese contrasenia:");
         String Contrasenia=scan.next();
         
         if (correo.compareTo("correo@correo.com")==0) {
             if (Contrasenia.compareTo("123")==0) {
                 System.out.println("***Bienvenido al sistema***");
                 
                 accesoValido=true;            
                 
         }  else{
                 System.err.println("contraseña incorrecta");
                accesoValido=false; 
             }
         }
           else{
             System.err.println("correo incorrecto");
             accesoValido=false;
                     }  */
        System.out.print("Ingrese su correo: ");
        String correo = scan.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scan.nextLine();

        // comparar datos registrados en archivo de texto
        if (verificarDatosVotante(correo, contraseña)) {
            System.out.println("**EMISION DE VOTOS");
            interfazVotacion();
            
        } else {
            System.out.println("No se pudo iniciar sesion. Verifique su correo y contrasenia.");
        }

        // Cerrar el scanner
        scan.close();
    }

    // Función para verificar las credenciales en el archivo de usuarios
    public static boolean verificarDatosVotante(String correo, String contraseña) {
        try (BufferedReader br = new BufferedReader(new FileReader("votantes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 11) {
                    String correoGuardado = partes[0];
                    String contraseñaGuardada = partes[1];

                    if (correo.equals(correoGuardado) && contraseña.equals(contraseñaGuardada)) {
                        return true; // datos validos
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // datos no validos
    }

     
     
    static void interfazVotacion(){
        Scanner scan=new Scanner(System.in);
        System.out.println("1. ELECCION A");
        System.out.println("2. ELECCION B");
        System.out.println("3. ELECCION C");
        System.out.println("");
        System.out.println("Seleccione opcion: ");
        int opcion=scan.nextInt();
        switch (opcion) {
            case 1:
                    System.out.println("1. CANDIDATO A");
                    System.out.println("2. CANDIDATO B");
                    System.out.println("3. CANDIDATO C");
                    System.out.println("Seleccione una opcion: ");
                    int n=scan.nextInt();
                    switch (n)  {
                        
                    case 1:
                        System.out.println("Has votado por candidato A");
                        
                        
                        break;
                    case 2:
                        System.out.println("Has votado por candidato B");
                        break;
                    case 3:
                        System.out.println("Has votado por candidato C");
                        break;
                    default:
                        System.out.println("Seleccione una opcion valida");;
                }
                break;
            case 2:
                    System.out.println("1. CANDIDATO A");
                    System.out.println("2. CANDIDATO B");
                    System.out.println("3. CANDIDATO C");
                
                
                break;
            case 3:
                    System.out.println("1. CANDIDATO A");
                    System.out.println("2. CANDIDATO B");
                    System.out.println("3. CANDIDATO C");
                break;
            default:
                System.out.println("seleccione una opcion valida");
        }
    }
       

    
     static void conteoVotos(){
         
         System.out.println("1. Conteo general de votos");
         System.out.println("2. Votos emitidos por sexo");
         System.out.println("3. Votos por ubicacion geográfica");
         System.out.println("4. Reporte maestro");
         System.out.println(" "); 
         System.out.println("Seleccione una opcion: ");     
  
     }
     
     
     
     
     public void votos(){
         
     }
}




