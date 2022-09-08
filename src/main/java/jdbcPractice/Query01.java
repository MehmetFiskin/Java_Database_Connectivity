package jdbcPractice;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Query01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1)Driver Yukle
        Class.forName("org.postgresql.Driver");
        //2)Baglantı olustur
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Esra0313");
        // 3)Statement olustur.
        Statement st = con.createStatement();
        //4)ResultSet
        ResultSet veri = st.executeQuery("select * from ogrenciler");
        //sonuclari al
        while (veri.next()) {
            //index kullanarak
            //System.out.println(veri.getInt(1) + veri.getString(2) + veri.getString(3) + veri.getString(4));
            //sutun kullanarak
          //  System.out.println(veri.getInt("okul_no") + veri.getString("ogrenci_ismi") + veri.getString("sinif") + veri.getString("cinsiyet"));

            //prinitf ile
            System.out.printf("%-6d %-15.15s %-8s %-8s\n", veri.getInt(1), veri.getString(2), veri.getString(3), veri.getString(4));

        }
    }
}