package tomweb.xyz.bjcms.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;

@Component
public class StaticHtmlUtils {
    @Value("#{htmlRootPath}")
    String htmlRootPath;
    @Autowired
    RestTemplate restTemplate;
    String host = "localhost";

    @Value("${server.port}")
    int port = 8010;


    public String staticIndex() {
        String fileName = "index.html";
        String string = restTemplate.getForObject("http://" + host + ":" + port + "/" + fileName, String.class);
        File htmlRootPathFile = new File(htmlRootPath);
        if (!htmlRootPathFile.exists()) {
            htmlRootPathFile.mkdirs();
        }
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(new File(htmlRootPath + fileName));
            fileOutputStream.write(string.getBytes("utf-8"));
            fileOutputStream.close();
            return string;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized String  staticArticle(Integer id) {
        String string = restTemplate.getForObject("http://" + host + ":" + port + "/" + "a/"+id, String.class);
        File htmlRootPathFile = new File(htmlRootPath+"a");
        if (!htmlRootPathFile.exists()) {
            htmlRootPathFile.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(htmlRootPath+"a/" + id));
            fileOutputStream.write(string.getBytes("utf-8"));
            fileOutputStream.close();
            return string;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
