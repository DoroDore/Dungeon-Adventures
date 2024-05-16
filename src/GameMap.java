import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class GameMap {
    private char[][] map;
    private char[][] visualMap;
    private int[] playerCoordinate = new int[2];
    public void generateMap() {
        Random rand = new Random();
        int rows = rand.nextInt(7) + 7;
        int columns = rand.nextInt(7) + 7;
        this.map = new char[rows][columns];
        this.visualMap = new char[rows][columns];

        // Add perimeter walls
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || i == rows - 1 || j == 0 || j == columns - 1) {
                    this.map[i][j] = 'W'; // Wall
                    this.visualMap[i][j] = 'W';
                } else {
                    // Generate random character for the tile
                    char[] tileTypes = {'O', 'F', 'E', 'L', 'W'};
                    char randomChar = tileTypes[rand.nextInt(tileTypes.length)];
                    this.map[i][j] = randomChar;
                    this.visualMap[i][j] = ' ';
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
    private boolean isOnIllegalTile(int row, int column) {
        return this.map[row][column] == 'W' || this.map[row][column] == 'X';
    }
    public static void main(String[] args) throws IOException, ParseException {
        Tiles.createTiles(Main.readFile("./src/data/Tiles.json"));
        GameMap myMap = new GameMap();
        myMap.setupMap();
        myMap.displayMap();
    }
    public void displayMap() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                switch (aChar) {
                    case 'A':
                        System.out.print(ConsoleColors.BOLD_RED + aChar + " ");
                        break;
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
    public void displayVisualMap() {
        for (char[] chars : visualMap) {
            for (char aChar : chars) {
                switch (aChar) {
                    case 'A':
                        System.out.print(ConsoleColors.BOLD_RED + aChar + " ");
                        break;
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
    private void placePlayer() {
        Random rand = new Random();
        int x = rand.nextInt(this.map[0].length-2) + 1;
        int y = rand.nextInt(this.map.length-2) + 1;
        while (isOnIllegalTile(y, x)) {
            x = rand.nextInt(this.map[0].length-2) +1 ;
            y = rand.nextInt(this.map.length)-2 + 1;
        }
        playerCoordinate[0] = x;
        playerCoordinate[1] = y;
        map[y][x] = 'A';
        visualMap[y][x] = 'A';
    }
    public void setupMap() {
        generateMap();
        placePlayer();
    }
    public void playerMove(int x, int y) {
        if (playerCoordinate == null || map == null || map[0] == null ||
                playerCoordinate[0] + x >= map[0].length || playerCoordinate[1] + y >= map.length ||
                playerCoordinate[0] + x < 0 || playerCoordinate[1] + y < 0) {
            System.out.println("Illegal Move Detected");
        }
        else {
            if (map[playerCoordinate[1]+y][playerCoordinate[0]+x] != 'W') {
                map[playerCoordinate[1]][playerCoordinate[0]] = 'O';
                visualMap[playerCoordinate[1]][playerCoordinate[0]] = 'O';
                playerCoordinate[0] += x;
                playerCoordinate[1] += y;
                if (map[playerCoordinate[1]][playerCoordinate[0]] == 'X') {
                    Scanner scanner = new Scanner(System.in);
                    String input = "";
                    while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
                        System.out.println("You have landed on the escape tile. Would you like to travel to the next floor?");
                        System.out.println("Yes/No");
                        input = scanner.nextLine().toLowerCase();
                        if (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
                            System.out.println("Please input a valid option.");
                        }
                    }
                    if (input.equalsIgnoreCase("yes")) {
                        System.out.println("Moving to next floor...");
                    }
                    else {
                        System.out.println("You didn't move to the next floor.");
                    }
                }
                else {
                    map[playerCoordinate[1]][playerCoordinate[0]] = 'A';
                    visualMap[playerCoordinate[1]][playerCoordinate[0]] = 'A';
                }
                revealCluster(playerCoordinate[0],playerCoordinate[1]);
                displayVisualMap();
            }
            else {
                System.out.println("Illegal Move Detected");
            }
        }

    }
    public void playerLook(String direction) {
        int x = 0, y = 0;
        y = switch (direction) {
            case "left" -> {
                x = -1;
                yield 0;
            }
            case "right" -> {
                x = 1;
                yield 0;
            }
            case "up" -> {
                yield -1;
            }
            case "down" -> {
                yield 1;
            }
            default -> y;
        };
        revealCluster(playerCoordinate[0]+x, playerCoordinate[1]+y);
        displayVisualMap();
    }
    public void revealCluster(int x, int y) {
        visualMap[y][x] = map[y][x];
        visualMap[y+1][x] = map[y+1][x];
        visualMap[y-1][x] = map[y-1][x];
        visualMap[y][x+1] = map[y][x+1];
        visualMap[y][x-1] = map[y][x-1];
    }
    public int[] getPlayerCoordinate() {
        return playerCoordinate;
    }
}
