package ifpr.pgua.eic.tads.comunicacao.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    private ServerSocket servidor;
    private Socket cliente;
    private String HOST;
    private int PORTA;
    private BufferedReader entrada;
    private BufferedWriter saida;

    public ServidorTCP(String HOST,int PORTA)throws IOException {
        servidor = new ServerSocket(PORTA);
    }

    public void escuta() throws IOException {
        System.out.println("Aguardando conexão...");
        cliente = servidor.accept();
        System.out.println("Conectado..."+cliente.getInetAddress()+":"+cliente.getPort());
        //abreFluxos();
    }

    private void abreFluxos() throws IOException{
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        saida = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
    }

    public void processa() throws IOException{
        Thread t = new Thread(new Worker(cliente));
        t.start();
    }

    public void fecha() throws IOException{
        entrada.close();
        saida.close();
        cliente.close();
    }
}