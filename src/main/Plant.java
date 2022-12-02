package main;

public class Plant {
	private int growingTime, dayPlanted;
	private boolean harvested;
	private PlantType type;
	
	public Plant(PlantType type) {
		this.growingTime = 2;
		this.dayPlanted = Main.getCurrentDay();
		this.harvested = false;
		this.type = type;
	}
	
	public void harvest() {
		if (harvested) return;
		
		harvested = true;
		Main.addPlantResource(2, type);
	}
	
	public boolean isMature() {
		return Main.getCurrentDay() - dayPlanted >= growingTime;
	}
	
	public boolean isHarvested() {
		return harvested;
	}
	
	public char getIcon() {
		char icon = Main.PLANT_ICONS[type.ordinal()];
		
		if (harvested) icon = 'X';
		else if (!isMature()) icon = '.';
		
		return icon;
	}
	
	public int getGrowingTime() {
		return growingTime;
	}
	
	public PlantType getType() {
		return type;
	}
	
	public void setGrowingTime(int growingTime) {
		this.growingTime = growingTime;
	}
}
