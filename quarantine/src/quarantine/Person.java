package quarantine;

public class Person {
	int ID;
	int age;
	String name;
	
	
	public Person(int ID, int age, String name) {
		this.ID = ID;
		this.age = age;
		this.name = name;
	}
	public String toString() {
		return String.format("Name: %s \nAge: %d \nID: %d\n", this.name, this.age, this.ID);
	}
}
