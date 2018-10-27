package com.quickbase.devint;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import static com.quickbase.devint.DevIntConstants.*;

public class DataOperationsImpl implements DataOperations {

    //Builds a HashMap<Key, Value> with country name as it's key and Value as it's total population
    //by querying database and requesting each row.
    @Override
    public Map<String, Integer> fetchPopulationListFromDb(String sqlOperationQuery,Connection connection) {
        Map<String, Integer> outputMap = new HashMap<String, Integer>();
        try {
            ResultSet resultSet = connection
                    .createStatement()
                    .executeQuery(sqlOperationQuery);
            while (resultSet.next()) {
                outputMap.put(resultSet.getString(COUNTRY_COLUMN),
                        resultSet.getInt(POPULATION_COLUMN));
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching the query " + e);
        }
        return outputMap;
    }

    private static List<Pair<String, Integer>> convertMapToPairList(Map<String, Integer> inputMap){
        List<Pair<String, Integer>> output = new ArrayList<>();
        inputMap.forEach((key, value) -> output.add(new ImmutablePair<>(key, value)));
        return output;
    }

    // Returns Combined Data by merging StaticData and the data fetched from List
    // Where, there is join in such a way that, the countries that doesn't exist in
    // db are added.
    @Override
    public List<Pair<String, Integer>> mergeStaticDataToDbData
            (Map<String,Integer> dbList, List<Pair<String, Integer>> staticList){

        Boolean dbListEmpty =  dbList == null || dbList.isEmpty();
        Boolean staticListEmpty = staticList == null || staticList.isEmpty();
        //If both lists are empty, we return empty list
        if(dbListEmpty && staticListEmpty){
            System.out.println(BOTH_DB_STATIC_EMPTY_LISTS_MSG);
            return Collections.emptyList();
        }
        // If dbList is empty, we'll return staticList
        else if(dbListEmpty){
            System.out.println(DBLIST_EMPTY_MSG);
            return staticList;
        }
        // If dbList is not empty and staticList is empty, we'll return dbList
        else if (staticListEmpty) {
            System.out.println(STATICLIST_EMPTY_MSG);
            return convertMapToPairList(dbList);
        }
        //When both lists are non empty
        else {
            //From each element in staticList, we select one pair at a time
            // if the country doesn't exist in dbList, we add pair to the dbList
            staticList.forEach(pair -> {
                if (!dbList.containsKey(pair.getKey())) {
                    dbList.put(pair.getKey(), pair.getValue());
                }
            });

            //Now since dbList has all the elements, we'll map through it
            //to return our output in the form of list<Pair<Key, Value>>
            //with the help of our helper function that would convert Map to List of Pairs
            return convertMapToPairList(dbList);
        }
    }
}
