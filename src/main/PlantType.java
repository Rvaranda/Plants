package main;

public enum PlantType {
	COMMON_HERB, RARE_HERB, RED_GRASS, BLUE_LEAF, YGON;
	
	@Override
	public String toString() {
		switch (this) {
		case COMMON_HERB:
			return "Erva comum";
		case RARE_HERB:
			return "Erva rara";
		case RED_GRASS:
			return "Grama vermelha";
		case BLUE_LEAF:
			return "Folha azul";
		case YGON:
			return "Ygon";
		default:
			return null;
		}
	}
}
