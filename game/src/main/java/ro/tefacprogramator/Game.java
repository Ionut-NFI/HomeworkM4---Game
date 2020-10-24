package ro.tefacprogramator;

import java.util.ArrayList;
import java.util.Stack;

public class Game 
{
    public static void main( String[] args )
    {
        String inputFile = "maze.in";
        String outputFile = "maze.out";
        
        Play(inputFile, outputFile);
    }

    public static void Play(String inputFile, String outputFile){
        Character ch1 = new Character();
        Character ch2 = new Character();
        FileMethods q = new FileMethods();
        CityMap cityMap = new CityMap();

        cityMap.setMapX(q.ReadRowFromFile(inputFile));
        cityMap.setMapY(q.ReadColumnFromFile(inputFile));

        char[] matrix1D = q.ReadOtherLineFromFile(inputFile).toCharArray();

        cityMap.setMap(q.makeMatrix2D(matrix1D, cityMap));

        ch1.setMapPositionX(q.RPosition(matrix1D, cityMap)[0]);
        ch1.setMapPositionY(q.RPosition(matrix1D, cityMap)[1]);

        ch2.setMapPositionX(q.JPosition(matrix1D, cityMap)[0]);
        ch2.setMapPositionY(q.JPosition(matrix1D, cityMap)[1]);
       
        Logic(inputFile, outputFile, ch1, ch2, q, cityMap);
    }

