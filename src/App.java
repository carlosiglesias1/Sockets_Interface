import sockets.Cliente;
import sockets.Servidor;

public class App {
    public static void main(String[] args){
        Servidor server = new Servidor();
        Cliente cliente = new Cliente();
        server.execute();
        cliente.execute();
    }
}
