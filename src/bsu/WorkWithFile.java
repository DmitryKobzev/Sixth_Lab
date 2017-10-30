package bsu;

import java.io.*;


public class WorkWithFile {
    public static void write(String fileName, String text){
        File file = new File(fileName);
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file.getAbsoluteFile());
            try {
                pw.write(text);
            }
            finally {
                pw.close();
            }
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }
    public static String read(String fileName) {
        File file = new File(fileName);
        StringBuilder text = new StringBuilder();
        try{
            if(file.canRead()) {
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                try {
                    String s;
                    while ((s = br.readLine()) != null) {
                        text.append(s);
                        text.append("\n");
                    }
                } finally {
                    br.close();
                }
            }
            else{
                throw new Exception("File can not be read");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return text.toString();
    }
    public static void exists(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        if(!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }
    public static void deleteFile(String FileName) throws FileNotFoundException{
        exists(FileName);
        new File(FileName).delete();
    }
}
