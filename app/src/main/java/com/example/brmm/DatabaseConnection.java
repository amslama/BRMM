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

public class DatabaseConnection extends Thread
{

    private int portNum;
    private final String SSH_Host = "10.110.21.210";
    private final String SSH_User = "vmuser";
    private final String SSH_Pass = "brmmproject4!";
    private final String MySQL_Host = "127.0.0.7";
    private final String MySQL_User = "vmuser";
    private final String MySQL_Pass = "brmmproject4!";

    public void run(){
        getServerConnection();
    }

    public void getServerConnection()
    {
        Connection serverConn = null;

        try
        {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session serverSession = jsch.getSession(SSH_User, SSH_Host, 22);
            serverSession.setPassword(MySQL_Pass);
            serverSession.setConfig(config);
            System.out.println("Starting SSH Connection");
            System.out.println(serverSession.getHost()+ "\n" +serverSession.getUserName()+ "\n"+serverSession.getPort());

            serverSession.connect();
            //Port Forwarding stuff
            System.out.println("Connection Established");
            System.out.println("Connected = "+serverSession.isConnected());
            int assigned_port = serverSession.setPortForwardingL(5656,MySQL_Host,3306);
            System.out.println("localhost:"+ assigned_port+"->"+MySQL_Host+":"+3306);
            System.out.println("Port Forwarded");

            //DB Part
            System.out.println("Before the Driver");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("After the Driver.......................");
            //Getting Stuck Right Here
            serverConn = DriverManager.getConnection("jdbc:mysql://localhost:"+5656+"/mysql?autoReconnect=true&amp;useSSL=false",MySQL_User,MySQL_Pass);
            System.out.println("Database connection established");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println();
        //return conn;
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
