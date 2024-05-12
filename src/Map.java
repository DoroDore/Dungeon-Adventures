import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Random;
public class Map {
    private char[][] map;
    private int[][] playerCoordinate;
    public void generateMap() {
        Random rand = new Random();
        int rows = rand.nextInt(7) + 7;
        int columns = rand.nextInt(7) + 7;
        this.map = new char[rows][columns];

        // Add perimeter walls
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || i == rows - 1 || j == 0 || j == columns - 1) {
                    this.map[i][j] = 'W'; // Wall
                } else {
                    // Generate random character for the tile
                    char[] tileTypes = {'O', 'F', 'E', 'L', 'W'};
                    char randomChar = tileTypes[rand.nextInt(tileTypes.length)];
                    this.map[i][j] = randomChar;
                }
            }
        }
        int exitRow = -1;
        int exitColumn = -1;
        while (exitRow == -1 || exitColumn == -1 || isSurroundedByWalls(exitRow, exitColumn)) {
            exitRow = rand.nextInt(rows - 2) + 1;
            exitColumn = rand.nextInt(columns - 2) + 1;
        }
        this.map[exitRow][exitColumn] = 'X'; // Exit tile
    }
    /** Check if a tile is surrounded by walls */
    private boolean isSurroundedByWalls(int row, int column) {
        return this.map[row - 1][column] == 'W' && this.map[row + 1][column] == 'W' &&
                this.map[row][column - 1] == 'W' && this.map[row][column + 1] == 'W';
    }
    public static void main(String[] args) throws IOException, ParseException {
        Tiles.createTiles(Main.readFile("./src/data/Tiles.json"));
        Map myMap = new Map();
        myMap.generateMap();
        myMap.displayMap();
    }
    public void displayMap() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                switch (aChar) {
                    case 'W':
                        System.out.print(ConsoleColors.RESET + aChar + " ");
                        break;
                    case 'O':
                        System.out.print(ConsoleColors.WHITE + aChar + " ");
                        break;
                    case 'F':
                        System.out.print(ConsoleColors.CYAN + aChar + " ");
                        break;
                    case 'L':
                        System.out.print(ConsoleColors.YELLOW + aChar + " ");
                        break;
                    case 'E':
                        System.out.print(ConsoleColors.GREEN + aChar + " ");
                        break;
                    case 'X':
                        System.out.print(ConsoleColors.RED + aChar + " ");
                        break;
                    default:
                        System.out.print(ConsoleColors.RESET + aChar + " ");
                        break;
                }
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }
}
