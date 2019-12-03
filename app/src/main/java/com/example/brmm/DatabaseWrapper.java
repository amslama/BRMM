package com.example.brmm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseWrapper extends Thread{

    DatabaseConnection dbconn = new DatabaseConnection();
    Connection conn = null;
    private String method = "";
    private int ID = 0;
    private String firstName = "";
    private String lastName = "";
    private String section = "";
    private boolean sectionLeader = false;
    private boolean faculty = false;
    private String note = "";
    private String ulid = "";
    private String password = "";
    private String department = "";
    private ArrayList<Instrument> instrumentList = null;
    private ArrayList<Student> studentList = null;
    private ArrayList<Faculty> facultyList = null;
    private ArrayList<Part> partList = null;
    private boolean valid = false;
    private String currentOwner = "";
    private String name = "";
    private double cost = 0;


    public DatabaseWrapper(Connection connection){
        conn = connection;
    }

    public void setMethod(String method){
        this.method = method;
    }

    public void setUlid(String ulid){
        this.ulid = ulid;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public ArrayList<Student> getStudentList(){
        return studentList;
    }

    public ArrayList<Faculty> getFacultyList(){
        return facultyList;
    }

    public ArrayList<Instrument> getInstrumentList(){
        return instrumentList;
    }

    public  ArrayList<Part> getPartList(){
        return partList;
    }

    public boolean getValidation(){
        return valid;
    }

    public void setUserArguments(int ID,String firstName, String lastName, String section, boolean sectionLeader, boolean faculty, String note, String ulid, String password,String department){
            this.ID = ID;
            this.firstName=firstName;
            this.lastName = lastName;
            this.section = section;
            this.sectionLeader=sectionLeader;
            this.faculty=faculty;
            this.note=note;
            this.ulid=ulid;
            this.password=password;
            this.department=department;
    }

    public boolean getSectionLeaderRights(){
        return sectionLeader;
    }

    public boolean getFacultyRights(){
        return faculty;
    }

    public void setInstrumentVariables(String currentOwner, String section, String name, double cost){
        this.currentOwner = currentOwner;
        this.section = section;
        this.name = name;
        this.cost = cost;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    /**
     * Read the comments in each method on how to use it
     * there then just call setMethod() with one of the options below then if it takes an argument like
     * ulid/ID then call setUlid() with the ulid in the argument
     * (These methods will be listed above)
     */
    public void run(){
        switch (method){
            case "addSectionLeaderRights":
                addSectionLeaderRights(ulid);
                break;
            case "removeSectionLeaderRights":
                removeSectionLeaderRights(ulid);
                break;
            case "getSectionLeaderRights":
                sectionLeader = getSectionLeaderRights(ulid);
                break;
            case "removeFacultyRights":
                removeFacultyRights(ulid);
                break;
            case "addFacultyRights":
                addFacultyRights(ulid);
                break;
            case "getFacultyRights":
                //Call getFaculty after you call run to get true or false
                faculty = getFacultyRights(ulid);
                break;
            case "checkLogin":
                //call setPassword() and setUlid() before you run this thread
                //call getValidation() after you call the run method.
                valid = checklogin(ulid,password);
                password = "";
                break;
            case "addUser":
                //call set user arguments then the run() method (no need for setUlid() method)
                addUser(ID, firstName, lastName, section, sectionLeader, faculty, note, ulid, password, department);
                password = "";
                break;
            case "removeUser":
                removeUser(ulid);
                break;
            case "getFaculty":
                //call the getFacultyList() method after the run() method (No need for setUlid() method)
                getFaculty();
                break;
            case "getStudents":
                //call the getStudentList() method after the run() method (No need for setUlid() method)
                getStudents();
                break;
            case "getInstruments":
                //call the getInstrumentList() method after the run() method (No need for setUlid() method)
                getInstruments();
                break;
            case "addInstrument":
                //call the setInstrumentVariables() method first then call the run method (No need for setUlid() method)
                addInstrument(currentOwner, section, name, cost);
                break;
            case "removeInstrument":
                //Call the setID() method for this one instead of the setUlid() method
                removeInstrument(ID);
                break;
            default:
                System.out.println("Method not found");
                break;
        }
    }

    /*
    USER TABLE SECTION
     */

    //Adds user's rights as a section leader
    private void addSectionLeaderRights(String ulid) {
        try {
            String query = "update user set sectionLeader = 1 where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error on add Section Leader rights");
        }
    }

    //Remove user's rights as a section leader
    private void removeSectionLeaderRights(String ulid) {
        try {
            String query = "update user set sectionLeader = 0 where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error on remove Section Leader rights");
        }
    }

    //Gets the Section Leader's rights
    private boolean getSectionLeaderRights(String ulid){
        boolean rights = false;
        try{
            String query = "select sectionLeader from user where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            if(rs.getInt("sectionLeader")==1){
                rights = true;
            }
        }
        catch(Exception e){
            System.out.println("Get Section Leader rights Failed or invalid user");
        }
        return rights;
    }

    //Remove user's rights as a faculty member
    private void removeFacultyRights(String ulid) {
        try {
            String query = "update user set faculty = 0 where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error on remove Faculty rights");
        }
    }
    //Adds user's rights as a faculty member
    private void addFacultyRights(String ulid) {
        try {
            String query = "update user set faculty = 1 where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error on add Faculty rights");
        }
    }

    //Gets the user's faculty rights
    private boolean getFacultyRights(String ulid){
        boolean rights = false;
        try{
            String query = "select faculty from user where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            if(rs.getInt("faculty")==1){
                rights=true;
            }
        }
        catch(Exception e){
            System.out.println("Get Faculty Rights Failed or invalid user");
        }
        return rights;
    }

    //Login authentication
    //TODO: ADD ENCRYPTION
    private boolean checklogin(String ulid, String password) {
        boolean login = false;
        try {
            String ulidquery = "Select username,passwrd from user where username = '" + ulid + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(ulidquery);
            if (rs.next() && password.equals(rs.getString("passwrd"))) {
                login = true;
            }

        } catch (Exception e) {
            System.out.println("Error on checking login");
        }
        System.out.println(login);
        return login;
    }

    //Adds a user to the database
    private void addUser(int ID,String firstName, String lastName, String section, boolean sectionLeader, boolean faculty, String note, String ulid, String password,String department){
        try{
            int leader = 0;
            int staff = 0;
            if(sectionLeader){
                leader = 1;
            }
            if(faculty){
                staff = 1;
            }
            String query = "insert into user (ID,firstName,lastName,section,sectionLeader,faculty,note,username,passwrd,department) values ("+ID+",'"+firstName+"','"+lastName+"','"+section+"',";
            query = query+leader+","+staff+",'"+note+"','"+ulid+"','"+password+"','"+department+"')";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Add user failed");
        }
    }

    //Removes a user from the database
    private void removeUser(String ulid){
        try{
            String query = "delete from user where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println("Removal of User failed");
        }
    }

    private ArrayList<Faculty> getFaculty(){
        Faculty faculty;
        ArrayList<Faculty> list = new ArrayList<Faculty>();
        try{
            String query = "select firstName,lastName,username,department,role,ID from user where faculty = 1 order by username asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int i = 1;
            while(rs.next()){
                faculty = new Faculty(rs.getString("firstName"),rs.getString("lastName"),rs.getString("username"),rs.getString("department"),rs.getString("role"),rs.getInt("ID"));
                list.add(faculty);
            }
        }
        catch (Exception e){
            System.out.println("Get users Failed");
        }
        return list;
    }

    private ArrayList<Student> getStudents(){
        Student student;
        ArrayList<Student> list = new ArrayList<Student>();
        try{
            String query = "select firstName,lastName,section,sectionLeader,note,username,ID from user where faculty = 0 order by username asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean leader = false;
            while(rs.next()){
                if(rs.getInt("sectionLeader")==1){
                    leader = true;
                }
                student = new Student(rs.getString("firstName"),rs.getString("lastName"),rs.getString("username"),rs.getString("section"),leader,rs.getInt("ID"),rs.getString("note"));
                list.add(student);
            }

        }
        catch(Exception e){
            System.out.println("Get students failed");
        }
        return list;
    }


    /*
    ITEM TABLE SECTION
     */

    private ArrayList<Instrument> getInstruments(){
        ArrayList<Instrument> list = new ArrayList<Instrument>();
        Instrument instrument;
        try{
            String query = "Select ownership,section,name,cost,id from instrument where instrument = 1 order by id asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                instrument = new Instrument(rs.getString("ownership"),rs.getString("section"),rs.getString("name"),Double.parseDouble(rs.getString("cost")),rs.getInt("id"));
                list.add(instrument);
            }
        }
        catch(Exception e)
        {
            System.out.println("Could not get instruments");
        }
        return list;
    }

    //TODO: INSTRUMENT STUFF
    private void addInstrument(String currentOwner, String section, String name, double cost){
        try{
            String query = "insert into item (ownership, section, name, cost, instrument) values ('"+currentOwner+"','"+section+"','"+name+"','"+cost+"',1)";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println("Add instrument failed");
        }
    }

    private void removeInstrument(int id){
        try{
            String query = "delete from item where ID="+id;
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println("Remove instrument failed");
        }
    }

}

