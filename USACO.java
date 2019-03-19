import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class USACO{
  private static char[][] silverPasture;
  private static int[][] paths;
  private static int rows;
  private static int cols;
  private static int[][] numPasture;

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
          String[] commands = line.split(" ", -1);
          if (commands.length > 3){
            for (int i=0; i<cols; i++){
              pasture[bigCount][i] = Integer.parseInt(commands[i]);
            }
            bigCount++;
          }
          else if (line.length() != 0){
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
          //System.out.print(pasture[r][c] + " ");
          if (pasture[r][c] < 22){
            sumDepths += 22 - pasture[r][c];
          }
        }
        //System.out.println();
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
      rows = inf.nextInt();
      cols = inf.nextInt();
      int steps = inf.nextInt();
      silverPasture = new char[rows][cols];
      paths = new int[rows][cols];
      numPasture = new int[rows][cols];
      for (int i=0; i<rows; i++){
        String line = inf.next();
        silverPasture[i] = line.toCharArray();
      }
      int r1 = inf.nextInt();
      int c1 = inf.nextInt();
      int r2 = inf.nextInt();
      int c2 = inf.nextInt();
      for (int row=0; row<rows; row++){
        for (int col=0; col<cols; col++){
          if (row == r1-1 && col == c1 - 1){
            numPasture[row][col] = 1;
          } else if (silverPasture[row][col] == '.'){
            numPasture[row][col] = 0;
          } else {
            numPasture[row][col] = -1;
          }
        }
      }
      return silverH(r1-1, c1-1, r2-1, c2-1, steps);
    } catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    return 0;
  }

  private static int silverH(int r1, int c1, int r2, int c2, int steps){
    for (int step=0; step<steps; step++){
      for (int row=0; row<rows; row++){
        for (int col=0; col<cols; col++){
          int total = 0;
          if (row > 0 && numPasture[row-1][col] != -1){
            total += numPasture[row-1][col];
          }
          if (col > 0 && numPasture[row][col-1] != -1){
            total += numPasture[row][col-1];
          }
          if (row < rows - 1 && numPasture[row+1][col] != -1){
            total += numPasture[row+1][col];
          }
          if (col < cols - 1 && numPasture[row][col+1] != -1){
            total += numPasture[row][col+1];
          }
          paths[row][col] = total;
        }
      }
      for (int i=0; i<paths.length; i++){
        for (int j=0; j<paths[i].length; j++){
          if (numPasture[i][j] != -1){
            numPasture[i][j] = paths[i][j];
            paths[i][j] = 0;
          }
        }
      }
    }
    return numPasture[r2][c2];
  }

  public static void main(String[] args) {
    USACO u = new USACO();
    System.out.println(USACO.silver(args[0]));
  }
}
