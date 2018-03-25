package sample;


        import com.sun.xml.internal.ws.api.ha.StickyFeature;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.AnchorPane;

        import java.net.URL;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.time.LocalDate;
        import java.util.ResourceBundle;

/**
 *
 * @author qiusuo
 */
public class FXMLDocumentController implements Initializable {

    //    @FXML
//    private Label label;
    @FXML
    private CheckBox sNumCheck;
    @FXML
    private CheckBox nameCheck;
    @FXML
    private CheckBox ageCheck;
    @FXML
    private CheckBox sexCheck;
    @FXML
    private CheckBox classCheck;
    @FXML
    private CheckBox deptCheck;
    @FXML
    private CheckBox addressCheck;
    @FXML
    private TextField name;
    @FXML
    private TextField sNum;
    @FXML
    private TextField ageFrom;
    @FXML
    private TextField sex;
    @FXML
    private TextField dept;
    @FXML
    private TextField address;
    @FXML
    private TextField ageTo;
    @FXML
    private Button startQuery;
    @FXML
    private Button insertRecord;
    @FXML
    private AnchorPane sqlstatement;
    @FXML
    private TableColumn<Student, String> Sid;
    @FXML
    private TextArea sqlStatement;
    @FXML
    private TextField classId;
    @FXML
    private Label label;
    @FXML
    private TableColumn<Student, String> Sname;
    @FXML
    private TableColumn<Student, Integer> Sage;
    @FXML
    private TableColumn<Student, String> Ssex;
    @FXML
    private TableColumn<Student, String> Sclass;
    @FXML
    private TableColumn<Student, String> Sdept;
    @FXML
    private TableColumn<Student, String> Saddress;
    @FXML
    private TableColumn<Student, String> deleteRecord;
    @FXML
    private TableView<Student> resultTable;

    @FXML
    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");

