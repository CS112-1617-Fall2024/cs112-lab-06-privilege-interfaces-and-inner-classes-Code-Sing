/**
 * Person Class which implements comparable
 * 
 * @author Cody Singleton
 * @version 1.0 10/13/24
 */
public class Person implements Comparable {

	/**
	 * Idenity Class: inner class of Person
	 * 
	 * @author Cody Singleton
	 * @version 1.0 10/13/24
	 */
	public class Identity {
		// IDENTITY INSTANCE VARIABLES
		private String pronouns, background;

		// IDENTITY CONSTRUCTORS

		/**
		 * Default Constructor
		 */
		public Identity() {
			this(DEFAULT_PRONOUNS, DEFAULT_BACKGROUND);
		}

		/**
		 * Full Constructor
		 * 
		 * @param pronouns   String, pronouns of user
		 * @param backgorund String, background of user
		 */
		public Identity(String pronouns, String backgorund) {
			if (!this.setAll(pronouns, backgorund)) {
				System.out.println("Identity full constructor error");
			}
		}

		// IDENTITY SETTERS

		/**
		 * Sets pronouns
		 * 
		 * @param pronouns String, pronouns of user
		 * @return boolean, true if string is not null and greater than 0
		 */
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

		/**
		 * Sets backgrounds
		 * 
		 * @param backgorund String, backgrounds of user
		 * @return boolean, true if string is not null and greater than 0
		 */
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

		/**
		 * Sets all instance varibales
		 * 
		 * @param pronouns   String, pronouns of user
		 * @param background String, background of user
		 * @return boolean, true if both pronouns and background strings are in not null
		 *         and greater than 0
		 */
		public boolean setAll(String pronouns, String background) {
			return this.setPronouns(pronouns) && this.setBackground(background);
		}

		// IDENTITY GETTERS

		/**
		 * Gets pronouns
		 * 
		 * @return String, pronouns instance variable
		 */
		public String getPronouns() {
			return this.pronouns;
		}

		/**
		 * Gets background
		 * 
		 * @return String, background instance variable
		 */
		public String getBackground() {
			return this.background;
		}

		// IDENTITY OTHER METHODS

		/**
		 * Creates a string of all instance variables
		 * 
		 * @return String, all instance varibales
		 */
		@Override
		public String toString() {
			return "Pronouns for " + Person.this.getName() + " are " + this.pronouns + "\n and " + Person.this.getName()
					+ "'s background is: " + this.background;
		}

		/**
		 * Compares object to Idenity object to see if all instance variables are equal
		 * 
		 * @return boolean, true if all instance variables are equal
		 */
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

	/**
	 * Full Constructor
	 * 
	 * @param name       String, name of person
	 * @param pronouns   String, pronouns of user
	 * @param background String, background of user
	 * @param privilege  int, privilege score
	 */
	public Person(String name, String pronouns, String background, int privilege) {
		this.setAll(name, pronouns, background, privilege);
	}

	/**
	 * Default Constructor
	 */
	public Person() {
		this(DEFAULT_NAME, DEFAULT_PRONOUNS, DEFAULT_BACKGROUND, DEFAULT_PRIVILEGE);
	}

	/**
	 * Copy Constructor
	 * 
	 * @param original Person, object to be replicated
	 */
	public Person(Person original) {
		if (original == null) {
			throw new IllegalArgumentException("Cannot copy null obect in Person copy constructor");
		} else {
			this.setAll(original.name, original.story.pronouns, original.story.background, original.privilege);
		}
	}

	// MUTATORS/SETTERS

	/**
	 * Sets name of person
	 * 
	 * @param name String, name of person
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets pronouns
	 * 
	 * @param pronouns String, pronouns of user
	 */
	public void setPronouns(String pronouns) {
		story.setPronouns(pronouns);
	}

	/**
	 * Sets background
	 * 
	 * @param background String, background of user
	 */
	public void setBackground(String background) {
		story.setPronouns(background);
	}

	/**
	 * Sets privilege score
	 * 
	 * @param privilege int, privilege score
	 */
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

	/**
	 * Sets all instance variable
	 * 
	 * @param name       String, name of person
	 * @param pronouns   String, pronouns of user
	 * @param background String, background of user
	 * @param privilege  int, privilege score
	 */
	public void setAll(String name, String pronouns, String background, int privilege) {
		this.setPrivilege(privilege);
		this.setName(name);
		this.setPronouns(pronouns);
		this.setBackground(background);
	}

	// ACCESSORS / GETTERS

	/**
	 * Gets name
	 * 
	 * @return String, name of person
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets pronouns
	 * 
	 * @return String, pronouns of user
	 */
	public String getPronouns() {
		return this.story.pronouns;
	}

	/**
	 * Gets backgrounds
	 * 
	 * @return String, background of user
	 */
	public String getBackground() {
		return this.story.background;
	}

	/**
	 * Gets privilege
	 * 
	 * @return int, privilege score
	 */
	public int getPrivilege() {
		return this.privilege;
	}

	// OTHER REQUIRED METHODS

	/**
	 * Creates a string of all instance variables
	 * 
	 * @return String, all instance varibales
	 */
	@Override
	public String toString() {
		return "My name is " + this.name + " and " + this.story + "\n"
				+ "According to this calculator I ended up with " + this.privilege + " estimated privilege points";
	}

	/**
	 * Compares object to Person object to see if all instance variables are equal
	 * 
	 * @return boolean, true if all instance variables are equal
	 */
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

	/**
	 * Interface to compare the privilege estimates of a Person object to another
	 * Person object.
	 * 
	 * @return int, integer > 0 if privilege is lower, 0 if privilege is equal, and
	 *         interger < 0 if privilege is
	 *         greater then passed Person object
	 */
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