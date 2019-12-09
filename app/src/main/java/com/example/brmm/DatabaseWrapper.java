package com.example.brmm;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseWrapper extends Thread{


    DatabaseConnection dbconn = new DatabaseConnection();
    Connection conn = null;
    Encryption encrypt = new Encryption();

    private String method = "";
    private int ID = 0;
    private String firstName = "";
    private String lastName = "";
    private String section = "";
    private String sectionLeader = "";
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
    private String newSection = "";
    private ArrayList<String> sectionList;
    private ArrayList<Student> studentArrayList = null;
    private ArrayList<Faculty> facultyArrayList = null;
    private ArrayList<Instrument> instrumentArrayList = null;
    private ArrayList<Part> partArrayList = null;
    private ArrayList<Category> superCategoryArrayList = null;
    private ArrayList<String> superCategoryPairsArrayList = null;


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

    //Sets the variables to be used with the AddUser() method
    public void setUserArguments(int ID,String firstName, String lastName, String section, String sectionLeader, boolean faculty, String note, String ulid, String password,String department){
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

    //Returns the section leader's Rights
    public String getSectionLeaderRights(){
        return sectionLeader;
    }

    //Returns the faculty rights
    public boolean getFacultyRights(){
        return faculty;
    }

    //Sets the variables used in the addInstrument() method
    public void setInstrumentVariables(String currentOwner, String section, String name, double cost, String catagory){
        this.currentOwner = currentOwner;
        this.section = section;
        this.name = name;
        this.cost = cost;
        this.category = catagory;
    }

    //Sets the ID for use in many getter methods
    public void setID(int ID){
        this.ID = ID;
    }

    //Returns the partList ArrayList
    public ArrayList<Part> getPartList(){
        return partList;
    }

    //Sets the variables that addPart() method uses
    public void setPart(double cost, String name, String category, int serialNumber){
        this.cost = cost;
        this.name = name;
        this.category = category;
        this.serialNumber = serialNumber;
    }

    //Sets the section to be used in the addSection() method
    public void setSection(String section){
        this.section = section;
    }

    //Sets newSection to be used in the changeSection() method
    public void setNewSection(String section){
        newSection = section;
    }

    //Gets the sectionList returned from the database method
    public ArrayList<String> getSectionList(){
        return sectionList;
    }

    //Supplies the studentArrayList to be used in the super user update method
    public void setStudentArrayList(ArrayList<Student> studentArrayList){
        this.studentArrayList = studentArrayList;
    }

    //Supplies the studentArrayList variable to be used in the user update method
    public void setFacultyArrayList(ArrayList<Faculty> facultyArrayList){
        this.facultyArrayList = facultyArrayList;
    }

    //Supplies the instrumentArrayList variable to be used in the user update method
    public void setInstrumentArrayList(ArrayList<Instrument> instrumentArrayList){
        this.instrumentArrayList = instrumentArrayList;
    }

    //Supplies the partArrayList variable to be used in the item update method
    public void setPartArrayList(ArrayList<Part> partArrayList){
        this.partArrayList = partArrayList;
    }

    //Supplies the supperCategoryArrayList variable to be used push the category relationship pairs
    public void setSuperCategoryArrayList(ArrayList<Category> superCategoryArrayList){
        this.superCategoryArrayList = superCategoryArrayList;
    }

    //Returns the relationship pairs from the superCategoryPairArraylist variable
    public ArrayList<String> getSuperCategoryPairsArrayList(){
        return superCategoryPairsArrayList;
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
                listOfStudents = getStudents();
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
            case "changeSectionUsers":
                //Call setUlid() with the user you want to update
                //then setSection() with the section you want to change that user to
                changeSectionUsers(ulid, section);
                break;
            case "changeAllOfOneSectionUsers":
                //Set old section by calling SetSection() method
                //Set new section by calling SetNewSection() method
                changeAllOfOneSectionUsers(section, newSection);
                break;
            case "getUniqueSectionUsers":
                //Just call getSectionList() method after this
                sectionList = getUniqueSectionUsers();
                break;
            case "changeSectionItem":
                //Call setID() then setSection() then run this method
                changeSectionItem(ID, section);
                break;
            case "changeAllOfOneSectionItem":
                //Set old section by calling SetSection() method
                //Set new section by calling SetNewSection() method
                changeAllOfOneSectionItem(section, newSection);
                break;
            case "getUniqueSectionItem":
                sectionList = getUniqueSectionItem();
                break;
            case "superUpdateUser":
                //Call the setStudentArrayList() method then the setFacultyArrayList() method before running this
                superUpdateUser(studentArrayList, facultyArrayList);
                break;
            case "superUpdateItem":
                //Call the setInstrumentArrayList() method then the setPartArrayList() method
                superUpdateItem(instrumentArrayList, partArrayList);
                break;
            case "updateSuperCategory":
                //Call the setSuperCategoryArrayList() method before this
                updateSuperCategory(superCategoryArrayList);
                break;
            case "getSuperCategory":
                //Call the getSuperCategoryPairsArrayList() method after this
                superCategoryPairsArrayList = getSuperCategory();
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
    private boolean checklogin(String ulid, String password) {
        boolean login = false;
        try {
            String ulidquery = "Select username,passwrd from user where username = '" + ulid + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(ulidquery);
            if (rs.next() && password.equals(encrypt.decode(rs.getString("passwrd")))) {
                login = true;
            }

        } catch (Exception e) {
            System.out.println("Error on checking login");
        }
        System.out.println(login);
        return login;
    }

    //Adds a user to the database
    private void addUser(int ID,String firstName, String lastName, String section, String sectionLeader, boolean faculty, String note, String ulid, String password){
        try{
            int staff = 0;

            if(faculty){
                staff = 1;
            }
            String query = "insert into user (ID,firstName,lastName,section,sectionLeader,faculty,note,username,passwrd) values ("+ID+",'"+firstName+"','"+lastName+"','"+section+"',";
            query = query+sectionLeader+","+staff+",'"+note+"','"+ulid+"','"+encrypt.encode(password)+"')";
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

    //gets all the faculty rows/objects from the database
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

    //Gets the instruments from the database
    private ArrayList<Instrument> getInstruments(){
        instrumentList = new ArrayList<Instrument>();
        Instrument instrument;
        try{
            String query = "Select ownership,section,name,cost,id,category from item where instrument = 1 order by id asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                instrument = new Instrument(rs.getString("ownership"),rs.getString("section"),rs.getString("name"),rs.getDouble("cost"),rs.getInt("id"),rs.getString("category"));
                instrumentList.add(instrument);
            }
        }
        catch(Exception e)
        {
            System.out.println("Could not get instruments");
        }
        return instrumentList;
    }

    //Gets each student rows/objects from the database
    private ArrayList<Student> getStudents(){
        Student student;
        ArrayList<Student> list = new ArrayList<Student>();
        try{
            String query = "select firstName,lastName,section,sectionLeader,note,username,ID from user where faculty = 0 order by username asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                student = new Student(rs.getString("firstName"),rs.getString("lastName"),rs.getString("username"),rs.getString("section"),rs.getString("sectionLeader"),rs.getInt("ID"),rs.getString("note"));
                list.add(student);
            }

        }
        catch(Exception e) {
            System.out.println("Get students failed");
        }
        return list;
    }

    //Adds an instrument row to the database
    private void addInstrument(String currentOwner, String section, String name, double cost, String catagory){
        try{
            String query = "insert into item (ownership, section, name, cost, instrument,category) values ('"+currentOwner+"','"+section+"','"+name+"',"+cost+",1,'"+catagory+"')";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println("Add instrument failed");
        }
    }

    //removes an instrument row from the database
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

    //gets all the part rows/objects from the database
    private ArrayList<Part> getParts(){
        Part part;
        ArrayList<Part> partList = new ArrayList<Part>();

        try{
            String query = "select cost, name, category, serialNumber from item where instrument = 0";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                part = new Part(rs.getDouble("cost"), rs.getString("name"), rs.getString("category"), rs.getString("serialNumber"));
                partList.add(part);
            }
        }
        catch (Exception e){
            System.out.println("Failed to get parts");
        }
        return partList;
    }

    //Adds a part object/row to the database
    private void addPart(double cost, String name, String category, int serialNumber){
        try{
            String query = "insert into item (cost, name, category, serialNumber,instrument) values ("+cost+",'"+name+"','"+category+"',"+serialNumber+","+0+")";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Failed to add part");
        }
    }

    //removes a part row from the database
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

    //Changes the section value of a user row in the database
    private void changeSectionUsers(String ulid, String section){
        try{
            String query = "update user set section = '"+section+"' where username = '"+ulid+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Change Section Failed (USER)");
        }
    }

    //Changes all of one section in the database to something else
    private void changeAllOfOneSectionUsers(String oldSection, String newSection){
        try{
            String query = "update user set section = '"+oldSection+"' where section = '"+newSection+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Change all sections failed (USER)");
        }
    }

    //Returns the unique sections from the user table
    private ArrayList<String> getUniqueSectionUsers(){
        ArrayList<String> list = new ArrayList<String>();
        try{
            String query = "select distinct section from user";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                list.add(rs.getString("section"));
            }
        }
        catch (Exception e){
            System.out.println("Get all sections failed (USER)");
        }
        return list;
    }

    //Changes the section of one item row/object in the database
    private void changeSectionItem(int ID, String section){
        try{
            String query = "update item set section = '"+section+"' where ID = '"+ID+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Change Section Failed (ITEM)");
        }
    }

    //Changes all of one section for all items in the database
    private void changeAllOfOneSectionItem(String oldSection, String newSection){
        try{
            String query = "update item set section = '"+oldSection+"' where section = '"+newSection+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Change all sections failed (ITEM)");
        }
    }

    //Gets the unique sections from the item table in the database
    private ArrayList<String> getUniqueSectionItem(){
        ArrayList<String> list = new ArrayList<String>();
        try{
            String query = "select distinct section from item";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                list.add(rs.getString("section"));
            }
        }
        catch (Exception e){
            System.out.println("Get all sections failed (ITEM)");
        }
        return list;
    }

    //Updates the user table in the database (Will not remove or add to the database)
    private void superUpdateUser(ArrayList<Student> studentsArrayList, ArrayList<Faculty> facultyArrayList){
        //Student Portion
        String firstName = "";
        String lastName = "";
        String ulid = "";
        String section = "";
        String sectionLeader = "";
        String role = "";
        int UID = 0;
        String notes = "";

        try{
            String delete = "delete from user";
            Statement statement = conn.createStatement();
            statement.executeUpdate(delete);
        }
        catch (Exception e){
            System.out.println("Delete of students failed");
        }

        for(int i = 0; studentsArrayList.get(i) != null; i++) {
            firstName = studentsArrayList.get(i).fname;
            lastName = studentsArrayList.get(i).lname;
            ulid = studentsArrayList.get(i).ulid;
            section = studentsArrayList.get(i).getSection();
            sectionLeader = studentsArrayList.get(i).getSectionLeader();
            notes = studentsArrayList.get(i).getNotes();

            try {
                //Student Portion
                System.out.println("SSSSSSSSSSSSSSSSSSSSs");
                //String firstName, String lastName, String ulid, String section, boolean sectionLeader, int UID, String notes
                String newQuery = "insert into user (firstName, lastName, username, section, sectionLeader,notes) values ('"+firstName+"','"+ lastName + "','"+ ulid + "','"+ section + "','"+ sectionLeader + "','"+ notes+"');";
                System.out.println("mmmmmmmmmmmmmmmmmmmmmmmm");
                //String query1 = "update user set firstName = '" + firstName + "' where ID = " + ID + ";";
                //String query2 = "update user set lastName = '" + lastName + "' where ID = " + ID + ";";
                //String query3 = "update user set username = '" + ulid + "' where ID = " + ID + ";";
                //String query4 = "update user set section = '" + section + "' where ID = " + ID + ";";
                //String query5 = "update user set sectionLeader = '" + sectionLeader + "' where ID = " + ID + ";";
                //String query6 = "update user set notes = '" + notes + "' where ID = " + ID + ";";
                Statement st = conn.createStatement();
                System.out.println("llllllllllllllllllllllllllllllll");
                //st.executeUpdate(query1);
                //st.executeUpdate(query2);
                //st.executeUpdate(query3);
                //st.executeUpdate(query4);
                //st.executeUpdate(query5);
                //st.executeUpdate(query6);
                st.executeUpdate(newQuery);
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
            } catch (Exception e) {
                System.out.println("Student part failed");
            }
        }

        //Faculty Part
        //String firstname, String lastname, String ulid, String role, int UID
        for(int i = 0; studentsArrayList.get(i) != null; i++){
            try {
                firstName = facultyArrayList.get(i).fname;
                lastName = facultyArrayList.get(i).lname;
                ulid = facultyArrayList.get(i).ulid;
                role = facultyArrayList.get(i).getRole();
                UID = facultyArrayList.get(i).getUID();

                String newQuery = "insert into user (firstName, lastName, username,role,ID) values ('"+firstName+"','"+lastName+"','"+ulid+"','"+role+"',"+UID+");";

                //String query1 = "update user set firstName = '" + firstName + "' where ID = " + ID + ";";
                //String query2 = "update user set lastName = '" + lastName + "' where ID = " + ID + ";";
                //String query3 = "update user set username = '" + ulid + "' where ID = " + ID + ";";
                //String query4 = "update user set role = '" + role + "' where ID = " + ID + ";";
                //String query5 = "update user set ID = '" + UID + "' where ID = " + ID + ";";
                Statement st = conn.createStatement();
                //st.executeUpdate(query1);
                //st.executeUpdate(query2);
                //st.executeUpdate(query3);
                //st.executeUpdate(query4);
                //st.executeUpdate(query5);
                st.executeUpdate(newQuery);
            }
            catch (Exception e){
                System.out.println("Faculty part failed");
            }
        }
    }

    //Updates the item table in the database (Will not add or remove)
    private void superUpdateItem(ArrayList<Instrument> instrumentArrayList, ArrayList<Part> partArrayList){
        //Instrument Section
        //String co, String st, String na, double price, int idnum, String category
        String currentOwner = "";
        String section = "";
        String name = "";
        double price = 0;
        int ID = 0;
        String category = "";
        String serialNumber = "";

        try{
            String delete = "delete from item";
            Statement statement = conn.createStatement();
            statement.executeUpdate(delete);
        }
        catch (Exception e){
            System.out.println("Deleting items failed");
        }
        try{
            String reset = "ALTER TABLE item AUTO_INCREMENT = 1;";
            Statement resetStatement = conn.createStatement();
            resetStatement.executeUpdate(reset);
        }
        catch (Exception e){
            System.out.println("Failed to reset auto increment");
        }

        for(int i = 0; instrumentArrayList.get(i) != null; i++){
            try {
                currentOwner = instrumentArrayList.get(i).currentOwner;
                section = instrumentArrayList.get(i).getSection();
                name = instrumentArrayList.get(i).getName();
                price = instrumentArrayList.get(i).cost;
                ID = instrumentArrayList.get(i).id;
                category = instrumentArrayList.get(i).getCategory().toString();

                String newQuery = "insert into item (name, ownership,section,cost,category) values ('"+name+"','"+currentOwner+"','"+section+"',"+price+",'"+category+"');";

                //String query1 = "update user set name = '" + name + "' where ID = " + ID + ";";
                //String query2 = "update user set ownership = '" + currentOwner + "' where ID = " + ID + ";";
                //String query3 = "update user set section = '" + section + "' where ID = " + ID + ";";
                //String query4 = "update user set cost = '" + price + "' where ID = " + ID + ";";
                //String query5 = "update user set category = '" + category + "' where ID = " + ID + ";";
                Statement st = conn.createStatement();
                //st.executeUpdate(query1);
                //st.executeUpdate(query2);
                //st.executeUpdate(query3);
                //st.executeUpdate(query4);
                //st.executeUpdate(query5);
                st.executeUpdate(newQuery);
            }
            catch (Exception e){
                System.out.println("Instrument portion failed");
            }

        }

        for(int i = 0; partArrayList.get(i) != null; i++){
            try{
                //Part Portion
                //double cost, String name, String category, String serialNumber
                price = partArrayList.get(i).cost;
                name = partArrayList.get(i).name;
                category = partArrayList.get(i).category;
                serialNumber = partArrayList.get(i).getSerialNumber();
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                String newQuery = "insert into item (category, name, cost, serialNumber) values ('"+category+"','"+name+"',"+price+",'"+serialNumber+"');";
                System.out.println("fffffffffffffffffffffffffffffffffff");
                //String query1 = "update user set category = '" + category + "' where ID = " + ID + ";";
                //String query2 = "update user set name = '" + name + "' where ID = " + ID + ";";
                //String query3 = "update user set cost = '" + price + "' where ID = " + ID + ";";
                //String query4 = "update user set serialNumber = '" + serialNumber + "' where ID = " + ID + ";";
                Statement st = conn.createStatement();
                //st.executeUpdate(query1);
                //st.executeUpdate(query2);
                //st.executeUpdate(query3);
                //st.executeUpdate(query4);
                st.executeUpdate(newQuery);
            }
            catch (Exception e){
                System.out.println("Part portion failed");
            }
        }
    }

    //Deletes then rewrites the whole superCategory table in the database
    private void updateSuperCategory(ArrayList<Category> superCategoryArrayList){
        String temp = "";
        try{
            String delete = "delete from superCategory;";
            Statement st = conn.createStatement();
            st.executeUpdate(delete);
        }
        catch (Exception e){
            System.out.println("Delete part failed");
        }
        for(int i = 0; superCategoryArrayList.get(i) != null; i++){
            try {
                temp = superCategoryArrayList.get(i).getRelationshipPair();
                String query = "insert into superCategory (superCategory) values ('" + temp + "');";
                Statement st = conn.createStatement();
                st.executeUpdate(query);
            }
            catch (Exception r){
                System.out.println("Updating superCategory pairs failed");
            }
        }
    }

    //Gets the superCategory table from the database
    private ArrayList<String> getSuperCategory(){
        ArrayList<String> str = new ArrayList<>();
        try{
            String query = "select * from superCategory";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               str.add(rs.getString("superCategory"));
            }
        }
        catch (Exception e){
            System.out.println("Getting superCategory pairs failed");
        }
        return str;
    }

}

