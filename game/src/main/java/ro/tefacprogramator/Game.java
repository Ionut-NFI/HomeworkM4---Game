package ro.tefacprogramator;

import java.util.ArrayList;
import java.util.Stack;

public class Game 
{
    public static void main( String[] args )
    {
        Play();
    }

    public static void Play(){
        Character ch1 = new Character();
        Character ch2 = new Character();
        FileMethods q = new FileMethods();
        CityMap cityMap = new CityMap();

        cityMap.setMapX(q.ReadRowFromFile());
        cityMap.setMapY(q.ReadColumnFromFile());

        char[] matrix1D = q.ReadOtherLineFromFile().toCharArray();

        cityMap.setMap(q.makeMatrix2D(matrix1D, cityMap));

        ch1.setMapPositionX(q.RPosition(matrix1D, cityMap)[0]);
        ch1.setMapPositionY(q.RPosition(matrix1D, cityMap)[1]);

        ch2.setMapPositionX(q.JPosition(matrix1D, cityMap)[0]);
        ch2.setMapPositionY(q.JPosition(matrix1D, cityMap)[1]);

        System.out.println("R position -> " + ch1.getMapPositionX() + " " + ch1.getMapPositionY());
        System.out.println("J position -> " + ch2.getMapPositionX() + " " + ch2.getMapPositionY());
        

        


        // print matrix 2D
        System.out.println("Row number -> " + q.ReadRowFromFile());
        System.out.println("Column number -> " + q.ReadColumnFromFile());
        for (int i = 0; i < cityMap.getMapX(); i++) {
            for (int j = 0; j < cityMap.getMapY(); j++) {
                System.out.print(cityMap.getMap()[i][j]);
            }
            System.out.println();
        }
        Logic(ch1, ch2, q, cityMap);
    }

    public static void Logic(Character ch1, Character ch2, FileMethods q, CityMap map) {
        System.out.println("Logic function");
        System.out.println();

        boolean[][] visitedMap = new boolean[q.ReadRowFromFile()][q.ReadColumnFromFile()];
        Stack xStack = new Stack();
        Stack yStack = new Stack();
        
        int[][] distanceMatrix = new int[q.ReadRowFromFile()][q.ReadColumnFromFile()];

        int min_dist = fiindMinDistance(map, visitedMap, ch1.getMapPositionX(), ch1.getMapPositionY(), ch2.getMapPositionX(), ch2.getMapPositionY(), Integer.MAX_VALUE, 0, distanceMatrix, xStack, yStack);
        
        
        if(min_dist != Integer.MAX_VALUE){
            System.out.println("The shortest distance is "+min_dist );

            for (int i = 0; i < q.ReadRowFromFile(); i++) {
                for (int j = 0; j < q.ReadColumnFromFile(); j++) {
                    System.out.print(distanceMatrix[i][j] + " ");
                }
                System.out.println();
            }
    
            if(Integer.parseInt(xStack.get(xStack.size()-1).toString())>0)
            {while( Integer.parseInt(xStack.get(xStack.size()-1).toString()) != ch2.getMapPositionX() && Integer.parseInt(xStack.get(xStack.size()-1).toString()) != ch2.getMapPositionX()){
                xStack.pop();
                yStack.pop();
            }}
      
        ArrayList v = new ArrayList(xStack);
        ArrayList w = new ArrayList(yStack);
    
        xStack.clear();
        yStack.clear();
        
            System.out.println("before      " +v);
            System.out.println("before      " +w);
    
            xStack.push(Integer.parseInt(v.get(v.size()-1).toString()));
            yStack.push(Integer.parseInt(w.get(w.size()-1).toString()));
            for(int i = v.size()-2; i>0 ; i--){
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
        }
        else{
            System.out.println("Destination can't be reached from source");
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
        
       //visitedMap[ch1X][ch1Y] = false;
    
        return min_dist;
    }
}