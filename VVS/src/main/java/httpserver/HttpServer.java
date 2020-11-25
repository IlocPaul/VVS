package httpserver;

import httpserver.config.Configuration;
import httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//driver class that starts the server running
//the server needs to be listening to a specific port; we will configure that port using a configuration  file si facem asta cu json
public class HttpServer {
    public static void main(String[] args)
    {
        System.out.println("Server starting...");

        //ii dam fisierul de unde sa ia configuratia si icarcam configuratia intr o variabila de tip configuration
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
         //dam retrieve la configuration
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using port: " + conf.getPort());
        System.out.println("Using WebRoot: " + conf.getWebroot());

        //
        try {
            ServerSocket serverSocket= new ServerSocket(conf.getPort()); //creez sochetrul serverului!
            Socket socket = serverSocket.accept(); //serverul asteapta sa se conecteze un client la el (prin browser) 

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

           String html = "<html><head><title>HTTP Server</title></head><body><h1>Hello there</h1></body></html>";

           final String CRLF = "\r\n";//13,10

            // Protocoulul de comunicare HTTP cere sa ai versiunea de HTTP, cod de raspuns si lungimea mesajului  (header)
           String response= "HTTP/1.1 200 OK " + CRLF +   // Status Line: HTTP Version response_code response_message
                           "Content Length: " + html.getBytes().length + CRLF + //Header
                   CRLF + html + CRLF + CRLF ;

           outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
