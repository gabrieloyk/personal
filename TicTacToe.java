package working_with_objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	static boolean check = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] gameBoard = {
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|',' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}
				};
		
		printGameBoard(gameBoard);
		
		
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your placement (1-9)");
			int pos = scan.nextInt();
			while (playerPositions.contains(pos) || cpuPositions.contains(pos)){
				System.out.println("Sorry that place is already taken. Please input another placement!");
				pos = scan.nextInt();
			}
			playerPositions.add(pos);
			
			placePiece(pos, gameBoard, "player");
			String result = checkWinner();
			if (result.length() > 0) {
				printGameBoard(gameBoard);
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
				cpuPos = rand.nextInt(9) + 1;
			}
			cpuPositions.add(cpuPos);
			placePiece(cpuPos, gameBoard, "cpu");
			
			printGameBoard(gameBoard);
			
			result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
		}
		
	}
	
	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(int pos, char[][] gameBoard, String user) {
		char symbol = 'X';
		
		if (user.equals("player")) {
			symbol = 'X';
		} else if (user.equals("cpu")){
			symbol = 'O';
		}
		gameBoard[2 * ((pos - 1) / 3)][(2 * ((pos - 1) % 3))] = symbol;
		// 2* is to skip the borders
	}
	
	public static String checkWinner() {
		List<Integer> topRow = Arrays.asList(1, 2, 3);
		List<Integer> midRow = Arrays.asList(4, 5, 6);
		List<Integer> botRow = Arrays.asList(7, 8, 9);
		List<Integer> leftCol = Arrays.asList(1, 4, 7);
		List<Integer> midCol = Arrays.asList(2, 5, 8);
		List<Integer> rightCol = Arrays.asList(3, 5, 7);
		List<Integer> diag1 = Arrays.asList(1, 5, 9);
		List<Integer> diag2 = Arrays.asList(3, 5, 7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(diag1);
		winning.add(diag2);
		
		for (List l : winning) {
			if (playerPositions.containsAll(l)) {
				return "Congratulations you won!";
			} else if (cpuPositions.containsAll(l)) {
				return "CPU wins! Sorry";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "Tie";
			}
		}
		return "";
	}
	
}
