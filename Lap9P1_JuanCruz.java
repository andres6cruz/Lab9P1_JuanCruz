import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Lap9P1_JuanCruz {

    private static ArrayList<User> usuarios = new ArrayList<>();
    private static ArrayList<Movie> movies;

    private static Carpeta HOME;

    private static final Scanner str = new Scanner(System.in);
    private static final Scanner num = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
        HOME = new Carpeta("HOME", "HOME/");
    }

    public static void crearMovies() {
        boolean continuar = false;
        do {
            System.out.println("Ingrese el nombre de la película: ");
            String nombre = str.nextLine();
            System.out.println("Ingrese el precio de la película: ");
            double precio = num.nextDouble();

            System.out.println("Ingrese el día de la película: ");
            int día = num.nextInt();
            System.out.println("Ingrese el número del mes de la película: ");
            int mes = num.nextInt() + 1;
            System.out.println("Ingrese el año de la película: ");
            int año = num.nextInt() + 1900;

            Date fecha = new Date(año, mes, día);
            Movie movie = new Movie(fecha, nombre, precio);
            llenarAsientos(movie.getAsientos());
            movies.add(movie);

            System.out.println("¿Desea ingresar otra película?(1.Sí 0.No)");
            int opción = num.nextInt();
            continuar = opción == 1;
        }while(continuar);
    }

    private static void llenarAsientos(String[][] asientos) {
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[0].length; j++) {
                asientos[i][j] ="[ " + " ]";
            }
        }
    }

    public static void menu() {
        int opción;
        boolean continuar = true;
        do {
            System.out.println("----------MENÚ----------");
            System.out.println("""
                    (1)Gestión de reservas de cine
                    (2)Gestión de archivos
                    (3)Salir""");
            opción = num.nextInt();
            switch (opción) {
                case 1 -> menuCine();
                case 2 -> menuArchivos();
                case 3 -> continuar = false;
                default -> System.out.println("Número inválido.");
            }
        }while(continuar);
        System.out.println("Saliendo del menú...");
    }

    public static void menuArchivos() {
        boolean continuar = true;
        int opción;
        do {
            System.out.println("---------Archivos---------");
            System.out.println("""
                    (1)Crear carpeta
                    (2)Crear archivo
                    (3)Eliminar archivo
                    (4)Copiar Archivo
                    (5)Cortar Archivo
                    (6)Salir""");
            opción = num.nextInt();
            switch (opción) {
                case 1 -> {
                    crearCarpeta(HOME.getPath());
                }
                case 2 -> {
                    crearArchivo(HOME.getPath());
                }
                case 3 -> {
                    eliminarArchivo(HOME.getPath());
                }
                case 4 -> {
                    copiarArchivo(new Carpeta(), HOME.getPath());
                }
                case 5 -> {
                    cortarArchivo(new Carpeta(), HOME.getPath(), new Carpeta());
                }
                case 6 -> {
                    continuar = false;
                    break;
                }
            }
        }while(continuar);
    }

    private static ArrayList<Carpeta> obtenerCarpetas(ArrayList<Object> HOME) {
        ArrayList<Carpeta> carpetas = new ArrayList<>();

        for (int i = 0; i < HOME.size(); i++) {
            if(HOME.get(i) instanceof Carpeta c) {
                carpetas.add(c);
            }
        }
        return carpetas;
    }

    public static ArrayList<Archivo> obtenerArchivos(ArrayList<Object> HOME) {
        ArrayList<Archivo> archivos = new ArrayList<>();

        for (int i = 0; i < HOME.size(); i++) {
            if(HOME.get(i) instanceof  Archivo a) {
                archivos.add(a);
            }
        }
        return archivos;
    }

    private static void listarListaArchivos(ArrayList<Archivo> archivos) {
        System.out.println("ARCHIVOS");
        for (int i = 0; i < archivos.size(); i++) {
            int posición = i + 1;
            System.out.println(posición + "->" + archivos.get(i));
        }
    }

    private static void listarListaCarpetas(ArrayList<Carpeta> carpetas) {
        System.out.println("CARPETAS");
        for (int i = 0; i < carpetas.size(); i++) {
            int posición = i + 1;
            System.out.println(posición + "->" + carpetas.get(i));
        }
    }

    private static Carpeta crearCarpeta(String path) {
        System.out.println("Ingrese el nombre de la carpeta: ");
        String nombre = str.nextLine();
        path = path + "/" + nombre;
        return new Carpeta(nombre, path);
    }

    private static Archivo crearArchivo(String path) {
        System.out.println("Ingrese el nombre del archivo: ");
        String nombre = str.nextLine();
        path = path + "/" + nombre;
        return new Archivo(nombre, path);
    }

    private static void eliminarArchivo(String path) {
        ArrayList<Object> home = HOME.getArchivos();
        Object remove = null;

        for (int i = 0; i < home.size(); i++) {
            if(home.get(i) instanceof Archivo a) {
                if(a.getPath().equals(path)) {
                    remove = home.get(i);
                }
            }
        }
        if(remove != null)
            home.remove(remove);
    }

    private static void copiarArchivo(Carpeta carpeta, String pathArchivo) {
        ArrayList<Object> archivos = carpeta.getArchivos();
        Archivo archivo = null;

        for (int i = 0; i < archivos.size(); i++) {
            if (archivos.get(i) instanceof Archivo a) {
                if (a.getPath().equals(pathArchivo))
                    archivo = a;
            }
        }

        if(archivo != null) {
            String nuevoPath = archivo.getPath() + "/" + archivo.getNombre() + "(2)";
            Archivo archivoCopiado = new Archivo(
                    archivo.getNombre() + "(2)",
                    nuevoPath
            );
            carpeta.getArchivos().add(archivoCopiado);
        }
    }

    private static void cortarArchivo(Carpeta carpeta,
                                      String pathArchivo,
                                      Carpeta carpetaDestino) {
        ArrayList<Object> archivos = carpeta.getArchivos();
        Archivo archivo = null;

        for (int i = 0; i < archivos.size(); i++) {
            if (archivos.get(i) instanceof Archivo a) {
                if (a.getPath().equals(pathArchivo))
                    archivo = a;
            }
        }

        if(archivo != null) {
            String pathNuevo = carpetaDestino.getPath() + "/" + archivo.getNombre();
            carpeta.getArchivos().remove(archivo);
            archivo.setPath(pathNuevo);
            carpetaDestino.getArchivos().add(archivo);
        }
    }

    public static void menuCine() {
        crearMovies();
        boolean continuar = true;
        int opción;
        User usuario = null;
        do {
            System.out.println("---------CINE---------");
            System.out.println("""
                    (1)SignUp
                    (2)Login
                    (3)Reservar entrada
                    (4)Salir""");
            opción = num.nextInt();
            switch (opción) {
                case 1 -> signUp();
                case 2 -> {
                    usuario = null;
                    if(login() != null) {
                        usuario = login();
                    }else {
                        System.out.println("¡Usuario no existe!");
                    }
                }
                case 3 -> {
                    if(usuario != null) {
                        reservarEntrada(usuario);
                    }else {
                        System.out.println("¡Debe iniciar sesión!");
                    }
                }
                case 4 -> {
                    continuar = false;
                    break;
                }
                default -> System.out.println("Número inválido.");
            }
        }while(continuar);
    }

    private static void reservarEntrada(User user) {
        listarMovies();
        int opción = num.nextInt();
        Movie movie = movies.get(opción - 1);
        asignarAsientos(movie);
    }

    private static void asignarAsientos(Movie movie) {
        mostrarAsientos(movie);
        boolean continuar = false;
        int fila;
        int columna;
        do {
            System.out.println("Ingrese el número de fila del asiento a comprar: ");
            fila = num.nextInt() - 1;
            System.out.println("Ingrese el número de columna del asiento a comprar: ");
            columna = num.nextInt() - 1;
            movie.getAsientos()[fila][columna] = "[X]";
            System.out.println("¿Desea comprar otra entrada?(1.Sí 0.No");
            int opción = num.nextInt();
            continuar = opción == 1;
        }while(continuar);
        System.out.println("¡Asientos comprados exitosamente!");
        mostrarAsientos(movie);
    }

    private static void mostrarAsientos(Movie movie) {
        String[][] asientos = movie.getAsientos();
        System.out.println("Los asientos marcados con 'X' están ocupados.");
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[0].length; j++) {
                System.out.println(asientos[i][j]);
            }
            System.out.println();
        }
    }

    private static void listarMovies() {
        for (int i = 0; i < movies.size(); i++) {
            int posición = i + 1;
            System.out.println(posición + "-> " + movies.get(i).toString());
        }
    }

    private static void signUp() {
        System.out.println("Ingrese su correo: ");
        String email = str.next();
        if(!existeCorreo(email)) {
            System.out.println("Ingrese su contraseña: ");
            String password = str.next();
            User user = new User(email, password);
            usuarios.add(user);
        }else {
            System.out.println("Usuario inválido");
        }
    }

    private static User login() {
        System.out.println("Ingrese su correo: ");
        String email = str.next();
        System.out.println("Ingrese su contraseña: ");
        String contraseña = str.next();
        return(existeUsuario(email, contraseña));
    }

    private static boolean existeCorreo(String email) {
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private static User existeUsuario(String email, String password) {
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getEmail().equals(email) &&
                    usuarios.get(i).getPassword().equals(password)) {
                return usuarios.get(i);
            }
        }
        return null;
    }
}