import java.util.Scanner;

public class Main {
	public static final String[] STATEMENTS = {
			"English is my native language.",
			"My parents graduated college.",
			"I have never wondered where my next meal will come from.",
			"I have no disabilities.",
			"My work and school holidays coincide with the religious holidays I celebrate.",
			"I studied the culture and history of my ancestors in elementary school.",
			"I have never been bullied or been made fun of based on something I could not change (ie. race, ethnicity, sexual orientation, disabilities.)",
			"I have never been stopped by law enforcement due to mere suspicion as opposed to legitimate wrongdoing.",
			"I or my parents have inherited money or property.",
			"I am a US citizen.",
			"I feel safe going for a walk alone.",
			"I go by the same name I was given at birth.",
			"I am comfortable presenting my ID because it properly identifies me.",
			"My ancestors were not forced to come to the United States against their will to be enslaved.",
			"I have family or friends that can give me employment if I need it.",
			"I have never been told my natural hair looks dirty or unprofessional.",
			"I have gone to private school.",
			"I can easily find souvenirs with my name on them."
	};
	public static final int PTS_PER_ANSWER = 10, TOTAL_PTS_POSSIBLE = PTS_PER_ANSWER * STATEMENTS.length,
			MAX = Person.DEFAULT_PRIVILEGE + TOTAL_PTS_POSSIBLE,
					MIN = Person.DEFAULT_PRIVILEGE - TOTAL_PTS_POSSIBLE,
			LEFT_WIDTH = Math.abs(MIN) / PTS_PER_ANSWER,
					RIGHT_WIDTH = MAX / PTS_PER_ANSWER,
			NAME_WIDTH = 20;

	public static final String PRIVILEGE_THAN = " privilege than ";
	public static final String FULL_BLOCK = "\u001B[1m███ \u001B[0m";
	public static final String UPPER_HALF_BLOCK = "▀▀▀ ";
	public static final String LOWER_HALF_BLOCK = "▄▄▄ ";

	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		// DECLARATION + INITIALIZATION
		Person p1 = new Person("Amira", "She/Her/Hers", "I am a Syrian refugee.", 40);
		Person p2 = new Person("D'Andra", "She/Her/Hers", "I am an African-American trans woman.", -20);
		Person p3 = new Person("Jennifer", "They/Them/Their(s)", "I am a New Yorker", 140);
		Person p4 = new Person("Pete", "He/Him/His", "I am a guy from Pennsylvania", 200);
		Person self = new Person();
		Person[] people = { p1, p2, p3, p4, self };
		boolean done = false;
		int input;

		// WELCOME + INTRO
		System.out.println("Welcome to the Privilege Calculator.\n\n"
				+ "This is a small exercise that gives us a glimpse at how "
				+ "fortunate we have been in life.\n");

		Main.fillInfo(self);

		// INPUT + CALCULATION + OUTPUT
		do {
			System.out.println("\n~~~Main Menu~~~\n");
			System.out.println("1. Take questionnaire to calculate privilege estimate.");
			System.out.println("2. Check my estimate. (Defaults to " + Person.DEFAULT_PRIVILEGE
					+ " if questionnaire has not been taken.)");
			System.out.println("3. Compare my estimate with others'.");
			System.out.println("4. Exit program.");
			System.out.print("What would you like to do?\nEnter choice: ");

			input = keyboard.nextInt();
			System.out.println();

			switch (input) {
				case 1:
					self.setPrivilege(Main.doPrivilegeQuestionnaire());
					System.out.println("Your privilege estimate is: " + self.getPrivilege());
					System.out.println("\nReturning to main menu...\n");
					break;
				case 2:
					System.out.println(self);
					break;
				case 3:
					// Hacker Chanllenge
					char[] firstInitial = Main.CreateNameFirstLetterArray(people);
					System.out.println(self.getName() + "(" + firstInitial[4] + ")" + " has " + self.getPrivilege()
							+ " privilege. How does that compare?");
					System.out.println(Main.barChart(Main.CreatePrivilegeArray(people), firstInitial));
					for (int i = 0; i < people.length - 1; i++) {
						if (self.compareTo(people[i]) < 0) {
							System.out.println(
									"Less" + PRIVILEGE_THAN + people[i].getName() + "(" + firstInitial[i] + ")"
											+ " with " + people[i].getPrivilege());
						} else if (self.compareTo(people[i]) > 0) {
							System.out.println(
									"More" + PRIVILEGE_THAN + people[i].getName() + "(" + firstInitial[i] + ")"
											+ " with " + people[i].getPrivilege());
						} else {
							System.out
									.println("Equal privilege to " + people[1].getName() + "(" + firstInitial[i] + ")"
											+ " with " + people[i].getPrivilege());
						}
					}

					System.out.println("\nScroll up to see comparsion.\n");
					break;
				case 4:
					System.out.println("Exiting Program...\n");
					keyboard.close(); // housekeeping
					done = true;
					break;
				default:
					System.out.println("Invalid input, please enter a valid choice."
							+ "\nReturning to main menu...\n");
					break;
			}
		} while (!done);

