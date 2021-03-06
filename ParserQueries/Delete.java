package ParserQueries;

import ParserQueriesOptional.WhereOrderLimit;
import ParserQueriesOptional.WhereOrderLimitData;
import ParserQueriesOptional.WhereOrderLimitOperations;
import ParserTools.Error;
import ParserTools.TextTools;
import java.util.ArrayList;
import java.util.HashMap;

public class Delete implements SingleQuery{
    
    private Error error;
    private TextTools text;
    private String query;
    private int stat;   //the stat of the query //zero for not prepared //one for prepared
    private String arr[];
    private ArrayList<String> parameters;
    private HashMap<String,String> cotHash;
    private int id;
    private int lastPos;
    
    public Delete(){
	this.error = new Error();
	this.parameters = new ArrayList<String>();
	this.stat = 0;
	this.text = new TextTools();
	this.cotHash = new HashMap<>();
	this.id = 8;
    }
    
    @Override
    public int getId(){
	return id;
    }
    
    public WhereOrderLimitData whereOrderLimit(){
	if(stat != 3)
	    throw new RuntimeException("the query must be valid first");
	
	WhereOrderLimitOperations wol = new WhereOrderLimit();
	wol.SetQuery(query);
	wol.execute(this.lastPos+1);
	wol.setHash(cotHash);

	return (WhereOrderLimitData) wol;
    }
    
    private void initialize(){
	this.error = new Error();
	this.parameters = new ArrayList<String>();
	this.stat = this.stat >1 ?1:0;
    }
    
    @Override
    public void setQuery(String query){
	
	//initialize the variables
	initialize();	
	
	//remove multiple spaces or tabs
	this.query = text.trim(query.trim());
	this.query = text.hashQuot(cotHash, this.query);

	arr = this.query.split(" ");
	stat = 1;
    }
    
    @Override
    public boolean isValid(){
	
	if(stat == 0)
	    return false;
	
	//initialize the variables
	initialize();
	
	//start the process steps
	try{
	    validateDelete();
	}
	catch(ArrayIndexOutOfBoundsException e){
	    error.reportError("Inclompete query");
	    stat = 2;
	    return false;
	}
	catch(StringIndexOutOfBoundsException e){
	    error.reportError("Inclompete query");
	    stat = 2;
	    return false;
	}
	//check if it's valid or not
	if(error.isError()){
	    stat = 2;
	    return false;
	}else{
	    stat = 3;
	    return true;
	}
    }
    
    @Override
    public ArrayList<String> parseIt(){
	
	//replace the hashValues
	for(int i = 0;i<parameters.size();i++)
	    parameters.set(i, text.replaceHashQuot(parameters.get(i), cotHash));
	
	if(stat == 3)
	    return this.parameters;
	else
	    throw new RuntimeException("can't parse invalid query");
    }
    
    private void validateDelete() throws ArrayIndexOutOfBoundsException , StringIndexOutOfBoundsException{
	if(arr[0].toLowerCase().equals("delete"))
	    if(arr[1].toLowerCase().equals("from"))
		stage7(2);
	    else
		error.reportError();
	else
	    error.reportError();
    }
    
    private void stage7(int pos) throws ArrayIndexOutOfBoundsException , StringIndexOutOfBoundsException{
	    parameters.add(arr[pos]);
	    lastPos = pos;
    }

    
    public static void main(String args[]){
	SingleQuery x = new Delete();
	x.setQuery("Delete from xxx;");
	System.out.println(x.isValid());
	ArrayList<String> y = x.parseIt();
	System.out.println(y);
    }
}
