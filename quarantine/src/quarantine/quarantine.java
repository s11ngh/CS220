package quarantine;

public class quarantine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person spiderman = new Person(2, 18,"Spiderman");
		Person ironman = new Person(1, 45, "Tony Stark");
		
		QuarantineQueue Marvel = new QuarantineQueue();
		Marvel.add(spiderman);
		Marvel.add(ironman);
		System.out.println(Marvel);
		
	}

}
