package com.example.brmm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseWrapper extends Thread{

    DatabaseConnection dbconn = new DatabaseConnection();
    Connection conn = null;
    private String method = "";
    private String argument = "";
    private String argument2 = "";

    public DatabaseWrapper(Connection connection){
        conn = connection;
    }

    public void setMethod(String method){
        this.method = method;
    }

    public void setArgument(String argument){
        this.argument = argument;
    }

    public void setArgument2(String argument2){
        this.argument2 = argument2;
    }


    public void run(){
        switch (method){
            case "addSectionLeaderRights":
                addSectionLeaderRights(argument);
                break;
            case "removeSectionLeaderRights":
                removeSectionLeaderRights(argument);
                break;
            case "getSectionLeaderRights":
                getSectionLeaderRights(argument);
                break;
            case "removeFacultyRights":
                removeFacultyRights(argument);
                break;
            case "addFacultyRights":
                addFacultyRights(argument);
                break;
            case "getFacultyRights":
                getFacultyRights(argument);
                break;
            case "checklogin":
                checklogin(argument,argument2);
                break;
            case "addUser":
                //addUser();
                break;
            default:
                //System.out.println("Method not found");
                addUser(4,"","","",false, false, "", "test2", "1234","");
                break;
        }
    }

    /*
    USER TABLE SECTION
     */

    //Adds user's rights as a section leader
    public void addSectionLeaderRights(String ulid) {
        try {
            String query = "update user set sectionLeader = 1 where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error on add Section Leader rights");
        }
    }

    //Remove user's rights as a section leader
    public void removeSectionLeaderRights(String ulid) {
        try {
            String query = "update user set sectionLeader = 0 where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error on remove Section Leader rights");
        }
    }

    //Gets the Section Leader's rights
    public boolean getSectionLeaderRights(String ulid){
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
    public void removeFacultyRights(String ulid) {
        try {
            String query = "update user set faculty = 0 where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error on remove Faculty rights");
        }
    }
    //Adds user's rights as a faculty member
    public void addFacultyRights(String ulid) {
        try {
            String query = "update user set faculty = 1 where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error on add Faculty rights");
        }
    }

    //Gets the user's faculty rights
    public boolean getFacultyRights(String ulid){
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
    public boolean checklogin(String ulid, String password) {
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
    public void addUser(int ID,String firstName, String lastName, String section, boolean sectionLeader, boolean faculty, String note, String ulid, String password,String department){
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
    public void removeUser(String ulid){
        try{
            String query = "delete from user where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println("Removal of User failed");
        }
    }

    public ArrayList<Faculty> getFaculty(){
        Faculty faculty = new Faculty();
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

    public ArrayList<Student> getStudents(){
        Student student = new Student();
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

    public ArrayList<Instrument> getInstruments(){
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
    public void addInstrument(String currentOwner, String section, String name, double cost){
        try{
            String query = "insert into item (ownership, section, name, cost, instrument) values ('"+currentOwner+"','"+section+"','"+name+"','"+cost+"',1)";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println("Add instrument failed");
        }
    }

    public void removeInstrument(int id){
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

