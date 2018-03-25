package sample;

public class Student {
    private String Sid;
    private String Sname;
    private String Sage;
    private String Ssex;
    private String Sdept;
    private String Saddress;
    private String Sclass;

    public String getSclass() {
        return Sclass;
    }

    public void setSclass(String sclass) {
        Sclass = sclass;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSage() {
        return Sage;
    }

    public void setSage(String sage) {
        Sage = sage;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public String getSdept() {
        return Sdept;
    }

    public void setSdept(String sdept) {
        Sdept = sdept;
    }

    public String getSaddress() {
        return Saddress;
    }

    public void setSaddress(String saddress) {
        Saddress = saddress;
    }

    public Student(String Sid,String Sname,String Sage,String Ssex,String Sclass,String Sdept,String Sddr) {
        this.Sid=Sid;
        this.Sname=Sname;
        this.Sage=Sage;
        this.Ssex=Ssex;
        this.Sclass=Sclass;
        this.Sdept=Sdept;
        this.Saddress=Sddr;
    }
}
