package util;

import model.Company.Company;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class ConvertUtil {
    public static String company2string(Company company) {
        try {
            ByteArrayOutputStream bass = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bass);
            oos.writeObject(company);
            oos.close();
            return Base64.getEncoder().encodeToString(bass.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
            return null;
        }
    }

    public static Company string2company(String string) {
        try {
            byte[] data = Base64.getDecoder().decode(string);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            return (Company) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
            return null;
        }
    }

}
