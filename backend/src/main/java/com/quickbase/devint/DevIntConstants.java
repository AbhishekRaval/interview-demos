package com.quickbase.devint;

//Class for storing all the constants across devInt package.
    final class DevIntConstants {

    static String CONNECTION_CLASS = "org.sqlite.JDBC";

    static String CONNECTION_URL = "jdbc:sqlite:resources/data/citystatecountry.db";

    static String COUNTRY_COLUMN = "CountryName";

    static String POPULATION_COLUMN = "Population";

    static String DB_CONN_ERR = "Error Occurred while connecting database";

    static String DB_CONN_SUCCESS_MSG = "Opened database successfully";

    static String DB_CONN_DRIVERCLASS_ERR = "could not load driver";

    static String DB_CLOSE_CONN_ERR ="Error Occurred while closing connection";

    static  String DB_CLOSE_CONN_MSG = "Closing Database Connection";

    static String BOTH_DB_STATIC_EMPTY_LISTS_MSG = "Both Lists are empty, no records to display";

    static String DBLIST_EMPTY_MSG = "No records in db for country, population. Returning Static List.";

    static String STATICLIST_EMPTY_MSG = "No records in static List, returning database data.";
}
