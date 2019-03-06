package java9;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Http2 {
//    public static void main(String[] args) throws Exception {
//        URL url = new URL("http://192.168.130.201:9000/app/config");
//        String param = "name=" + URLEncoder.encode("丁丁", "UTF-8");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setDoOutput(true);
//        conn.setDoInput(true);
//        conn.setUseCaches(false);
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Charset", "utf-8");
//        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
//        dos.writeBytes(param);
//        dos.flush();
//        dos.close();
//        if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
//            String data = new String(conn.getInputStream().readAllBytes());
//            conn.getInputStream().close();
//            System.out.println(data);
//        }
//    }
}
