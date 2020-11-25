package httpserver.config;


//Retine datele citite din fisierul de configuratie: http.json
// si ofera metode de accesare a lor
public class Configuration {

    private int port;
    private String webroot;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebroot() {
        return webroot;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }
}
