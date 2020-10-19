package ro.tefacprogramator;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class FileMethods {

  public String ReadFirstLineFromFile() {
    
    String data = "";
  
    try {
      File myObj = new File("maze.in");
      Scanner myReader = new Scanner(myObj);
      
      data = myReader.nextLine();
      myReader.close();
    } 
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    return data;
  }

  public int ReadRowFromFile(){
    String line = ReadFirstLineFromFile();

    return Integer.parseInt ( line.substring(0, line.indexOf(" ")));
  }

  public int ReadColumnFromFile(){
    String line = ReadFirstLineFromFile();
    
    return Integer.parseInt ( line.substring(line.indexOf(" ")+1, line.length()));
  }

  public String ReadOtherLineFromFile() {
    
    String data = "";
  
    try {
      File myObj = new File("maze.in");
      Scanner myReader = new Scanner(myObj);
      myReader.nextLine();
      while(myReader.hasNextLine()){
        
        data += myReader.nextLine();
      }
      
      myReader.close();
    } 
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    System.out.print(data);
    return data;
  }
  

}