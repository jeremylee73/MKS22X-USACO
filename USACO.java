import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class USACO{
  public static int bronze(String filename){
    int[][] pasture;
    int rows;
    int cols;
    int finalElevation;
    int command;
    try {
      File text = new File(filename);
      Scanner inf = new Scanner(text);
      rows = Integer.parseInt(inf.next());
      cols = Integer.parseInt(inf.next());
      finalElevation = Integer.parseInt(inf.next());
      command = Integer.parseInt(inf.next());
      pasture = new int[rows][cols];
      int bigCount = 0;
      while(inf.hasNextLine()){
          String line = inf.nextLine();
          if (line.length() == (cols*2) + (cols-1)){
            String currentNum = "";
            int count = 0;
            for (int i=0; i<line.length(); i++){
              if (line.charAt(i) == ' '){
                pasture[bigCount][count] = Integer.parseInt(currentNum);
                count++;
                currentNum = "";
              } else {
                currentNum += line.charAt(i);
              }
            }
            bigCount++;
          }
      }
      for (int row=0; row<pasture.length; row++){
        for (int col=0; col<pasture[row].length; col++){
          System.out.print(pasture[row][col] + " ");
        }
        System.out.println();
      }
    } catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    return 0;
  }

  public static int silver(String filename){
    return 0;
  }


}
