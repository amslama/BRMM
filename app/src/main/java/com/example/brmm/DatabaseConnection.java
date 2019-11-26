package com.example.brmm;

import java.sql.Connection;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class DatabaseConnection
{

    private int portNum;
    private final String SSH_Host = "10.110.21.210";
    private final String SSH_Pass = "brmmproject4";
    private final String MySQL_Host = "127.0.0.7";
    private final String MySQL_User = "vmuser";
    private final String MySQL_Pass = "brmmproject4";


    public Connection getConnection()
    {
        Connection conn = null;
        Session session = null;
        try
        {
            JSch jsch = new JSch();
         //   session = jsch.getSession(user, host, portNum);


        }
        catch(Exception e)
        {

        }

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
