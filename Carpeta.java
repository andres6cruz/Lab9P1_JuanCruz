import java.util.ArrayList;

public class Carpeta {
    private String nombre;
    private String path;
    private ArrayList<Object> archivos;

    public Carpeta() {
        archivos = new ArrayList<>();
    }

    public Carpeta(String nombre, String path) {
        this.nombre = nombre;
        this.path = path;
        archivos = new ArrayList<>();
    }

    public ArrayList<Object> getArchivos() {
        return archivos;
    }

    public void setArchivos(ArrayList<Object> archivos) {
        this.archivos = archivos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Carpeta{" +
                "nombre='" + nombre + '\'' +
                ", path='" + path + '\'' +
                ", archivos=" + archivos +
                '}';
    }
}
