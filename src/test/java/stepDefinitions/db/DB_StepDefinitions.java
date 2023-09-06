package stepDefinitions.db;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.ConfigReader;
import utilities.DB_ReusableMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static utilities.DB_ReusableMethods.getStatement;

public class DB_StepDefinitions {
    ResultSet rs;
    List<Object[]> itemList = new ArrayList<>();
    int result;

    @Given("Database connection is established")
    public void database_connection_is_established() {

        DB_ReusableMethods.createConnection();
    }
    @Given("Query is prepared, executed and results are collected {string}")
    public void query_is_prepared_executed_and_results_are_collected(String query) throws SQLException {
        rs = getStatement().executeQuery(ConfigReader.getProperty(query));
    }

    @Given("Results for US16 are verified")
    public void results_for_us16_are_verified() throws SQLException {
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("firstname");
            Object[] item = {id, name};
            itemList.add(item);
        }

        Assert.assertEquals(10, itemList.size());

        for (Object[] item : itemList) {
            System.out.println("id: " + item[0] + ", name: " + item[1]);
        }
    }

    @Given("Database connection is closed")
    public void database_connection_is_closed() {
        DB_ReusableMethods.closeConnection();
    }

    @Given("Results for US17 are verified")
    public void results_for_us17_are_verified() throws SQLException {
        // 34.38461538461539

        double expectedValue = 34.91428571428571;
        double actualValue = 0;
        double delta = 0.0000000001;
        
        if (rs.next()) {
            actualValue = rs.getDouble("average_passing_percentage");
        }

        System.out.println(actualValue);

        Assert.assertEquals(expectedValue, actualValue, delta);
    }

    @Given("Results for US18 are verified")
    public void results_for_us18_are_verified() throws SQLException {
        // 266

        int expected = 276;
        int actual = 0;

        if (rs.next()){
            actual = rs.getInt("student_count");
        }

        System.out.println(actual);

        Assert.assertEquals(expected, actual);
    }


    @Given("A requested data is deleted from the visitors_book table")
    public void a_requested_data_is_deleted_from_the_visitors_book_table() throws SQLException {
        DB_ReusableMethods.getStatement().executeUpdate(ConfigReader.getProperty("db_us10_query_create"));
        result  = DB_ReusableMethods.getStatement().executeUpdate(ConfigReader.getProperty("db_us10_query"));
    }
    @Given("The deletion of a requested data from the visitors_book table is confirmed")
    public void the_deletion_of_a_requested_data_from_the_visitors_book_table_is_confirmed() {

        int expectedData = 1;

        int verify = 0 ;

        if (result > 0){
            verify++;
        }

        Assert.assertEquals(expectedData, verify);


    }

    @Given("The fine_amount value of the record in the transport_feemaster table with a month value of October is updated to {double}")
    public void the_fine_amount_value_of_the_record_in_the_transport_feemaster_table_with_a_month_value_of_october_is_updated_to(Double double1) throws SQLException {

        result = DB_ReusableMethods.getStatement().executeUpdate(ConfigReader.getProperty("db_us11_query"));

    }
    @Given("The update in the transport_feemaster table is verified")
    public void the_update_in_the_transport_feemaster_table_is_verified() {

        int expectedData = 1;

        int verify = 0;

        if (result > 0){
            verify++;
        }

        Assert.assertEquals(expectedData,verify);

    }

    @Given("The first five employees in the staff table are listed, sorted based on their work_exp value in ascending order from oldest to newest")
    public void the_first_five_employees_in_the_staff_table_are_listed_sorted_based_on_their_work_exp_value_in_ascending_order_from_oldest_to_newest
            () throws SQLException {

        rs = DB_ReusableMethods.getStatement().executeQuery(ConfigReader.getProperty("db_us12_query"));

        Set<String> workExpSet = new TreeSet<>();

        int count = 0;

        while (rs.next()){

            String workExp = rs.getString("work_exp");
            String digitOnly = workExp.replaceAll("[^0-9]","");
            workExpSet.add(digitOnly);
            count++;

            if (count==5){
                break;
            }

        }

        List<String> workExpList = new ArrayList<>();

        Iterator<String> workExpIterator = workExpSet.iterator();

        while (workExpIterator.hasNext()){

            String list = workExpIterator.next();

            workExpList.add(list);

        }

        List<Integer> expectedData = new ArrayList<>();

        expectedData.add(18);
        expectedData.add(15);
        expectedData.add(12);
        expectedData.add(6);
        expectedData.add(3);

        List<Integer> actualData = new ArrayList<>();

        for (int i = workExpList.size()-1; i >=0 ; i--) {

            Integer ectualDataInt = Integer.parseInt(workExpList.get(i));

            actualData.add(ectualDataInt);
        }

        for (int i = 0; i < workExpList.size(); i++) {

            System.out.println(expectedData.get(i)+ "     " + actualData.get(i));

            Assert.assertEquals(expectedData.get(i),actualData.get(i));

        }

    }

    public static void main(String[] args) {

        Set<String> workExpSet = new TreeSet<>();

        workExpSet.add("aziz");
        workExpSet.add("eksik");


        List<String> workExpList = new ArrayList<>();

        Iterator<String> workExpIterator = workExpSet.iterator();

        while (workExpIterator.hasNext()){

            String list = workExpIterator.next();

            workExpList.add(list);

        }



    }

    @Given("Verify results for {string}")
    public void us_07_08_09(String userStory) throws SQLException {


        if (userStory.equals("US_07")){

            int id=0;
            int flag=0;
            while (rs.next()){
                if (rs.getInt("id")>id && rs.getString("role").equals("parent")) {
                    System.out.println("Verified");
                } else {
                    flag++;
                }

                id=rs.getInt("id");
            }

            Assert.assertEquals(flag,0);

        }

        if (userStory.equals("US_08")){

            rs=  getStatement().executeQuery("SELECT * FROM topic");

            while (rs.next()){

                if (rs.getInt("id")==randomId){
                    System.out.println(randomId);
                    System.out.println(rs.getInt("id"));
                    System.out.println("expected name : "+ randomName);
                    System.out.println("actual data : "+ rs.getString("name"));
                    Assert.assertEquals(randomName,rs.getString("name"));

                    break;}

            }


        }

        if (userStory.equals("US_09")){
            rs=  getStatement().executeQuery("SELECT * FROM transport_route");

            while (rs.next()){

                if (rs.getInt("id")==randomId){
                    System.out.println(randomId);
                    System.out.println(rs.getInt("id"));
                    System.out.println("expected name : "+ randomName);
                    System.out.println("actual data : "+ rs.getString("route_title"));
                    Assert.assertEquals(randomName,rs.getString("route_title"));
                    break;}

            }


        }


    }



    String randomName; int randomId;
    @Given("Prepare query and execute UPDATE for US_08")
    public void query_is_prepared_executed_and_results_are_collecte() throws SQLException {

        // Select topic list

         rs=  getStatement().executeQuery("SELECT * FROM topic");

         // Prepare random name list
        List<String> rndNameList=new ArrayList<>();
        rndNameList.add("Math");
        rndNameList.add("Animals");
        rndNameList.add("Planets");
        rndNameList.add("Politics");
        rndNameList.add("Human Rights");
        rndNameList.add("First Aid");
        Random rnd= new Random();

        // Prepare Random id list

        List<Integer> idList= new ArrayList<>();

        while (rs.next() && rs.getInt("id")<70){
            idList.add(rs.getInt("id"));

        }

        randomName= rndNameList.get(rnd.nextInt(rndNameList.size()));
        randomId= idList.get(rnd.nextInt(idList.size()));

        String query= "UPDATE topic SET name='"+randomName+"' WHERE id="+randomId;

        int res = getStatement().executeUpdate(query);

        System.out.println(randomId+" "+randomName);


    }

    @Given("Prepare query and execute INSERT for US_09")
    public void query_is_prepared_executed_and_results_are_collect() throws SQLException {
        Random rnd= new Random();

        List<String> rndRouteList= new ArrayList<>();
        rndRouteList.add("Paris");
        rndRouteList.add("Hürriyet");
        rndRouteList.add("Viyana");
        rndRouteList.add("Çinçin");

        randomName= rndRouteList.get(rnd.nextInt(rndRouteList.size()));
        randomId= rnd.nextInt(1000);


      String query=  "INSERT INTO transport_route (id, route_title, no_of_vehicle,note,is_active) VALUES ("+randomId+",'"+randomName+"',null,null,0)";

      int res=getStatement().executeUpdate(query);

    }





    @Then("Database connection is established.")
    public void databaseConnectionIsEstablished() {
        DB_ReusableMethods.createConnection();
    }


    @Then("{string} The query is run and the results are retrieved.")
    public void theQueryIsRunAndTheResultsAreRetrieved(String query) throws SQLException {

        rs= getStatement().executeQuery(ConfigReader.getProperty(query));
    }

    @Then("US05 Query results are validated")
    public void us05_query_results_are_validated() throws SQLException {
        while (rs.next()) {

            String motherName = rs.getString("mother_name");
            String motherOccupation = rs.getString("mother_occupation");

            System.out.println("Mother Name:   " + motherName + ", Mother Occupation:   " + motherOccupation);
        }

    }

    @Given("Result of US13 query")
    public void result_of_us13_query() throws SQLException {
        while (rs.next()) {
            String email = rs.getString("email");
            System.out.println(email);
        }

    }
    @Given("Result of US14 query")
    public void result_of_us14_query() throws SQLException {

        String expected = "Every Drop Counts";
        String actual = null;

        if (rs.next()){
            actual = rs.getString("book_title");
        }

        System.out.println(actual);

        Assert.assertEquals(expected, actual);

    }

    @Given("Result of US15 query")
    public void result_of_us15_query() throws SQLException {
        while (rs.next()) {
            String bookTitle = rs.getString("book_title");
            System.out.println(bookTitle);
        }

    }

    //DB_US25
    @Given("Result of db_us25 query")
    public void result_of_db_us25_query() throws SQLException {
        while(rs.next()){
            int id= rs.getInt("id");
            String entryDate= rs.getString("entrydt");
            System.out.println("id: " + id + " ---> " + "EntryDate: "+ entryDate);
        }
        if (!rs.next()){
            System.out.println("No data for the query");
        }
    }
        // DB_US26
    @Given("Result of db_us26 query")
    public void result_of_db_us26_query() throws SQLException {
        while (rs.next()){
           int admissionNo= rs.getInt("admission_no");
           String firstname= rs.getString("firstname");
           String lastname= rs.getString("lastname");
            System.out.println("Admission_no: "+ admissionNo + " --> " + "FirstName: "+ firstname + " -->" +"LastName: "+ lastname);
        }
        if (!rs.next()){
            System.out.println("No data for the query");
        }
    }

// DB_27
@Given("Result of db_us27 query")
public void result_of_db_us27_query() throws SQLException {
   while (rs.next()){
     String firstname=  rs.getString("firstname");
     String lastName= rs.getString("lastname");
     System.out.println("Firstname: " + firstname +"--> " + "LastName: " + "--> " +lastName );
   }
    if (!rs.next()){
        System.out.println("No data for the query");
    }
}

    @Then("US04 Query results are validated")
    public void us04QueryResultsAreValidated() throws SQLException {
        while (rs.next()) {

            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");

            System.out.println("Firstname:  " + firstname + ", Lastname:  " + lastname);
        }
    }

    @Then("US06 Query results are validated")
    public void us06QueryResultsAreValidated() throws SQLException {
        while (rs.next()) {

            String roll_no = rs.getString("roll_no");

            System.out.println("roll_no:  " + roll_no);
        }
    }
}
