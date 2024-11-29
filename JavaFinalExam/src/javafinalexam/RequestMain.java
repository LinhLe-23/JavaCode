package javafinalexam;

import java.sql.Date;
import java.util.Scanner;
import javafinalexam.Request;
import javafinalexam.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class RequestMain {

    public static void main(String[] args) {
        JFRequest jfr = new JFRequest();
        jfr.show();

        Scanner scanner = new Scanner(System.in);
        RequestController controller = new RequestController();

        try {

            System.out.print("NHAP ReqID: ");
            int reqID = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            System.out.print("NHAP ReqName: ");
            String reqName = scanner.nextLine();

            System.out.print("NHAP ReqDate (yyyy-MM-dd): ");
            String reqDateStr = scanner.nextLine();
            Date reqDate = Date.valueOf(reqDateStr);

            System.out.print("NHAP ReqEmail: ");
            String reqEmail = scanner.nextLine();

            System.out.print("NHAP ReqType: ");
            String reqType = scanner.nextLine();

            System.out.print("NHAP ReqDetails: ");
            String reqDetails = scanner.nextLine();

            Request request = new Request(reqID, reqName, reqDate, reqEmail, reqType, reqDetails);

            boolean result = insertRequest(request);

            if (result) {
                System.out.println("THEM THANH CONG!");
            } else {
                System.out.println("THEM THAT BAI!");
            }

            displayAllRequests();

            System.out.print("NHAP ID CAN TIM: ");
            int searchID = scanner.nextInt();

            Request foundRequest = findRequest(searchID);

            if (foundRequest != null) {
                System.out.println("-------------THONG TIN------------");
                System.out.println("ReqID: " + foundRequest.getReqID());
                System.out.println("ReqName: " + foundRequest.getReqName());
                System.out.println("ReqDate: " + foundRequest.getReqDate());
                System.out.println("ReqEmail: " + foundRequest.getReqEmail());
                System.out.println("ReqType: " + foundRequest.getReqType());
                System.out.println("ReqDetails: " + foundRequest.getReqDetails());
            } else {
                System.out.println("KHONG TIM THAY = " + searchID);
            }

        } catch (IllegalArgumentException e) {
            System.err.println("SAI DINH DANG NGAY");
        } catch (Exception e) {
            System.err.println("LOI " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static boolean insertRequest(Request request) {
        String sql = "INSERT INTO ITRequest (ReqID, ReqName, ReqDate, ReqEmail, ReqType, ReqDetails) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection connect = DBConnection.getConnection(); PreparedStatement ps = connect != null ? connect.prepareStatement(sql) : null) {
            if (ps != null) {
                ps.setInt(1, request.getReqID());
                ps.setString(2, request.getReqName());
                ps.setDate(3, request.getReqDate());
                ps.setString(4, request.getReqEmail());
                ps.setString(5, request.getReqType());
                ps.setString(6, request.getReqDetails());

                int rowsInserted = ps.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static Request findRequest(int reqID) {
        String sql = "SELECT * FROM ITRequest WHERE ReqID = ?";
        Request req = null;

        try (
                Connection connect = DBConnection.getConnection(); PreparedStatement ps = connect != null ? connect.prepareStatement(sql) : null) {
            if (ps != null) {
                ps.setInt(1, reqID);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Tạo đối tượng Request từ kết quả truy vấn
                        int id = rs.getInt("ReqID");
                        String name = rs.getString("ReqName");
                        Date date = rs.getDate("ReqDate");
                        String email = rs.getString("ReqEmail");
                        String type = rs.getString("ReqType");
                        String details = rs.getString("ReqDetails");

                        req = new Request(id, name, date, email, type, details);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("LOI TRUY VAN " + e.getMessage());
            e.printStackTrace();
        }

        return req;
    }

    public static void displayAllRequests() {
        String sql = "SELECT * FROM ITRequest";

        try (
                Connection connect = DBConnection.getConnection(); PreparedStatement ps = connect != null ? connect.prepareStatement(sql) : null; ResultSet rs = ps != null ? ps.executeQuery() : null) {
            if (rs != null) {
                System.out.println("-------------DANH SACH YEU CAU-------------");
                while (rs.next()) {
                    int id = rs.getInt("ReqID");
                    String name = rs.getString("ReqName");
                    Date date = rs.getDate("ReqDate");
                    String email = rs.getString("ReqEmail");
                    String type = rs.getString("ReqType");
                    String details = rs.getString("ReqDetails");

                    System.out.println("ReqID: " + id);
                    System.out.println("ReqName: " + name);
                    System.out.println("ReqDate: " + date);
                    System.out.println("ReqEmail: " + email);
                    System.out.println("ReqType: " + type);
                    System.out.println("ReqDetails: " + details);
                    System.out.println("----------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.err.println("LOI TRUY VAN " + e.getMessage());
            e.printStackTrace();
        }
    }
}