		System.out.println("Thank you for exploring your privilege, it can be uncomfortable but it's a crucial step " +
				"in our own growth and self-reflection. We appreciate you taking that journey with us! :D");
	}

	public static void fillInfo(Person person) {
		// sets default privilege prior to questionnaire to 100
		String name, pronouns, background;

		System.out.println("What is your name? ");
		name = keyboard.nextLine();
		System.out.println("\nHello " + name + "!"
				+ "\nHere is a list of common examples of preferred pronouns:"
				+ "\n Gender Neutral/Nonbinary: They/Them/Thier(s) or ze/hir/hirs"
				+ "\n Feminie: she/her/hers"
				+ "\n Masculine: he/him/hers"
				+ "What are your prefered pronouns?");
		pronouns = keyboard.nextLine();
		System.out.println(
				"Now please write a small self-identifying statement about yourself "
						+ "and your background and identity, this can be anything you like!\n"
						+ "For example: I'm a [nationality / place of origin / ethnicity / sexuality / gender expression / etc.]...");
		System.out.println("Tell us about yourself: ");
		background = keyboard.nextLine();

		person.setName(name);
		person.setPronouns(pronouns);
		person.setBackground(background);
	}

	public static int doPrivilegeQuestionnaire() {
		boolean isValid;
		int choice, privilegeEstimate = Person.DEFAULT_PRIVILEGE;

		System.out.println("Please indicate whether the following statements are true or false.\n"
				+ "Input 1 or 2 accordingly.");

		for (int i = 0; i < STATEMENTS.length; i++) {
			isValid = false;
			do {
				System.out.println(STATEMENTS[i]);
				System.out.print("1. True. \n2. False.\nEnter the appropriate answer: ");
				choice = keyboard.nextInt();
				System.out.println();

				switch (choice) {
					case 1:
						privilegeEstimate += 10;
						isValid = true;
						break;
					case 2:
						privilegeEstimate -= 10;
						isValid = true;
						break;
					default:
						System.out.println("Invalid choice, please make sure to enter 1 or 2.");
						break;
				}
			} while (!isValid);
		}

		return privilegeEstimate;
	}

	// Hacker Challenge Methods

	/**
	 * Creates a bar chart of privilege to visualize privilege comparsion
	 * by concatenating a string (not quite how it works alot of trail and error)
	 * 
	 * @param privilege       int[], integer value of person privilege from
	 *                        CreatePrivilegeArray method
	 * @param nameFirstLetter char[], char of frist letter in person name from
	 *                        CreateNameFirstLetterArray method
	 * @return String, the barchart of privilege
	 */
	public static String barChart(int[] privilege, char[] nameFirstLetter) {
		String blockLine = "";
		String spacer = "";
		String chart = "\n";
		int linesOverZero = 7;
		// "Draws" each horizontal line of the chart one half at a time
		for (int line = 0; line < 1; line++) {
			// Makes half of bar chart above zero by breaking privilege into 40 point block.
			// If less then 40 half block is uesd
			for (int j = 0; j < linesOverZero; j++) {
				for (int i = 0; i < privilege.length; i++) {
					int addBlock = privilege[i] / 40;
					int addBlockPredictor = addBlock + 1;
					int addHalf = privilege[i] % 40;
					if (addBlockPredictor >= linesOverZero - j && addBlock < linesOverZero - j && addHalf > 0) {
						blockLine += LOWER_HALF_BLOCK;
					} else if (addBlock >= linesOverZero - j && addBlock > 0) {
						blockLine += FULL_BLOCK;
					} else {
						blockLine += "    ";
					}
				}
				chart += blockLine + "\n";
				blockLine = "";
			}
			// Makes half of bar chart below zero by breaking privilege into 40 point block.
			// If less then 40 half block is uesd
			for (int spacerIndex = 0; spacerIndex < nameFirstLetter.length; spacerIndex++) {
				spacer += "(" + nameFirstLetter[spacerIndex] + ")-";
			}
			chart += spacer + " <0 Privilege>" + "\n";

			for (int j = 0; j <= 1; j++) {
				for (int i = 0; i < privilege.length; i++) {
					int addBlock = privilege[i] / 40;
					int addBlockPredictor = addBlock - 1;
					int addHalf = privilege[i] % 40;
					if (addBlockPredictor <= -1 + -j && addBlock > -1 + -j && addHalf < 0) {
						blockLine += UPPER_HALF_BLOCK;
					} else if (addBlock <= -1 + -j && addBlock < 0) {
						blockLine += FULL_BLOCK;
					} else {
						blockLine += "    ";
					}
				}
				chart += blockLine + "\n";
				blockLine = "";
			}
		}
		return chart;
	}

	/**
	 * Creates a int[] of privilege scores from the people array
	 * 
	 * @param people Person[], All person objects to be compared to
	 * @return int[], integer value of person privilege
	 */
	public static int[] CreatePrivilegeArray(Person[] people) {
		int[] privilege = new int[people.length];
		for (int i = 0; i < people.length; i++) {
			privilege[i] = people[i].getPrivilege();
		}
		return privilege;
	}

	/**
	 * Creates a char[] of the 1st letter of a persons name w/ the people array
	 * 
	 * @param people Person[], All person objects to be compared to
	 * @return char[], char of frist letter in person name
	 */
	public static char[] CreateNameFirstLetterArray(Person[] people) {
		char[] nameFirstLetter = new char[people.length];
		for (int i = 0; i < people.length; i++) {
			nameFirstLetter[i] = people[i].getName().charAt(0);
		}
		return nameFirstLetter;
	}
}
