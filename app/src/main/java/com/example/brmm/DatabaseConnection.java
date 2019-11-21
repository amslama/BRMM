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

public class DatabaseConnection
{

    private int portNum;
    private final String SSH_Host = "10.110.21.210";
    private final String SSH_Pass = "brmmproject4!";
    private final String MySQL_Host = "127.0.0.7";
    private final String MySQL_User = "vmuser";
    private final String MySQL_Pass = "brmmproject4!";



    public Connection getConnection()
    {
        Connection conn = null;


        try
        {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession("vmuser", "10.110.21.210", 22);
            session.setPassword(MySQL_Pass);
            session.setConfig(config);
            System.out.println("Starting SSH Connection");
            System.out.println(session.getHost()+ "\n" +session.getUserName()+ "\n"+session.getPort());

            session.connect();
            System.out.println("Connection Established");
            System.out.println(session.isConnected());
            System.out.println("Did it hit an exception???????????????????");
            

        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("It did not work.... poop..............................................................................................................................................................................................");
        }
        System.out.println();
        return conn;
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