        StringBuilder sqlStat=new StringBuilder();
        sqlStat.append("SELECT * FROM Student WHERE ");
        boolean filter=false;
        boolean preConditionSet=false;
//        if (event.getSource() == sNumCheck || event.getSource() == nameCheck||event.getSource() == ageCheck||event.getSource() == sexCheck||event.getSource()==classCheck||event.getSource()==deptCheck||event.getSource()==addressCheck) {
//
//        }
        if(event.getSource()!=insertRecord){
            if(sNumCheck.isSelected()){

                if(sNum.getText().trim().length()>0){
                    filter=true;

                    if(preConditionSet){
                        sqlStat.append("AND ");
                    }
                    sqlStat.append("Sid like '%"+sNum.getText()+"%' ");
                    preConditionSet=true;
                }
            }
            if(nameCheck.isSelected()){

                if(name.getText().trim().length()>0){
                    filter=true;
                    if(preConditionSet){
                        sqlStat.append("AND ");
                    }
                    sqlStat.append("Sname like '%"+name.getText()+"%' ");
                    preConditionSet=true;
                }

            }
            if(ageCheck.isSelected()){

                if(ageFrom.getText().trim().length()>0){
                    filter=true;
                    if(preConditionSet){
                        sqlStat.append("AND ");
                    }
                    sqlStat.append("Sage>="+ageFrom.getText()+" ");
                    preConditionSet=true;
                }

                if(ageTo.getText().trim().length()>0){
                    filter=true;
                    if(preConditionSet){
                        sqlStat.append("AND ");
                    }
                    sqlStat.append("Sage<="+ageTo.getText()+" ");
                    preConditionSet=true;
                }

            }
            if(sexCheck.isSelected()){

                if(sex.getText().trim().length()>0){
                    filter=true;
                    if(preConditionSet){
                        sqlStat.append("AND ");
                    }
                    sqlStat.append("Ssex='"+sex.getText()+"' ");
                    preConditionSet=true;
                }

            }
            if(classCheck.isSelected()){

                if(classId.getText().trim().length()>0){
                    filter=true;
                    if(preConditionSet){
                        sqlStat.append("AND ");
                    }
                    sqlStat.append("Sclass like '%"+classId.getText()+"%' ");
                    preConditionSet=true;
                }

            }
            if(deptCheck.isSelected()){

                if(dept.getText().trim().length()>0){
                    filter=true;
                    if(preConditionSet){
                        sqlStat.append("AND ");
                    }
                    sqlStat.append("Sdept like '%"+dept.getText()+"%' ");
                    preConditionSet=true;
                }

            }
            if(addressCheck.isSelected()){

                if(address.getText().trim().length()>0){
                    filter=true;
                    if(preConditionSet){
                        sqlStat.append("AND ");
                    }
                    sqlStat.append("Saddress like '%"+address.getText()+"%' ");
                }

            }


            String sqlStatementStirng=sqlStat.toString();
            if(filter==false)
                sqlStatementStirng="SELECT * FROM Student";
            sqlStatement.setText(sqlStatementStirng);
            if(event.getSource()==startQuery) {
                try {
                    MysqlConnector mysqlConnector = new MysqlConnector();


                    Connection con = mysqlConnector.solution("DataBaseCourse");
                    Statement statement = null;

                    statement = con.createStatement();
                    ResultSet r=statement.executeQuery(sqlStatementStirng);


                    resultTable.getItems().clear();
                    while (r.next()){
//                    Sage.setCellFactory();
//                    Sage.getColumns().add(r.getString("Sname"));
//                    Sname.set
                        Student newStudent = new Student(r.getString("Sid"),

                                r.getString("Sname"),
                                r.getInt("Sage"),
                                r.getString("Ssex"),
                                r.getString("Sclass"),
                                r.getString("Sdept"),
                                r.getString("Saddress")
                        );



                        //Get all the items from the table as a list, then add the new person to

                        //the list

                        resultTable.getItems().add(newStudent);
                        System.out.println(resultTable.getColumns());
                    }
                    r.close();
                    con.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }


        }
        else {
            String SidInsert="NULL";
            String SnameInsert="NULL";
            String SageInsert="NULL";
            String SsexInsert="NULL";
            String SclassInsert="NULL";
            String SdeptInsert="NULL";
            String SaddressInsert="NULL";

            if(sNum.getText().trim().length()>0){
               SidInsert=sNum.getText().trim();
            }
            if(name.getText().trim().length()>0){
                SnameInsert=name.getText().trim();
            } if(ageFrom.getText().trim().length()>0){
                SageInsert=ageFrom.getText().trim();
            } if(sex.getText().trim().length()>0){
                SsexInsert=sex.getText().trim();
            } if(classId.getText().trim().length()>0){
                SclassInsert=classId.getText().trim();
            } if(dept.getText().trim().length()>0){
                SdeptInsert=dept.getText().trim();
            } if(address.getText().trim().length()>0){
                SaddressInsert=address.getText().trim();
            }

            if(SidInsert.equals("NULL")){
                sqlStatement.setText("Invalid Values!");
            }
            else{
                String insertStatement="INSERT INTO STUDENT (Sid,Sname,Sage,Ssex,Sclass,Sdept,Saddress) VALUES ('"+SidInsert+"','"+SnameInsert+"','"+SageInsert+"','"+SsexInsert+"','"+SclassInsert+"','"+SdeptInsert+"','"+SaddressInsert+"')";
                try {
                    MysqlConnector mysqlConnector = new MysqlConnector();


                    Connection con = mysqlConnector.solution("DataBaseCourse");
                    Statement statement = null;

                    statement = con.createStatement();
                    statement.executeUpdate(insertStatement);
                    con.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                sqlStatement.setText(insertStatement);
            }

        }
    }

//        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        Sid=new TableColumn<Student, String>();
        Sid.setCellValueFactory(new PropertyValueFactory<Student, String>("Sid"));

        Sname.setCellValueFactory(new PropertyValueFactory<Student, String>("Sname"));
        Ssex.setCellValueFactory(new PropertyValueFactory<Student, String>("Ssex"));
        Sdept.setCellValueFactory(new PropertyValueFactory<Student, String>("Sdept"));
        Saddress.setCellValueFactory(new PropertyValueFactory<Student, String>("Saddress"));

        Sage.setCellValueFactory(new PropertyValueFactory<Student, Integer>("Sage"));



        //load dummy data

        resultTable.setItems(getStudent());
    }
    public ObservableList<Student> getStudent()
    {
        ObservableList<Student> people = FXCollections.observableArrayList();
//        people.add(new Student("Frank","Sinatra", 22,"男","1503106","002","哈尔滨"));
        return people;
    }

}
