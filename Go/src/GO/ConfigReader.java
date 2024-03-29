package GO;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URISyntaxException;

public class ConfigReader {
    private String ipAddress;
    private int port;

    public ConfigReader() throws IOException, ParseException, URISyntaxException {
        String url = gameAdmin.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        String newURL = url.replace("gameAdmin.jar", "go.config");
        System.out.println(newURL);
        String goConfigData = getConfigData(newURL);
        JSONParser parser = new JSONParser();

        JSONObject configData = (JSONObject) parser.parse(goConfigData);
        ipAddress = configData.get("IP").toString();
        port = ((Long) configData.get("port")).intValue();
    }

    private String getConfigData(String fileName) throws IOException {
        InputStream in = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String currentLine = reader.readLine();
        reader.close();
        return currentLine;
    }

    public int port() {
        return port;
    }

    public String ipAddress() {
        return ipAddress;
    }
}
