package quarantine;

import java.util.ArrayList;

public class QuarantineQueue {
	ArrayList <Person> queue = new ArrayList<Person>();
	
	public void add(Person person){
		if(contains(person)) {
			System.out.println("Error: This ID already exists");
		}
		else {
			queue.add(person);
		}
		
	}
	
	public int count() {
		return queue.size();
	}
	
	public boolean contains(Person person) {
	
		for(Person p: queue) {
			if(p.ID == person.ID) {
				return true;
			}
			
		}
		return false;
	}
	public String toString() {
		String details = "";
		for(int i=0; i<queue.size(); i++) {
			details += queue.get(i).toString()+"\n";
		}
		
		return details;
	}
}	
