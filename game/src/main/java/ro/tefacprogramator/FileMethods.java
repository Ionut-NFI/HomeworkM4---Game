package ro.tefacprogramator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack; 

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
    
    return data;
  }
  
  public char[][] makeMatrix2D(char[] matrix1D, CityMap cityMap) {
    char [][] tempMap = new char[cityMap.getMapX()][cityMap.getMapY()];
    int count = 0;
    for(int i = 0; i < cityMap.getMapX();i++){
    for(int j = 0; j< cityMap.getMapY();j++){
        tempMap[i][j] = matrix1D[count];
        count++;
    }
    }
  return tempMap;
  }

 public int[] RPosition(char[] matrix1D, CityMap cityMap){
  int[] position = new int[2];
  char[][]tempMatrix = makeMatrix2D(matrix1D,cityMap);
  
  for(int i = 0; i<cityMap.getMapX(); i++){
    for(int j = 0; j<cityMap.getMapY(); j++){
      if( tempMatrix[i][j] == 'R'){
        position[0] = i;
        position[1] = j;
      }
    }
  }
  return position;
 }

 public int[] JPosition(char[] matrix1D, CityMap cityMap){
  int[] position = new int[2];
  char[][]tempMatrix = makeMatrix2D(matrix1D,cityMap);
  
  for(int i = 0; i<cityMap.getMapX(); i++){
    for(int j = 0; j<cityMap.getMapY(); j++){
      if( tempMatrix[i][j] == 'J'){
        position[0] = i;
        position[1] = j;
      }
    }
  }
  return position;
 }
 
public void WriteResultInFile(String result){
  try {
    FileWriter myObj = new FileWriter("maze.out");
    
    
    myObj.write(result);
    
    myObj.close();
  } 
  catch (IOException e) {
    System.out.println("An error occurred.");
    e.printStackTrace();
  }

  System.out.println("The result was written to the file");
}
}