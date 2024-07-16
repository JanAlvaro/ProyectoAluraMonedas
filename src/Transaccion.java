import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaccion {

    private double cantidadAntesCambio;
    private double cantidadDespuesCambio;
    private String monedaAntesCambio;
    private String monedaDespuesCambio;



    public Transaccion(double cantidadAntesCambio, double cantidadDespuesCambio, String monedaAntesCambio, String monedaDespuesCambio ) {
        this.cantidadAntesCambio = cantidadAntesCambio;
        this.cantidadDespuesCambio = cantidadDespuesCambio;
        this.monedaAntesCambio = monedaAntesCambio;
        this.monedaDespuesCambio = monedaDespuesCambio;


    }

    public Transaccion(Conversion transaccion, double cantidad ) {

        this.cantidadAntesCambio = cantidad;
        this.monedaDespuesCambio = transaccion.target_code();
        this.cantidadDespuesCambio = Double.valueOf(transaccion.conversion_result());
        this.monedaAntesCambio = transaccion.base_code();



    }

    public double getCantidadAntesCambio() {
        return cantidadAntesCambio;
    }

    public double getCantidadDespuesCambio() {
        return cantidadDespuesCambio;
    }

    public String getMonedaAntesCambio() {
        return monedaAntesCambio;
    }

    public String getMonedaDespuesCambio() {
        return monedaDespuesCambio;
    }

    @Override
    public String toString() {
        return "ConversorMonedas{" +
                "cantidadAntesCambio=" + cantidadAntesCambio +
                ", cantidadDespuesCambio=" + cantidadDespuesCambio +
                ", monedaAntesCambio='" + monedaAntesCambio + '\'' +
                ", monedaDespuesCambio='" + monedaDespuesCambio + '\'' +
                '}';
    }
}
