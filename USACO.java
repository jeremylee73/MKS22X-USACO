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
      while(inf.hasNextLine()){
          String line = inf.nextLine();
          System.out.println(line);
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
