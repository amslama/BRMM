package com.example.brmm;
import java.sql.Array;
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
    private ArrayList<Student> listOfStudents = new ArrayList<Student>();
    private ArrayList<Faculty> facultyList = null;
    private ArrayList<Part> partList = null;
    private boolean valid = false;
    private String currentOwner = "";
    private String name = "";
    private double cost = 0;
    private String category = "";
    private int serialNumber = 0;



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
        return listOfStudents;
    }

    public ArrayList<Faculty> getFacultyList(){
        return facultyList;
    }

    public ArrayList<Instrument> getInstrumentList(){
        return instrumentList;
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

    public void setInstrumentVariables(String currentOwner, String section, String name, double cost, String catagory){
        this.currentOwner = currentOwner;
        this.section = section;
        this.name = name;
        this.cost = cost;
        this.category = catagory;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public ArrayList<Part> getPartList(){
        return partList;
    }

    public void setPart(double cost, String name, String category, int serialNumber){
        this.cost = cost;
        this.name = name;
        this.category = category;
        this.serialNumber = serialNumber;
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
                //Call getSectionLeaderRights after you call run to get true or false
                sectionLeader = getSectionLeaderRights(ulid);
                break;
            case "removeFacultyRights":
                removeFacultyRights(ulid);
                break;
            case "addFacultyRights":
                addFacultyRights(ulid);
                break;
            case "getFacultyRights":
                //Call getFacultyRights after you call run to get true or false
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
                addUser(ID, firstName, lastName, section, sectionLeader, faculty, note, ulid, password);
                password = "";
                break;
            case "removeUser":
                removeUser(ulid);
                break;
            case "getFaculty":
                //call the getFacultyList() method after the run() method (No need for setUlid() method)
                facultyList = getFaculty();
                break;
            case "getStudents":
                //call the getStudentList() method after the run() method (No need for setUlid() method)
                try {
                    getStudents();
                }
                catch (Exception e){
                    System.out.println("Assigning the student list is not working................................................");
                }
                break;
            case "getInstruments":
                //call the getInstrumentList() method after the run() method (No need for setUlid() method)
                instrumentList = getInstruments();
                break;
            case "addInstrument":
                //call the setInstrumentVariables() method first then call the run method (No need for setUlid() method)
                addInstrument(currentOwner, section, name, cost, category);
                break;
            case "removeInstrument":
                //Call the setID() method for this one instead of the setUlid() method
                removeInstrument(ID);
                break;
            case "getParts":
                //Call getPartList after the run method
                partList = getParts();
                break;
            case "addPart":
                //Call the setPart method first then call run() method
                addPart(cost, name, category, serialNumber);
                break;
            case "removePart":
                //Call setID before the run method
                removePart(ID);
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
    private void addUser(int ID,String firstName, String lastName, String section, boolean sectionLeader, boolean faculty, String note, String ulid, String password){
        try{
            int leader = 0;
            int staff = 0;
            if(sectionLeader){
                leader = 1;
            }
            if(faculty){
                staff = 1;
            }
            String query = "insert into user (ID,firstName,lastName,section,sectionLeader,faculty,note,username,passwrd) values ("+ID+",'"+firstName+"','"+lastName+"','"+section+"',";
            query = query+leader+","+staff+",'"+note+"','"+ulid+"','"+password+"')";
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
            String query = "select firstName,lastName,username,role,ID from user where faculty = 1 order by username asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                faculty = new Faculty(rs.getString("firstName"),rs.getString("lastName"),rs.getString("username"),rs.getString("role"),rs.getInt("ID"));
                list.add(faculty);
            }
        }
        catch (Exception e){
            System.out.println("Get users Failed");
        }
        return list;
    }




    /*
    ITEM TABLE SECTION
     */

    private ArrayList<Instrument> getInstruments(){
        instrumentList = new ArrayList<Instrument>();
        Instrument instrument;
        try{
            String query = "Select ownership,section,name,cost,id,category from item where instrument = 1 order by id asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                instrument = new Instrument(rs.getString("ownership"),rs.getString("section"),rs.getString("name"),Double.parseDouble(rs.getString("cost")),rs.getInt("id"),rs.getString("category"));
                instrumentList.add(instrument);
            }
        }
        catch(Exception e)
        {
            System.out.println("Could not get instruments");
        }
        return instrumentList;
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
                System.out.println(rs.getString("firstName")+"\n"+rs.getString("lastName")+"\n"+rs.getString("username")+"\n"+rs.getString("section")+"\n"+leader+rs.getInt("ID")+"\n"+rs.getString("note")+"\n");
            }

        }
        catch(Exception e) {
            System.out.println("Get students failed");
        }
        return list;
    }

    //TODO: INSTRUMENT STUFF
    private void addInstrument(String currentOwner, String section, String name, double cost, String catagory){
        try{
            String query = "insert into item (ownership, section, name, cost, instrument,category) values ('"+currentOwner+"','"+section+"','"+name+"','"+cost+"',1,'"+catagory+"')";
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

    private ArrayList<Part> getParts(){
        Part part;
        ArrayList<Part> partList = new ArrayList<Part>();

        try{
            System.out.println("select is bad");
            String query = "select cost, name, category, serialNumber from item where instrument = 0";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("Select isnt bad");
            double realCost = 0;

            while(rs.next()){
                System.out.println("While loop");
                    realCost = Double.parseDouble(rs.getString("cost"));
                part = new Part(realCost, rs.getString("name"), rs.getString("category"), rs.getString("serialNumber"));
                partList.add(part);
            }
        }
        catch (Exception e){
            System.out.println("Failed to get parts");
        }
        return partList;
    }

    private void addPart(double cost, String name, String category, int serialNumber){
        try{
            String stringCost = cost + "";
            String query = "insert into item (cost, name, category, serialNumber,instrument) values ('"+stringCost+"','"+name+"','"+category+"',"+serialNumber+","+0+")";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Failed to add part");
        }
    }

    private void removePart(int ID){
        try{
            String query = "remove item where ID = "+ID;
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Failed to remove part");
        }
    }

    private void changeSection(String ulid){

    }

}

