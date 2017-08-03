package main;

import java.util.ArrayList;
import Parser.*;
import java.util.HashMap;
import java.util.Scanner;

class Main{
    
    String query;
    String curr_db = null;
    
    //Object of the parser
    ValidateQuery parser = new ValidateQuery();
  
    //Object of the file Manipulation
	//TODO
    //Object of printing
	//TODO
    
    public void Main(){
	//Welcome from the other side
    }
    
    private void getQuery(){
	
        System.out.print(">> ");
	//get the data from the user
	Scanner x = new Scanner(System.in);
	String query = x.nextLine();
	//assign the data to the class's var
	this.query = query;
    }
    
    //validate
    private boolean validate(){
	parser.setQuery(this.query);
	return parser.isValid();
    }
    
    //parse
    private ArrayList<String> getData(){
	
	try{
	    return parser.parseIt();
	    
	}
	catch(RuntimeException e){
	    return null;
	}
    }
    
    
    //execute
    private ArrayList<HashMap> executeQuery(ArrayList<String> data){
	//TODO
	
	//switch between the query types 
	switch(parser.getQueryType()){
	    case '1':
		return query1(data);
	    case '2':
		break;
	    case '3':
		break;
	    case '4':
		break;
	    case '5':
		break;
	    case '6':
		break;
	    case '7':
		break;
	    case '8':
		break;
	    case '9':
		break;	
	}
	return null;
    }
    
    private ArrayList<HashMap> query1 (ArrayList<String> data){
	//call for Abo bakr classes and method here
	//handle exception
	return null;
    }
    
    private void print(ArrayList<HashMap> x){
	//print from the printing class
	//TODO
	//this code with Khaled
    }
    
    private void debugList (ArrayList<String> x){
	System.out.println();
	for(int i=0;i<x.size();i++){
	    System.out.println(x.get(i));
	}
	System.out.println("---------------------------------");
    }
    
    public static void main(String args[]){
	
	Main start = new Main();
	
	while(true){
	    start.getQuery();
	    if(start.validate()){
		
		System.out.println("It's valid");
		
		ArrayList<String> data = start.getData();   //get the data
		System.out.println(data);
		start.debugList(data);	//show the data for debugging purposes
		ArrayList<HashMap> dataPrint = start.executeQuery(data);   //execute the query
		start.print(dataPrint);	//print the data to the screen
	    }else{
		System.out.println("it's not valid");
	    }
	}
    }
    
}