package main;

import java.util.Scanner;

public class Main {
	private static Scanner scan = new Scanner(System.in);
	private static int currentDay = 1;
	
	public final static char[] PLANT_ICONS = {'h', 'H', 'R', 'B', 'Y'};
	
	public static int[] plantSeeds = new int[PlantType.values().length];

	public static void main(String[] args) {
		boolean running = true;
		Field field = new Field();
		
		init();
		
		while (running) {
			Main.clearScreen();
			System.out.println("PLANTADOR DE PLANTAS\n1 - Plantas\n2 - Sementes\n3 - Avançar dia\n4 - Sair");
			System.out.println("Dia: " + currentDay);
			int option = scan.nextInt();
			
			switch (option) {
			case 1:
				Main.plantMenu(field);
				break;
			case 2:
				Main.seedsMenu();
				break;
			case 3: currentDay++; break;
			case 4: running = false; break;
			default: break;
			}
		}
	}
	
	private static void init() {		
		for (int i = 0; i < plantSeeds.length; i++) {
			plantSeeds[i] = 1;
		}
	}
	
	public static int getCurrentDay() {
		return currentDay;
	}
	
	public static void addPlantResource(int amount, PlantType plantType) {
		plantSeeds[plantType.ordinal()] += amount;
	}
	
	public static void clearScreen() {
	    //Clears Screen in java
       /* try {
			if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
        }
        catch (Exception e) {
        	e.printStackTrace();
        }*/
		System.out.print("\033[H\033[2J");  
	    System.out.flush();
	}
	
	public static void plantMenu(Field field) {
		boolean quit = false;
		
		while (!quit) {
			Main.clearScreen();
			
			field.print();
			System.out.println("1 - Plantar\n2 - Colher\n3 - Voltar");
			int option = scan.nextInt();
			
			switch (option) {
			case 1:
				field.prepareSowPlant(scan);
				break;
			case 2:
				field.harvestPlants(scan);
				break;
			case 3:
				quit = true;
				break;
			default: break;
			}
		}
	}
	
	public static void seedsMenu() {
		PlantType[] types = PlantType.values();
		
		Main.clearScreen();
		
		System.out.println("Sementes: ");
		
		for (int i = 0; i < types.length; i++) {
			System.out.println(Main.plantSeeds[i] + "x " + types[i]);
		}
		
		System.out.println("\nPressione Enter para continuar...");
		scan.nextLine();
		scan.nextLine();
	}
}
