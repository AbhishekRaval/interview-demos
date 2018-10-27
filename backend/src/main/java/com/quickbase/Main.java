package com.quickbase;

import com.quickbase.devint.*;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

/**
 * The main method of the executable JAR generated from this repository. This is to let you execute something from the
 * command-line or IDE for the purposes of demonstration, but you can choose to demonstrate in a different way (e.g. if
 * you're using a framework)
 */
public class Main {

    public static String GET_ALL_COUNTRY_POPULATION_QUERY =
            "SELECT Country.CountryName, sum(city.Population) AS Population FROM Country, State, City " +
                    "WHERE Country.CountryId == State.CountryId AND State.StateId== City.StateId" +
                    " GROUP BY Country.CountryName";

    public static void main(String args[]) {

        System.out.println("Starting.");
        System.out.print("Getting DB Connection...");

        DBManager dbm = new DBManagerImpl();
        Connection c = dbm.getConnection();
        IStatService iStatService = new ConcreteStatService();
        DataOperationsImpl operations = new DataOperationsImpl();

        if (null == c) {
            System.out.println("failed.");
            System.exit(1);
        }

        List<Pair<String, Integer>> outputList = operations.
                mergeStaticDataToDbData(
                        operations.fetchPopulationListFromDb(GET_ALL_COUNTRY_POPULATION_QUERY, c),
                        iStatService.GetCountryPopulations());

        Collections.sort(outputList);
        System.out.println("||||||Printing Final Output List||||||||||");
        outputList.forEach(x -> System.out.println(x));
        System.out.println("|||||Finished Printing List |||||");
        System.out.println("The output List's size: " + outputList.size());
        dbm.closeConnection();
    }
}