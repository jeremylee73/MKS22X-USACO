import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class USACO{
  private static char[][] silverPasture;
  private static int[][] sums;

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
              if (i == line.length()-1){
                currentNum += line.charAt(i);
                pasture[bigCount][count] = Integer.parseInt(currentNum);
                count++;
                currentNum = "";
              } else if (line.charAt(i) == ' '){
                pasture[bigCount][count] = Integer.parseInt(currentNum);
                count++;
                currentNum = "";
              } else {
                currentNum += line.charAt(i);
              }
            }
            bigCount++;
          }
          else if (line.length() != 0){
            String[] commands = line.split(" ", -1);
            int row = Integer.parseInt(commands[0]);
            int col = Integer.parseInt(commands[1]);
            int depth = Integer.parseInt(commands[2]);
            int[][] moves = {{row, col}, {row+1, col}, {row-1, col}, {row, col+1}, {row+1, col+1}, {row-1, col+1}, {row, col-1}, {row+1, col-1}, {row-1, col-1}};
            int largest = 0;
            for (int i=0; i<moves.length; i++){
              if (moves[i][0] >= 0 && moves[i][0] < rows && moves[i][1] >= 0 && moves[i][1] < cols){
                if (pasture[moves[i][0]][moves[i][1]] > largest){
                  largest = pasture[moves[i][0]][moves[i][1]];
                }
              }
            }
            for (int i=0; i<moves.length; i++){
              if (moves[i][0] >= 0 && moves[i][0] < rows && moves[i][1] >= 0 && moves[i][1] < cols){
                if (pasture[moves[i][0]][moves[i][1]] > largest - depth){
                  pasture[moves[i][0]][moves[i][1]] = largest - depth;
                }
                if (pasture[moves[i][0]][moves[i][1]] < 0){
                  pasture[moves[i][0]][moves[i][1]] = 0;
                }
              }
            }
          }
      }
      int sumDepths = 0;
      for (int r=0; r<pasture.length; r++){
        for (int c=0; c<pasture[r].length; c++){
          System.out.print(pasture[r][c] + " ");
          if (pasture[r][c] < 22){
            sumDepths += 22 - pasture[r][c];
          }
        }
        System.out.println();
      }
      return 72 * 72 * sumDepths;
    } catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    return 0;
  }

  public static int silver(String filename){
    try {
      File text = new File(filename);
      Scanner inf = new Scanner(text);
      int rows = inf.nextInt();
      int cols = inf.nextInt();
      int steps = inf.nextInt();
      silverPasture = new char[rows][cols];
      sums = new int[rows][cols];
      for (int i=0; i<rows; i++){
        String line = inf.next();
        silverPasture[i] = line.toCharArray();
      }
      int r1 = inf.nextInt();
      int c1 = inf.nextInt();
      int r2 = inf.nextInt();
      int c2 = inf.nextInt();
    } catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    return 0;
  }


}
