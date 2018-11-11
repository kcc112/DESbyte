import java.nio.charset.Charset;
import java.lang.String;
import java.nio.charset.StandardCharsets;
import java.io.IOException;



public class Main {

    public static void main(String[] args) throws IOException{
        DES des = new DES();
        ReadWrite file = new ReadWrite();

        String key = "12";
        String mesage = "12345678ąą";
        byte[] k = key.getBytes(Charset.forName("UTF-8"));
        byte[] m = mesage.getBytes(Charset.forName("UTF-8"));

        byte[] cipher = des.encrypt(m,k);

        //Zamiana na hex
        StringBuilder sb = new StringBuilder();
        for (byte b : cipher) {
            sb.append(String.format("%02X ", b));
        }
        System.out.println("Zaszyfrowana hex " + sb.toString());

        byte[] output = des.decrypt(cipher,k);
        String str = new String(output, StandardCharsets.UTF_8);
        System.out.println("Odszyfrowana" + str);

        byte[] array = file.read("D:/Projekty/DES/DESbin/x.jpg");
        cipher = des.encrypt(array,k);
        file.write("D:/Projekty/DES/DESbin/z.bin",cipher);
        output = des.decrypt(cipher,k);
        file.write("D:/Projekty/DES/DESbin/y.jpg",output);

    }
}
