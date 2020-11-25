package httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.net.httpserver.HttpsConfigurator;
import httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//singleton shared trough all the project
public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {
    }

    //we create a config manager daca nu exista unul
    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }


     // folosim pentru a incarca configuration file pe care vrem sa l folosim
    //dam file-ul ca si parametru

    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
          throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            //cat timp citim din fisierul de config il punem in buffer
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        }catch(IOException e)
        {
            throw new HttpConfigurationException(e);
        }
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the Configuration File",e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf,Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Configuration file internal",e);
        }
    }


     //  functie cu care dam  retrieve la configuration file care este incarcat
    public Configuration getCurrentConfiguration(){
        if(myCurrentConfiguration ==null)
        {
            throw new HttpConfigurationException("No Curent Configuration Set.");
        }
        return myCurrentConfiguration;
    }
}
