package javafinalexam;

import java.sql.Date;

public class Request {
    private int ReqID;
    private String ReqName;
    private Date ReqDate;
    private String ReqEmail;
    private String ReqType;
    private String ReqDetails;

    public Request(int ReqID, String ReqName, Date ReqDate, String ReqEmail, String ReqType, String ReqDetails) {
        this.ReqID = ReqID;
        this.ReqName = ReqName;
        this.ReqDate = ReqDate;
        this.ReqEmail = ReqEmail;
        this.ReqType = ReqType;
        this.ReqDetails = ReqDetails;
    }

    public int getReqID() {
        return ReqID;
    }

    public void setReqID(int ReqID) {
        this.ReqID = ReqID;
    }

    public String getReqName() {
        return ReqName;
    }

    public void setReqName(String ReqName) {
        this.ReqName = ReqName;
    }

    public Date getReqDate() {
        return ReqDate;
    }

    public void setReqDate(Date ReqDate) {
        this.ReqDate = ReqDate;
    }

    public String getReqEmail() {
        return ReqEmail;
    }

    public void setReqEmail(String ReqEmail) {
        this.ReqEmail = ReqEmail;
    }

    public String getReqType() {
        return ReqType;
    }

    public void setReqType(String ReqType) {
        this.ReqType = ReqType;
    }

    public String getReqDetails() {
        return ReqDetails;
    }

    public void setReqDetails(String ReqDetails) {
        this.ReqDetails = ReqDetails;
    }
    
    
}
