
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class Hotelreservation {

    private static final String url = "----";
    private static final String user = "---";
    private static final String pass = "----";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            while (true) {
                System.out.println();
                System.out.println("HOTEL RESERVATION SYSTEM");
                Scanner sc = new Scanner((System.in));
                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservation");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservation");
                System.out.println("5. Delete Reservation");
                System.out.println("0. Exit");
                System.out.println("Choose an option");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        reserveRoom(con, sc);
                        break;
                    case 2:
                        viewReservation(con);
                        break;
                    case 3:
                        getRoomNumber(con, sc);
                        break;
                    case 4:
                        updateReservation(con, sc);
                        break;
                    case 5:
                        deleteReservation(con, sc);
                        break;
                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid input , Pls try again");

                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    private static void reserveRoom(Connection con, Scanner sc) {
        try {
            System.out.println("Enter guest name:");
            String guestname = sc.next();
            sc.nextLine();
            System.out.println("Enter Room Number");
            int roomNumber = sc.nextInt();
            System.out.println("Enter Contact Number");
            String contactnumber = sc.next();

            String sql = "INSERT INTO reservation (guest_name, room_number, contact_number) " +
                    "VALUES ('" + guestname + "', " + roomNumber + ", '" + contactnumber + "')";

            try (Statement st = con.createStatement()) {
                int affectedrows = st.executeUpdate(sql);
                if (affectedrows > 0) {
                    System.out.println("Reservation Successful");
                } else {
                    System.out.println("Reservation Failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewReservation(Connection con) throws SQLException {
        String sql = " select reservation_id,guest_name,room_number,contact_number,reservation_date from reservation";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("Current Reservation");
            System.out.println("------------------------+-------------------------+----------------------------------------------------------------------");
            System.out.println("| Reservation ID  | Guest       | Room Number         | Contact Number         | reservation date");
            System.out.println("------------------------+-------------------------+----------------------------------------------------------------------");

            while (rs.next()) {
                int reservationid = rs.getInt("reservation_id");
                String guestname = rs.getString("guest_name");
                int roomnumber = rs.getInt("room_number");
                String contactnumber = rs.getString("contact_number");
                String reservationdate = rs.getTimestamp("reservation_date").toString();

                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s | \n",
                        reservationid, guestname, roomnumber, contactnumber, reservationdate);
            }
            System.out.println("------------------------+-------------------------+-----------------------------------------------------------------------");
        }

    }

    private static void getRoomNumber(Connection con, Scanner sc) throws SQLException {
        String sql;
        try {
            System.out.println("Enter reservation Id ");
            int reservationid = sc.nextInt();
            System.out.println("Enter guest name");
            String guestname = sc.next();

            sql = "select room_number from reservation " +
                    "where reservation_id =" + reservationid +
                    " AND guest_name= '" + guestname+ "'";

            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                if (rs.next()) {
                    int roomnumber = rs.getInt("room_number");
                    System.out.println("Room Number for Reservation id = "
                            + reservationid + " and guest "
                            + guestname + " is " + roomnumber);
                } else {
                    System.out.println("Reservation not found for the given ID and guest name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void updateReservation(Connection con,Scanner sc) {

        try {
System.out.println("Enter reservation id to update:");
int reservationid = sc.nextInt();
sc.nextLine();

if (!reservationexist(con,reservationid)){
    System.out.println("Reservation not found");
    return;
}
System.out.println("Enter new guest name");
String newguest = sc.next();
System.out.println("Enter new room number");
int newroom = sc.nextInt();
System.out.println("Enter new contact number");
String newcontact = sc.next();

            String sql = "UPDATE reservation SET " +
                    "guest_name = '" + newguest + "', " +
                    "room_number = " + newroom + ", " +
                    "contact_number = '" + newcontact + "' " +
                    "WHERE reservation_id = " + reservationid;
            try(Statement st = con.createStatement()){
                int affectrows= st.executeUpdate(sql);
                if (affectrows>0){
                    System.out.println("rESERVATION UPDATED SUCCESSFULLY");
                }
                else{
                    System.out.println("rESERVATION UPDATE FAILED");
                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
    }
    private static void deleteReservation(Connection con, Scanner sc){
        try{
            System.out.println("Enter reservation id to delete");
            int reservationid = sc.nextInt();

            if (!reservationexist(con,reservationid)){
                System.out.println("No reservation found for the following ID");
                return;

            }

            String sql = "delete from reservation where reservation_id= " +reservationid;
            try(Statement st = con.createStatement()){
                int affectedrows = st.executeUpdate(sql);
                if (affectedrows>0){
                    System.out.println("Reservation deleted successfully");
                }
                else{
                    System.out.println("Reservation deletetion failed");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static boolean reservationexist(Connection con, int reservationid){
        try {
            String sql = "select reservation_id from reservation where reservation_id =" + reservationid;
            try (Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){

                return rs.next();
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
            return false;
        }
    }
    public static void exit () throws InterruptedException{
        System.out.println("Exiting System");
        int i =5;
        while (i!=0){
            System.out.println(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("Exited Successfully");
    }
}


