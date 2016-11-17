import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Advanced Task 1 form here: https://cumoodle.coventry.ac.uk/pluginfile.php/1511361/mod_resource/content/0/Week%204%20Coursework%20Labsheet.pdf
 * Takes input like (where matrix is nXm and the user starts at i,j. And the uses 1's for a passage 0's for somewhere thats imparsable.) :
 * n
 * m
 * i
 * j
 * exitX
 * exitY
 * {rows of matrix}...
 *
 * Example input:
 * 3
 * 3
 * 1
 * 1
 * 2
 * 1
 * 000
 * 011
 * 000
 */
public class Main {
    private static int i, j, n, m, exitX, exitY;
    private static List<List<Integer>> maze = new ArrayList<>();
    private static List<Position> placesBeen = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //get all of the basic variables
        n = scanner.nextInt();
        m = scanner.nextInt();
        i = scanner.nextInt();
        j = scanner.nextInt();
        exitX = scanner.nextInt();
        exitY = scanner.nextInt();
        //this is required as it returns ""
        scanner.nextLine();
        //for each line up to m split in to chars, convert them to ints then add them to the maze list
        int count = 0;
        while (count < m) {
            count++;
            List<Integer> row = new ArrayList<>();
            String in = scanner.nextLine();
            for (String s : in.split("")) {
                row.add(Integer.parseInt(s));
            }
            maze.add(row);
        }

        //attempt to find all paths intill we have found them all
        boolean out = true;
        while (out){
            out = recessive(new Position(j ,i));
        }

    }

    /**
     * This will navigate the maze
     * @param current the current potion in the maze
     * @return boolean, false if we cant find an exit (meaning they have all been found or we cant reach it)
     */
    public static boolean recessive(Position current) {
        int currentX = current.getX();
        int currentY = current.getY();

        //base case check if we at the exit
        if (currentX == exitX && currentY == exitY) {
            System.out.println(currentX + " " + currentY);
            return true;
        }

        placesBeen.add(current);

        //now try to find where to go next
        //try eatch of the 4 adjacent tiles, check we have not been down them and that we can go down them
        Position upOne = new Position(currentY + 1, currentX);
        if (upOne.getY() < m && !placesBeen.contains(upOne) && maze.get(currentY + 1).get(currentX) == 1) {
            if (recessive(upOne)) {
                System.out.println(currentX + " " + currentY);
                return true;
            }else{
                placesBeen.remove(upOne);
            }
        }

        Position downOne = new Position(currentY - 1, currentX);
        if (downOne.getY() >= 0 && !placesBeen.contains(downOne) && maze.get(currentY - 1).get(currentX) == 1) {
            if (recessive(downOne)) {
                System.out.println(currentX + " " + currentY);
                return true;
            }else{
                placesBeen.remove(downOne);
            }
        }

        Position rightOne = new Position(currentY, currentX + 1);
        if (rightOne.getX() < n && !placesBeen.contains(rightOne) && maze.get(currentY).get(currentX + 1) == 1) {
            if (recessive(rightOne)) {
                System.out.println(currentX + " " + currentY);
                return true;
            }else{
                placesBeen.remove(rightOne);
            }
        }

        Position leftOne = new Position(currentY, currentX - 1);
        if (leftOne.getX() >= 0 && !placesBeen.contains(leftOne) && maze.get(currentY).get(currentX - 1) == 1) {
            if (recessive(leftOne)) {
                System.out.println(currentX + " " + currentY);
                return true;
            }else{
                placesBeen.remove(leftOne);
            }
        }

        return false;
    }

    /**
     * this is a class used to store X and Y positions
     */
    private static class Position{
        private int x;
        private int y;

        public Position() {
        }

        public Position(int y, int x) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (x != position.x) return false;
            return y == position.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}


/*




 */