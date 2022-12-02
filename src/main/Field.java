package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Field {
	private int rows, columns;
	private Plant[] plants;
	
	public Field() {
		rows = 4;
		columns = 5;
		plants = new Plant[rows * columns];
	}
	
	public Field(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		plants = new Plant[rows * columns];
	}
	
	public void print() {
		int plantAmount = plants.length;
		StringBuilder fieldString = new StringBuilder();
		
		for (int i = 0; i < plantAmount; i++) {
			if (plants[i] == null) {
				fieldString.append("[ ]");
			}
			else if (plants[i].isMature()) {
				fieldString.append("[").append(plants[i].getIcon()).append("]");
			}
			else {
				fieldString.append("[.]");
			}
			
			if ((i + 1) % columns == 0) {
				fieldString.append("\n\n");
			}
			else if (i < plantAmount - 1) {
				fieldString.append("   ");
			}
		}
		
		System.out.print(fieldString);
	}
	
	public void sowPlant(int spotIndex, PlantType type) {
		if (spotIndex < 0 || spotIndex > plants.length) {
			System.out.print("Campo invalido!");
		}
		else if (plants[spotIndex] == null) {
			plants[spotIndex] = new Plant(type);
		}
		else {
			// Ja tem planta aqui, lança uma excessao
			System.out.print("Ja tem uma planta aqui, escolha outro campo.");
		}
	}
	
	public void harvestPlants(Scanner scan) {
		List<PlantType> harvestedPlantTypes = new ArrayList<PlantType>();
		int[] harvestedAmount = new int[PlantType.values().length];
		
		for (Plant plant : plants) {
			if (plant != null && plant.isMature() && !plant.isHarvested()) {
				plant.harvest();
				if (!harvestedPlantTypes.contains(plant.getType())) {
					harvestedPlantTypes.add(plant.getType());
				}
				harvestedAmount[plant.getType().ordinal()] += 2;
			}
		}
		
		for (int i = 0; i < plants.length; i++) {
			if (plants[i] != null && plants[i].isHarvested()) {
				plants[i] = null;
			}
		}
		
		System.out.println("\nPlantas colhidas:\n");
		
		for (PlantType type : harvestedPlantTypes) {
			if (harvestedAmount[type.ordinal()] > 0)
				System.out.println(harvestedAmount[type.ordinal()] + "x " + type);
		}
		
		System.out.println("\nPressione Enter para continuar...");
		scan.nextLine();
		scan.nextLine();
	}
	
	public void prepareSowPlant(Scanner scan) {
		boolean plantDoesntExistError = false;
		boolean plantNoSeedsError = false;
		
		boolean spotOutOfBoundsError = false;
		boolean spotAlreadyOccupiedError = false;
		int plantOption, spotOption;
		
		PlantType[] plantTypes = PlantType.values();
		
		// Menu de seleção da planta
		do {
			Main.clearScreen();
			
			for (int i = 0; i < plantTypes.length; i++)
				System.out.println((i + 1) + " - " + Main.plantSeeds[i] + "x " + plantTypes[i]);
			
			System.out.println();
			
			if (plantDoesntExistError) {
				System.out.println("Planta selecionada nao existe!");
				plantDoesntExistError = false;
			}
			else if (plantNoSeedsError) {
				System.out.println("Voce nao possui mais sementes dessa planta!");
				plantNoSeedsError = false;
			}
			
			System.out.print("Escolha a planta: ");
			plantOption = scan.nextInt() - 1;
			
			if (plantOption < 0 || plantOption >= plantTypes.length)
				plantDoesntExistError = true;
			else if (Main.plantSeeds[plantOption] <= 0) {
				plantNoSeedsError = true;
			}
			
		} while (plantDoesntExistError || plantNoSeedsError);
		
		// Menu de seleção dos campos
		do {
			Main.clearScreen();
			this.print();
			
			System.out.println();
			
			if (spotOutOfBoundsError) {
				System.out.println("Campo inexistente!");
				spotOutOfBoundsError = false;
			}
			else if (spotAlreadyOccupiedError) {
				System.out.println("Campo ja ocupado. Escolha outro!");
				spotAlreadyOccupiedError = false;
			}
			else if (plantNoSeedsError) {
				System.out.println("Voce nao possui mais sementes dessa planta!");
				plantNoSeedsError = false;
			}
			
			System.out.println();
			
			System.out.println("Planta escolhida: " + plantTypes[plantOption] + "\tSementes: " + Main.plantSeeds[plantOption]);
			System.out.print("Selecione o campo para plantar (1 - 20) ou digite '0' para finalizar: ");
			spotOption = scan.nextInt() - 1;
			
			if (spotOption < 0 || spotOption >= this.getSize()) {
				spotOutOfBoundsError = true;
			}
			else if (plants[spotOption] != null) {
				spotAlreadyOccupiedError = true;
			}
			else if (Main.plantSeeds[plantOption] <= 0) {
				plantNoSeedsError = true;
			}
			else {
				Main.plantSeeds[plantOption]--;
				plants[spotOption] = new Plant(plantTypes[plantOption]);
			}
			
		} while (spotOption + 1 != 0);
	}
	
	public int getSize() {
		return rows * columns;
	}
	
	public Plant[] getPlants() {
		return plants;
	}
}
