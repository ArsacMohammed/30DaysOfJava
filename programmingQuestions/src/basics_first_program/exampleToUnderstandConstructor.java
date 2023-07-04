package basics_first_program;

public class exampleToUnderstandConstructor {
    private String name;
    private int age;

    // Constructor for initializing instance variables
    public exampleToUnderstandConstructor() {
        this.name = "John Doe";
        this.age = 30;
    }

    // Parameterized constructor
    public exampleToUnderstandConstructor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Overloaded constructor
    public exampleToUnderstandConstructor(String name) {
        this.name = name;
        this.age = 0;
    }

    // Performing additional setup tasks
    public exampleToUnderstandConstructor(String name, int age, boolean isStudent) {
        this.name = name;
        this.age = age;

        if (isStudent) {
            System.out.println("Additional setup tasks: Enrolling in classes");
            // Perform other student-related setup tasks
        } else {
            System.out.println("Additional setup tasks: Preparing for work");
            // Perform other non-student setup tasks
        }
    }

    // Object initialization from external data
    public exampleToUnderstandConstructor(String name, String ageString) {
        this.name = name;
        try {
            this.age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age format. Setting age to 0.");
            this.age = 0;
        }
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        // Initializing instance variables using default constructor
        exampleToUnderstandConstructor person1 = new exampleToUnderstandConstructor();
        System.out.println("Person 1 - Name: " + person1.getName() + ", Age: " + person1.getAge());

        // Parameterized construction
        exampleToUnderstandConstructor person2 = new exampleToUnderstandConstructor("Alice", 25);
        System.out.println("Person 2 - Name: " + person2.getName() + ", Age: " + person2.getAge());

        // Overloaded constructor
        exampleToUnderstandConstructor person3 = new exampleToUnderstandConstructor("Bob");
        System.out.println("Person 3 - Name: " + person3.getName() + ", Age: " + person3.getAge());

        // Additional setup tasks in constructor
        exampleToUnderstandConstructor person4 = new exampleToUnderstandConstructor("Eve", 22, true);

        // Object initialization from external data
        exampleToUnderstandConstructor person5 = new exampleToUnderstandConstructor("Alex", "30");
        System.out.println("Person 5 - Name: " + person5.getName() + ", Age: " + person5.getAge());
    }
}
