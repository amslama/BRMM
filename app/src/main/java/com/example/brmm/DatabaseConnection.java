package com.example.brmm;

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
    public int getSectionLeaderRights(String ulid){
        int rights = 0;
        try{
            String query = "select sectionLeader from user where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            rights = rs.getInt("sectionLeader");
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
    public int getFacultyRights(String ulid){
        int rights = 0;
        try{
            String query = "select faculty from user where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            rights = rs.getInt("faculty");
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
    public void addUser(int ID,String firstName, String lastName, String section, int sectionLeader, int faculty, String note, String ulid, String password){
        try{
            String query = "insert into user values ("+ID+",'"+firstName+"','"+lastName+"','"+section+"',";
            query = query+sectionLeader+","+faculty+",'"+note+"','"+ulid+"','"+password+"')";
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

    public void getUsers(String ascORdesc, String anySection){
        try{
            String query = "select ID,firstName,lastName,section,sectionLeader,faculty,note,username from user order by "+ anySection + " " + ascORdesc;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int i = 1;
            while(rs.next()){
                System.out.println("Round "+i);
                System.out.println("ID: "+rs.getInt("ID"));
                System.out.println("First Name: "+rs.getString("firstName"));
                System.out.println("Last Name: "+rs.getString("lastName"));
                System.out.println("Section: "+rs.getString("section"));
                System.out.println("Section Leader: "+rs.getString("sectionLeader"));
                System.out.println("Faculty: "+rs.getString("faculty"));
                System.out.println("Note: "+rs.getString("note"));
                System.out.println("ULID: "+rs.getString("username"));
                i++;
            }
        }
        catch (Exception e){
            System.out.println("Get users Failed");
        }
    }


    /*
    ITEM TABLE SECTION
     */



    //TODO: INSTRUMENT STUFF

    //TODO: FILTER METHODS

    //TODO: ADD BAND SECTION


    //TODO: EDIT & RECEIVES NOTES FOR USERS

    //TODO: SORT USERS

    //TODO: SORT INSTRUMENTS



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

