import java.util.Date;

public class Movie {
    private Date fecha;
    private String nombre;
    private double precioBoleto;
    private String[][] asientos;

    public Movie() {}

    public Movie(Date fecha, String nombre, double precioBoleto) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.precioBoleto = precioBoleto;
        asientos = new String[10][10];
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBoleto() {
        return precioBoleto;
    }

    public void setPrecioBoleto(double precioBoleto) {
        this.precioBoleto = precioBoleto;
    }

    public String[][] getAsientos() {
        return asientos;
    }

    public void setAsientos(String[][] asientos) {
        this.asientos = asientos;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "fecha=" + fecha +
                ", nombre='" + nombre + '\'' +
                ", precioBoleto=" + precioBoleto +
                '}';
    }
}
