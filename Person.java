public class Person implements Comparable {
	public class Identity {
		// IDENTITY INSTANCE VARIABLES
		private String pronouns, background;

		// IDENTITY CONSTRUCTORS
		public Identity() {
			this(DEFAULT_PRONOUNS, DEFAULT_BACKGROUND);
		}

		public Identity(String pronouns, String backgorund) {
			if (!this.setAll(pronouns, backgorund)) {
				System.out.println("Identity full constructor error");
			}
		}

		// IDENTITY SETTERS
		public boolean setPronouns(String pronouns) {
			boolean isValid;
			if (pronouns != null) {
				this.pronouns = pronouns;
				isValid = true;
			} else {
				System.out.println("Exception at set pronouns");
				isValid = false;
			}
			return isValid;
		}

		public boolean setBackground(String backgorund) {
			boolean isValid;
			if (backgorund != null) {
				this.background = backgorund;
				isValid = true;
			} else {
				System.out.println("Exception at set background");
				isValid = false;
			}
			return isValid;
		}

		public boolean setAll(String pronouns, String background) {
			return this.setPronouns(pronouns) && this.setBackground(background);
		}

		// IDENTITY GETTERS
		public String getPronouns() {
			return this.pronouns;
		}

		public String getBackground() {
			return this.background;
		}

		// IDENTITY OTHER METHODS
		@Override
		public String toString() {
			return "Pronouns for " + Person.this.getName() + " are " + this.pronouns + "\n and " + Person.this.getName()
					+ "'s background is: " + this.background;
		}

		@Override
		public boolean equals(Object other) {
			if (other == null || (!(other instanceof Identity))) {
				return false;
			}

			Identity otherPerson = (Identity) other;
			return this.pronouns.equals(otherPerson.pronouns) && this.background.equals(otherPerson.background);
		}
	}

	// CONSTANT VARIABLES
	public static final String DEFAULT_NAME = "Jamie Doe";
	public static final String DEFAULT_PRONOUNS = "they/them";
	public static final String DEFAULT_BACKGROUND = "Unknown";
	public static final int DEFAULT_PRIVILEGE = 100;

	// INSTANCE VARIABLES
	private String name;
	private Identity story = new Identity();
	private int privilege;

	// CONSTRUCTORS
	public Person(String name, String pronouns, String background, int privilege) {
		this.setAll(name, pronouns, background, privilege);
	}

	public Person() {
		this(DEFAULT_NAME, DEFAULT_PRONOUNS, DEFAULT_BACKGROUND, DEFAULT_PRIVILEGE);
	}

	public Person(Person original) {
		if (original == null) {
			throw new IllegalArgumentException("Cannot copy null obect in Person copy constructor");
		} else {
			this.setAll(original.name, original.story.pronouns, original.story.background, original.privilege);
		}
	}

	// MUTATORS/SETTERS
	public void setName(String name) {
		this.name = name;
	}

	public void setPronouns(String pronouns) {
		story.setPronouns(pronouns);
	}

	public void setBackground(String background) {
		story.setPronouns(background);
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

	public void setAll(String name, String pronouns, String background, int privilege) {
		this.setPrivilege(privilege);
		this.setName(name);
		this.setPronouns(pronouns);
		this.setBackground(background);
	}

	// ACCESSORS / GETTERS
	public String getName() {
		return this.name;
	}

	public String getPronouns() {
		return this.story.pronouns;
	}

	public String getBackground() {
		return this.story.background;
	}

	public int getPrivilege() {
		return this.privilege;
	}

	// OTHER REQUIRED METHODS
	@Override
	public String toString() {
		return "My name is " + this.name + " and " + this.story + "\n"
				+ "According to this calculator I ended up with " + this.privilege + " estimated privilege points";
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || (!(other instanceof Person))) {
			return false;
		}

		Person otherPerson = (Person) other;
		return this.name.equals(otherPerson.name) && this.story.pronouns.equals(otherPerson.story.pronouns)
				&& this.story.background.equals(otherPerson.story.background) &&
				this.privilege == otherPerson.privilege;
	}

	// INTERFACE METHODS
	@Override
	public int compareTo(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("Null data given to compareTo");
		} else if (o.getClass() != this.getClass()) {
			throw new ClassCastException(" Non-Person data given to compareTO");
		}

		Person otherPerson = (Person) o;
		return this.privilege - otherPerson.getPrivilege();
	}
}