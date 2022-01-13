package sockets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public void execute() {
        MarcoCliente miMarco = new MarcoCliente();
        miMarco.setVisible(true);
    }
}

class MarcoCliente extends JFrame {
    public MarcoCliente() {
        setBounds(600, 300, 280, 350);
        LaminaMarcoCliente miLamina = new LaminaMarcoCliente();
        add(miLamina);
        setVisible(true);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

class LaminaMarcoCliente extends JPanel {
    private JTextField campo1;
    private JButton myButton;

    public LaminaMarcoCliente() {
        JLabel texto = new JLabel("CLIENTE");
        super.add(texto);
        this.campo1 = new JTextField(20);
        super.add(campo1);
        this.myButton = new JButton("Enviar");
        myButton.addActionListener(new EnviarTexto());
        super.add(myButton);
    }

    private class EnviarTexto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try (Socket socket = new Socket("192.168.52.1", 9998)) {
                System.out.println("Se ha creado el socket en: " + socket.getInetAddress() + "/" + socket.getPort());
                DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
                mensaje.writeUTF(campo1.getText());
                mensaje.close();
            } catch (UnknownHostException e2) {
                System.out.println(e2.getMessage());
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }
}