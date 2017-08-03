package ParserQueries;

import ParserQueriesOptional.WhereOrderLimitData;
import java.util.ArrayList;
import java.util.Map;

public interface SingleQuery{
    
    //set the query
    public void setQuery(String query);
    
    //check if the statement is valid or not
    public boolean isValid();
    
    //return the data of the parsed sentence
    public ArrayList<String> parseIt();
    
    //get the id of the Operation
    public int getId();
    
    //for optional parameters
    public WhereOrderLimitData whereOrderLimit();
}