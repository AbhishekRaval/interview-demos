package com.quickbase.devint;

import org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/***
 * Interface for merging a list generated from database with static list
 */
public interface DataOperations {

    //Method to fetch the List of Country and Population from DB
    public Map<String, Integer> fetchPopulationListFromDb(String sqlOperationQuery,Connection connection);

    //Method for merging the dblist to staticList
    public List<Pair<String, Integer>> mergeStaticDataToDbData(Map<String, Integer> dbList,
                                             List<Pair<String, Integer>> staticList);
}
