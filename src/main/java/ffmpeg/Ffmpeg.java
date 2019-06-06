package ffmpeg;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Ffmpeg {
    static String root = "D:\\下载\\Java\\";
    static String mp3 = root + "mp3";
    static String doc = root + "doc";


    public static void main(String[] args) throws Exception {
        File file = new File(mp3);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(doc);
        if (!file.exists()) {
            file.mkdirs();
        }
        walk(Paths.get(root).toFile());
    }

    public static void walk(File file) throws Exception {
        if (null == file) {
            return;
        }
        if (file.isFile()) {
            if (file.getName().endsWith(".mp3")) {
            } else if (file.getName().endsWith(".avi")) {
                convert(file.getPath());
            } else {
                Files.copy(file.toPath(), Paths.get(doc, getName(file.getPath())), StandardCopyOption.REPLACE_EXISTING);
            }
            return;
        }
        if (file.isDirectory() && !file.getPath().equals(mp3) && !file.getPath().equals(doc)) {
            File[] files = file.listFiles();
            for (File f : files) {
                walk(f);
            }
        }
    }

    public static String getName(String path) {
        return path.substring(root.length()).replace("\\", "_");
    }


    public static void convert(String path) throws Exception {
        String outFile = Paths.get(mp3, getName(path) + ".mp3").toString();
        Process process = Runtime.getRuntime().exec("D:\\ProgramFiles\\ffmpeg-20190524-1d74150-win64-static\\bin\\ffmpeg.exe -i " + path + " -vn " + outFile);
//        ProcessBuilder pb = new ProcessBuilder("D:\\ProgramFiles\\ffmpeg-20190524-1d74150-win64-static\\bin\\ffmpeg.exe -i " + path + " -vn " + outFile);
//        pb.redirectErrorStream(true);
//        Process process = pb.start();
        if (process == null) {
            return;
        }

        // 处理InputStream的线程
        new Thread() {
            @Override
            public void run() {
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                try {
                    while ((line = in.readLine()) != null) {
                        System.out.println("output: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        // 处理ErrorStream的线程
        new Thread() {
            @Override
            public void run() {
                BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line = null;
                try {
                    while ((line = err.readLine()) != null) {
                        System.out.println("err: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        err.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        process.waitFor();
    }
}
