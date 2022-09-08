package jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Esra0313");
        Statement st = con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        // 1.Adim : prepared statement query olusturalim
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        // 2.Adim : PreparedStatement objesini olustur.
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3. Adım: set...() methodları ile soru işaretleri için değer gir.
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

        //4. Adım: Execute query
        int updateRowSayisi = pst1.executeUpdate();
        System.out.println(updateRowSayisi+" satır güncellendi.");

        String sql2 = "SELECT * FROM companies";
        ResultSet result1 =  st.executeQuery(sql2);

        while (result1.next()){

            System.out.println(result1.getInt(1)+"--"+result1.getString(2)+"--"+result1.getInt(3));
        }





        //    G O O G L E   I C I N   D E G I S I K L I K

        pst1.setInt(1,15000);
        pst1.setString(2,"GOOGLE");


        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println(updateRowSayisi2+" satır güncellendi.");

        String sql3 = "SELECT * FROM companies";
        ResultSet result2 =  st.executeQuery(sql3);

        while (result2.next()){

            System.out.println(result2.getInt(1)+"--"+result2.getString(2)+"--"+result2.getInt(3));
        }


        //2. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.

        System.out.println("===================");
        read_data(con,"companies");

    }

    //Bir tablonun istenilen datasını prepared statement ile çağırmak için kullanılan method.
    public static void read_data(Connection con, String tableName ){

        try {

            String query = String.format("SELECT * FROM %s",tableName);//Format() methodu dinamik String oluşturmak için kullanılır.

            Statement statement = con.createStatement();
            //SQL query'yi çalıştır.
            ResultSet rs = statement.executeQuery(query);//Datayı çağırıp ResultSet konteynırına koyuyoruz.

            while (rs.next()){//Tüm datayı çağıralım.

                System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getInt(3));

            }


        }catch (Exception e){
            System.out.println(e);
        }

    }




}


