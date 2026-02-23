package Project1;

import java.util.*;
/**
 *
 * 6713225 Chatchawal Labkim 
 * 6713232 Thanapon Rojanabenjakul 
 * 6713234 Thanawat Mekwattanawanit
 */
public class MainApplication {
    
    class marbleBoard {
        private String[] Board;
        private int num;
        private int emptyIndex;
        
        public marbleBoard(int n) {
            this.num = n;
            this.Board = new String[2 * n + 1];
            initialBoard();
        }

        public void initialBoard() {
            //white marble
            for (int i = 0; i < num; i++) {
                Board[i] = "w" + i;
            }

            //empty space
            emptyIndex = num;
            Board[emptyIndex] = "__";

            //black marble
            for (int i = 0; i < num; i++) {
                Board[num + 1 + i] = "b" + i;
            }
        }
        public void printBoard(){
            for(String n: Board){
              System.out.printf(n+' ');
            }
            System.out.println();
        }
        public boolean isGameOver() {
    
            for (int i = 0; i < num; i++) {
                 if (!Board[i].startsWith("b")) return false;
            }
             for (int i = num + 1; i < Board.length; i++) {
                 if (!Board[i].startsWith("w")) return false;
            }
            return true; 
        }
        
        public boolean move(String marbleId) {
            int currentIndex = -1;
            
            // check id & index 
            for (int i = 0; i < Board.length; i++) {
                if (Board[i].equals(marbleId)) {
                    currentIndex = i;
                    break;
                }
            }
            // if not found id
            if (currentIndex == -1  && !marbleId.equalsIgnoreCase("a")) {
                System.out.printf("%19s %s\n" ,"Not Found", marbleId);
                return false;
            }
            
            
            boolean canMove = false;
            String moveType= "";
            
            //white move
            if (marbleId.startsWith("w")) {
                //white move right
                if (currentIndex + 1 == emptyIndex) {
                    canMove = true;
                    moveType = "Move right";
                }
                //white jump over black
                else if (currentIndex + 2 == emptyIndex && Board[currentIndex + 1].startsWith("b")) {
                    canMove = true;
                    moveType = "Jump right";
                }
            }
            
            if (marbleId.startsWith("b")) {
                //black move left
                if (currentIndex - 1 == emptyIndex) {
                    canMove = true;
                    moveType = "Move left";
                }
                //black jump over white
                else if (currentIndex - 2 == emptyIndex && Board[currentIndex - 1].startsWith("w")) {
                    canMove = true;
                    moveType = "Jump left";
                }
            }
            
            if (canMove) {
                System.out.printf("%12s : %s\n",marbleId,moveType);
                
                //switch to empty 
                Board[emptyIndex] = Board[currentIndex];
                
                //switch empty to previous
                Board[currentIndex] = "__";
                
                emptyIndex = currentIndex;
                
                return true;
            } else {
                if(!marbleId.equalsIgnoreCase("a"))
                System.out.printf("%21s: %s\n" ,"Cannot move", marbleId);
                return false;
            }
        }
        
  }
    
    
    
        public void menu() {
        Scanner scan = new Scanner(System.in);
        int marble_num;
        do{
            
        do {
           System.out.printf("Enter number of white marbles = ");
            marble_num = scan.nextInt();
            
            if (marble_num >= 2) {
                System.out.print("Initial >> ");
                marbleBoard board=new marbleBoard(marble_num);
                board.printBoard();
                
                int step = 1;
                while (true) {
                    System.out.println("=".repeat(103));
                    System.out.print("Step " + step + " >> Enter marble ID or A to switch to auto mode = ");
                    String input = scan.next();
                    
                    if(board.move(input)) {
                    System.out.printf("%15s : ","Board");
                    board.printBoard();
                    }
                    step++;
                    if (input.equalsIgnoreCase("A")) {
                        System.out.println("Switching to Auto Mode...");
                         
                        break; 
                    }
                    
                    
                    if(board.isGameOver()){
                        System.out.println("=".repeat(103));
                        System.out.println("You win!");
                        
                        break;
                    }
                 }
            
            } else {
                System.out.println("n must be at least 2.");
            }
            
            } while (marble_num < 2);
        
            System.out.println("=".repeat(103));    
            System.out.println("Do you want to play again?(y/n)");
            
        }while(scan.next().equalsIgnoreCase("y"));
    }

    public static void main(String[] args) {
        MainApplication mainapp = new MainApplication();
        mainapp.menu();

    }
}