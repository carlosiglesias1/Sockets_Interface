package sockets;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Servidor {
    public void execute() {
        Thread hiloServidor = new Thread(new MarcoServidor());
        hiloServidor.start();
    }
}

class MarcoServidor extends JFrame implements Runnable {
    private JTextArea textArea;

    public MarcoServidor() {
        setBounds(1200, 300, 400, 500);
        JPanel miPanel = new JPanel();
        miPanel.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        miPanel.add(textArea, BorderLayout.CENTER);
        super.add(miPanel);
        super.setVisible(true);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(9998)) {
            String mensaje = "";
            do {
                System.out.println("Estoy a la escucha");
                Socket cliente = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(cliente.getInputStream());
                mensaje = dataInputStream.readUTF();
                textArea.append("\n" + mensaje);
                System.out.println(textArea.getText());
                cliente.close();
            } while (!mensaje.equalsIgnoreCase("cerrar"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
