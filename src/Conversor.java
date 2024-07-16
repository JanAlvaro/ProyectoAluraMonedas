import com.google.gson.Gson;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.List;

public class Conversor {



    public static void main(String[] args) throws IOException, InterruptedException{


        List<Transaccion> listaTransacciones=new ArrayList<>();


        int Salir=1;
        while(Salir != 0) {

            String menu = """
                    ** Escriba el número de la opción deseada **                                   
                    1) Dólar   --->> Peso Argentino
                    2) Peso Argentino --->> Dólar
                    3) Dólar --->> Real brasileño
                    4) Real brasileño ---> Dólar
                    5) Dólar --->> Peso Colombiano
                    6) Peso Colombiano --->> Dólar
                    7) Dólar ---> Peso Mexicano
                    8) Peso Mexicano --->>  Dólar
                    9) Ver historial de conversiones y salir
                                        
                                    
                    Elija una de las opciones 
                    """;
            System.out.println(menu);
            Scanner lectura = new Scanner(System.in);
            var opcion = lectura.nextInt();


            String base = "";
            String target = "";
            if (opcion >= 1 && opcion < 9) {


                switch (opcion) {
                    case 1:
                        base = "USD";
                        target = "ARS";
                        break;
                    case 2:
                        base = "ARS";
                        target = "USD";
                        break;
                    case 3:
                        base = "USD";
                        target = "BRL";
                        break;
                    case 4:
                        base = "BRL";
                        target = "USD";
                        break;
                    case 5:
                        base = "USD";
                        target = "COP";
                        break;
                    case 6:
                        base = "COP";
                        target = "USD";
                        break;
                    case 7:
                        base = "USD";
                        target = "MXN";
                        break;
                    case 8:
                        base = "MXN";
                        target = "USD";
                        break;


                }


                System.out.println("ingrese la cantidad");

                Scanner leccanti = new Scanner(System.in);
                var cantidad = lectura.nextInt();
                String direccion = "https://v6.exchangerate-api.com/v6/cf919871dd2d9997f69c9e01/pair/" +
                        base + "/" + target + "/" + cantidad;
                //System.out.println(direccion);

                try {
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(direccion))
                            .build();
                    HttpResponse<String> response = client
                            .send(request, HttpResponse.BodyHandlers.ofString());

                    String json = response.body();
                    //System.out.println(json);

                    Gson gson = new Gson();
                    Transaccion trans = gson.fromJson(json, Transaccion.class);

                    Conversion transaccion = gson.fromJson(json, Conversion.class);

                    Transaccion miConversion = new Transaccion(transaccion, cantidad);
                    listaTransacciones.add(miConversion);
                    System.out.println("Conversion " + miConversion);

                /*System.out.println("Seguir ?");
                Salir = lectura.nextInt();*/

                } catch (NumberFormatException e) {
                    System.out.println("Ocurrió un error: ");
                    System.out.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error en la URI, verifique la dirección.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            if (opcion == 9) {

                System.out.println("Finalizó la ejecución del programa!");

                for (Transaccion listaTransaccione : listaTransacciones) {
                    System.out.println(listaTransaccione);
                }
                Salir=0;


            }
        }









    }


}