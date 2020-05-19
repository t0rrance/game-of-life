package sample.logic;

import javafx.scene.layout.Pane;
import sample.infrastructure.Window;

import java.util.Random;

public class GameOfLife extends Pane {

    private final int BOARD_MAX_RANGE = 100;
    private final int MIN_SIZE = 0;
    private Window[][] windows;
    private int[][][] board;
    private double cellSize;
    private boolean swapBoard = true;
    private Random random;

    public GameOfLife() {
        initBoard();
        setInitialAliveCell();
    }

    private void initBoard() {
        this.random = new Random();
        this.board = new int[2][BOARD_MAX_RANGE][BOARD_MAX_RANGE];
        this.windows = new Window[BOARD_MAX_RANGE][BOARD_MAX_RANGE];
    }

    private void setInitialAliveCell() {
        for (int i = 0; i < BOARD_MAX_RANGE; i++) {
            for (int j = 0; j < BOARD_MAX_RANGE; j++) {
                int randomCell = setAliveOrDeadCell();
                this.board[0][i][j] = randomCell;
                createCellWindow(i, j, randomCell);
            }
        }
    }

    private int setAliveOrDeadCell() {
        double randomValue = random.nextDouble();
        return randomValue < 0.3 ? 1 : 0;
    }

    private void createCellWindow(int i, int j, int deadOrAlive) {
        this.windows[i][j] = new Window();
        if (deadOrAlive == 1) this.windows[i][j].setALiveCell();
        else this.windows[i][j].setDeathCell();
        getChildren().add(windows[i][j]);
    }

    public void startSimulation() throws InterruptedException {
        for (int i = 0; i < BOARD_MAX_RANGE; i++) {
            for (int j = 0; j < BOARD_MAX_RANGE; j++) {
                checkCell(i, j, swapBoard);
            }
        }
        swapBoard = !swapBoard;
        drawGameOfLife(swapBoard);
        Thread.sleep(100);
    }

    private void drawGameOfLife(boolean turnBoard) {
        int indexBoard = turnBoard ? 0 : 1;
        for (int i = 0; i < BOARD_MAX_RANGE; i++) {
            for (int j = 0; j < BOARD_MAX_RANGE; j++) {
                if (this.board[indexBoard][i][j] == 1) windows[i][j].setALiveCell();
                else windows[i][j].setDeathCell();
            }
        }
    }

    private void checkCell(int i, int j, boolean boardTurn) {
        int index = boardTurn ? 0 : 1;
        int sumAliveCell = sumAlive(i, j, index);
        int indexAnotherBoard = !boardTurn ? 0 : 1;

        if (this.board[index][i][j] == 1) {
            if (sumAliveCell == 2 || sumAliveCell == 3) {
                this.board[indexAnotherBoard][i][j] = 1;
            } else {
                this.board[indexAnotherBoard][i][j] = 0;
            }
        }

        if (this.board[index][i][j] == 0) {
            if (sumAliveCell == 3) {
                this.board[indexAnotherBoard][i][j] = 1;
            } else {
                this.board[indexAnotherBoard][i][j] = 0;
            }
        }
    }

    private int sumAlive(int indexCellX, int indexCellY, int indexTurn) {
        int counterNeighbours = 0;
        int iterateX = indexCellX - 1;
        int iterateY = indexCellY - 1;
        int iterateSizeX = indexCellX + 2;
        int iterateSizeY = indexCellY + 2;

        if (iterateX >= MIN_SIZE && iterateSizeX <= BOARD_MAX_RANGE && iterateY >= MIN_SIZE && iterateSizeY <= BOARD_MAX_RANGE) {
            for (int i = iterateX; i < iterateSizeX; i++) {
                for (int j = iterateY; j < iterateSizeY; j++) {
                    if (this.board[indexTurn][i][j] == 1 && !(i == indexCellX && j == indexCellY))
                        counterNeighbours++;
                }
            }
        }

        return counterNeighbours;
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        this.cellSize = width / 100.0;
        this.cellSize = height / 100.0;
        for (int i = 0; i < BOARD_MAX_RANGE; i++) {
            for (int j = 0; j < BOARD_MAX_RANGE; j++) {
                windows[i][j].relocate(i * cellSize, j * cellSize);
                windows[i][j].resize(cellSize, cellSize);
            }
        }
    }

}
