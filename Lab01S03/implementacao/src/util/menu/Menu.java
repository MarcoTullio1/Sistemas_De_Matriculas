package util.menu;

import java.util.*;

public class Menu {
	private String name;
	private String description;

	private Map<Integer, String> options;

	public Menu(String name, String description, Map<Integer, String> options) {
		this.name = name;
		this.options = options;
		this.description = description;
	}

	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public void setOptions(HashMap<Integer, String> options) {
		this.options = options;
	}

	public static void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	private String Header() {
		StringBuilder sb = new StringBuilder();

		sb.append("===================== " + this.name + " =====================\n"
				+ "                        " + this.description + "\n");

		sb.append("=====================");

		for (int i = 0; i < this.name.length() + 1; i++) {
			sb.append("=");
		}
		sb.append("======================\n");

		return sb.toString();
	}

	private String options() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		options.forEach((key, value) -> {
			sb.append(key + " - " + value + "\n");
		});
		return sb.toString();
	}

	public void mainMenu() {
		StringBuilder sb = new StringBuilder();
		clearScreen();
		sb.append(Header());
		sb.append(options());

		System.out.println(sb.toString());
	}

	public void subMenu() {
		StringBuilder sb = new StringBuilder();
		clearScreen();
		sb.append(Header());

		System.out.println(sb.toString());

	}

	public static int optionHandler(String input, List<Integer> validOptions) {
		Integer opt;
		try {
			opt = Integer.valueOf(input);
		} catch (Exception e) {
			opt = 0;
		}
		if (!validOptions.contains(opt)) {
			return 0;
		}
		return opt;
	}

	public static Double optionHandler(String input) {
		double opt;
		try {
			opt = Double.valueOf(input);
		} catch (Exception e) {
			System.out.println("\n!! Valor não aceito !!\n");
			return -1.0;
		}
		return opt;
	}

	public static void pausaTeclado(Scanner input) {
		System.out.println("\nPressione qualquer tecla para continuar");
		try {
			input.nextLine();
		} catch (NoSuchElementException e) {
			return;
		}
	}
}
