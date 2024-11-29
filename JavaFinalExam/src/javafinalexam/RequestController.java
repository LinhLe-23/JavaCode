package javafinalexam;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class RequestController {

    public List<Request> getAllData() {
        List<Request> ListRequest = new ArrayList<>();

        String sql = "SELECT * FROM ITRequest";
        Connection connect = DBConnection.getConnection();

        try {
            if (connect != null) {
                PreparedStatement prepare = connect.prepareStatement(sql);
                ResultSet rs = prepare.executeQuery();

                while (rs.next()) {
                    int reqID = rs.getInt("ReqID");
                    String reqName = rs.getString("ReqName");
                    Date reqDate = rs.getDate("ReqDate");
                    String reqEmail = rs.getString("ReqEmail");
                    String reqType = rs.getString("ReqType");
                    String reqDetails = rs.getString("ReqDetails");

                    Request request = new Request(reqID, reqName, reqDate, reqEmail, reqType, reqDetails);
                    ListRequest.add(request);
                }
            } else {
                System.out.println("KHONG THE KET NOI");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ListRequest;
    }

    public boolean insertRequest(Request req) {
        Connection connect = DBConnection.getConnection();
        String sql = "INSERT INTO ITRequest (ReqID, ReqName, ReqDate, ReqEmail, ReqType, ReqDetails) " + "VALUES (?, ?, ?, ?, ?, ?)";
        boolean isInsert = false;

        try {
            if (connect != null) {
                PreparedStatement pr = connect.prepareStatement(sql);
                pr.setInt(1, req.getReqID());
                pr.setString(2, req.getReqName());
                pr.setDate(3, req.getReqDate());
                pr.setString(4, req.getReqEmail());
                pr.setString(5, req.getReqType());
                pr.setString(6, req.getReqDetails());

                int rowsAffected = pr.executeUpdate();
                isInsert = rowsAffected > 0;
            } else {
                System.out.println("KHONG THE KET NOI");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isInsert;
    }
    
    public Request findRequest(int reqID) {
    String sql = "SELECT * FROM ITRequest WHERE ReqID = ?";
    Request req = null;

    try (
        Connection connect = DBConnection.getConnection();
        PreparedStatement ps = connect != null ? connect.prepareStatement(sql) : null
    ) {
        if (ps != null) {
            ps.setInt(1, reqID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    
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
        System.err.println("LOI TRUY VAN DU LIEU" + e.getMessage());
        e.printStackTrace();
    }

    return req;
}

}