    public static void Logic(String inputFile, String outputFile, Character ch1, Character ch2, FileMethods q, CityMap map) {

        boolean[][] visitedMap = new boolean[q.ReadRowFromFile(inputFile)][q.ReadColumnFromFile(inputFile)];
        Stack xStack = new Stack();
        Stack yStack = new Stack();
        
        int[][] distanceMatrix = new int[q.ReadRowFromFile(inputFile)][q.ReadColumnFromFile(inputFile)];

        int min_dist = fiindMinDistance(map, visitedMap, ch1.getMapPositionX(), ch1.getMapPositionY(), ch2.getMapPositionX(), ch2.getMapPositionY(), Integer.MAX_VALUE, 0, distanceMatrix, xStack, yStack);
        
        
        if(min_dist != Integer.MAX_VALUE){  

            for (int i = 0; i < q.ReadRowFromFile(inputFile); i++) {
                for (int j = 0; j < q.ReadColumnFromFile(inputFile); j++) {
                    System.out.print(distanceMatrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("R-> " +ch1.getMapPositionX() + " " + ch1.getMapPositionY());
            System.out.println("J-> " +ch2.getMapPositionX() + " " + ch2.getMapPositionY());

        int count = 0;
        for(int i =0; i< xStack.size();i++){
            if((Integer.parseInt(xStack.get(i).toString()) == ch2.getMapPositionX()) && (Integer.parseInt(yStack.get(i).toString()) == ch2.getMapPositionY()))
            count ++; 
        }
        if(count >=2){
            for(int i =1; i< xStack.size()-1;i++){
                if((Integer.parseInt(xStack.get(i).toString()) == ch2.getMapPositionX()) && (Integer.parseInt(yStack.get(i).toString()) == ch2.getMapPositionY())){
                    xStack.setSize(i+1);
                    yStack.setSize(i+1);
                    distanceMatrix[Integer.parseInt(xStack.get(i).toString())][Integer.parseInt(yStack.get(i).toString())] =distanceMatrix[Integer.parseInt(xStack.get(i-1).toString())][Integer.parseInt(yStack.get(i-1).toString())] +1;
                }
            }
        }
        System.out.println(xStack);
        System.out.println(yStack);
        for (int i = 0; i < q.ReadRowFromFile(inputFile); i++) {
            for (int j = 0; j < q.ReadColumnFromFile(inputFile); j++) {
                System.out.print(distanceMatrix[i][j] + " ");
            }
            System.out.println();
        }
        while(Integer.parseInt(xStack.get(xStack.size()-1).toString()) != ch2.getMapPositionX() || Integer.parseInt(yStack.get(yStack.size()-1).toString()) != ch2.getMapPositionY())
        {
        xStack.pop();
        yStack.pop();
        }
      
        ArrayList v = new ArrayList(xStack);
        ArrayList w = new ArrayList(yStack);

        xStack.clear();
        yStack.clear();
           
        xStack.push(Integer.parseInt(v.get(v.size()-1).toString()));
        yStack.push(Integer.parseInt(w.get(w.size()-1).toString()));

        for(int i = v.size()-1; i>0 ; i--){
            int x = Integer.parseInt(v.get(i).toString());
            int y = Integer.parseInt(w.get(i).toString());
            int l = Integer.parseInt(xStack.lastElement().toString());
            int k = Integer.parseInt(yStack.lastElement().toString());

            if( distanceMatrix[x][y] == distanceMatrix[l][k]-1 ){
        
                xStack.push(Integer.parseInt(v.get(i).toString()));
                yStack.push(Integer.parseInt(w.get(i).toString()));
            }
        }
            xStack.push(Integer.parseInt(v.get(0).toString()));
            yStack.push(Integer.parseInt(w.get(0).toString()));
            
        System.out.println(xStack); 
        System.out.println(yStack);

        int a= Integer.parseInt(xStack.get(xStack.size()/2).toString())+1 ;
        int b = Integer.parseInt(yStack.get(yStack.size()/2).toString())+1;
        System.out.println("Location-> " +a + " , "+ b);

        String result = min_dist/2 + " "+ a + " " + b; 
        q.WriteResultInFile(outputFile,result); //write in file the result
        }
        else{
            String result = "Destination can't be reached.";
            q.WriteResultInFile(outputFile, result); //write in file the result
        }
      
    }
    public static boolean isSafe(CityMap map, boolean visitedMap[][], int x, int y) {

        return !(map.getMap()[x][y] == '#' || visitedMap[x][y] == true );
    }

    public static boolean isValid(int x, int y, CityMap map){
        return (x < map.getMapX() && y <map.getMapY() && x >=0 && y>=0); 
    }

    public static int fiindMinDistance(CityMap map,boolean visitedMap[][], int ch1X, int ch1Y, int ch2X, int ch2Y, int min_dist,int dist, int distanceMatrix[][],Stack xStack, Stack yStack){
        
        distanceMatrix[ch1X][ch1Y] = dist;
      
        xStack.push(ch1X);
        yStack.push(ch1Y);
    
        if( ch1X == ch2X && ch1Y == ch2Y){
            return Integer.min(dist, min_dist); //if source == destination 
        }

        visitedMap[ch1X][ch1Y] = true; //set visitedMap true for visited cell
       
        //go to bottom cell
        if(isValid(ch1X+1,ch1Y,map) && isSafe(map, visitedMap, ch1X+1, ch1Y)){
            min_dist = fiindMinDistance(map, visitedMap, ch1X+1, ch1Y, ch2X, ch2Y, min_dist, dist+1, distanceMatrix, xStack, yStack);
        }
        
        //go to right cell
        if(isValid(ch1X,ch1Y+1,map) && isSafe(map, visitedMap, ch1X, ch1Y+1)){
            min_dist = fiindMinDistance(map, visitedMap, ch1X, ch1Y+1, ch2X, ch2Y, min_dist, dist+1, distanceMatrix, xStack, yStack);
        }
       //go to top cell
       if(isValid(ch1X-1, ch1Y, map) && isSafe(map, visitedMap,ch1X-1,ch1Y)){
        min_dist = fiindMinDistance(map, visitedMap, ch1X-1, ch1Y, ch2X, ch2Y, min_dist, dist+1, distanceMatrix, xStack, yStack);
        }
        // go to left cell
        if(isValid(ch1X,ch1Y-1,map) && isSafe(map, visitedMap,ch1X,ch1Y-1)){
            min_dist = fiindMinDistance(map, visitedMap, ch1X, ch1Y-1, ch2X, ch2Y, min_dist, dist+1, distanceMatrix, xStack, yStack);
        }

        return min_dist;
    }
}