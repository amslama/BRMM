package com.example.brmm;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserAuth;
import com.jcraft.jsch.UserInfo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;

public class DatabaseConnection extends Thread {

    private int portNum;
    private final String SSH_Host = "10.110.21.210";
    private final String SSH_User = "vmuser";
    private final String SSH_Pass = "brmmproject4!";
    private final String MySQL_Host = "127.0.0.1";
    private final String MySQL_User = "vmuser";
    private final String MySQL_Pass = "brmmproject4!";
    private Connection conn = null;

    public void run() {
        dbGetConnection();
        getFaculty();
    }

    public Session getServerConnection() {
        //Session serverSession = null;
        Session serverSession = null;
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            serverSession = jsch.getSession(SSH_User, SSH_Host, 22);
            serverSession.setPassword(MySQL_Pass);
            serverSession.setConfig(config);
            System.out.println("Starting SSH Connection");
            System.out.println(serverSession.getHost() + "\n" + serverSession.getUserName() + "\n" + serverSession.getPort());
            System.out.println("Connection Established");
            serverSession.connect();
            System.out.println("Connected = " + serverSession.isConnected());

            //Port Forwarding stuff
            int assigned_port = serverSession.setPortForwardingL(5656, MySQL_Host, 3306);
            System.out.println("localhost:" + assigned_port + "->" + MySQL_Host + ":" + 3306);
            System.out.println("Port Forwarded");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverSession;
    }


    public void dbGetConnection() {
        Session ses = null;
        try {
            ses = getServerConnection();
        } catch (Exception v) {
            System.out.println("Part 2 fail...................");
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:5656/BRMM";
            conn = DriverManager.getConnection(url, "vmuser", "brmmproject4!");
            System.out.println("Connection Complete");

        } catch (Exception t) {
            System.out.println("Part 3 fail...............");
            t.printStackTrace();
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
    public void addUser(int ID,String firstName, String lastName, String section, boolean sectionLeader, boolean faculty, String note, String ulid, String password){
        try{
            int leader = 0;
            int staff = 0;
            if(sectionLeader){
                leader = 1;
            }
            if(faculty){
                staff = 1;
            }
            String query = "insert into user values ("+ID+",'"+firstName+"','"+lastName+"','"+section+"',";
            query = query+leader+","+staff+",'"+note+"','"+ulid+"','"+password+"')";
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

    public ArrayList<Student> getStrudents(){
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

    //TODO: ADD BAND SECTION


    //TODO: EDIT & RECEIVES NOTES FOR USERS

    //TODO: SORT USERS

    //TODO: Get and SORT INSTRUMENTS BY ID ASC

    //TODO: WHEN ADDING INSTRUMENT, AUTO INCREMENT ID



    public void test() {
        try {
            String query = "SELECT * FROM item";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Error on Test");
        }
    }


    public String getSSH_Host() {
        return SSH_Host;
    }

    public String getSSH_Pass() {
        return SSH_Pass;
    }

    public String getMySQL_Host() {
        return MySQL_Host;
    }

    public String getMySQL_User() {
        return MySQL_User;
    }


    public String getMySQL_Pass() {
        return MySQL_Pass;
    }

    public int getPortNum() {
        return portNum;
    }

    public void setPortNum(int portNum) {
        this.portNum = portNum;
    }
}

