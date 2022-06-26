import java.util.*;

public class Methods {

    //String > Unique Username of the gamer
    //ArrayList > Queue of KDAs in games
    HashMap<String, Queue<userKDA>> map = new HashMap<>();

    //these can generate
    // up to 100 unique names (200 if reversed but that wouldn't be practical)

    String[] fruits = new String[]{"Apple", "Orange", "Pineapple", "Grape", "Coconut", "Strawberry", "Kiwi", "Peach", "Watermelon", "Melon"};
    String[] cars = new String[]{"Carrera", "Panamera", "Scuderia", "Vantage", "Ghost", "FlyingSpur", "Huracan", "Aventador", "SuperVeloce", "Escalade"};



    public void record(String username, userKDA score){ // adds a new record to the username's gaming history
        Queue<userKDA> q = new LinkedList<>();
        q=map.get(username);
        q.add(score);
        map.put(username, q);

    }

    public boolean usernameValidator(String username){ //checks if username is valid
        List<String> usernameArray = Arrays.asList(username);
        if(username.length()>16 || username.length()<6){
            return false; // if username is longer than 16  or shorter 4 characters
        }
        for(String i: usernameArray){
            if(i.equals(" ") || i.equals("!") || i.equals("!@") || i.equals("#") || i.equals(",")){ //(did not include every invalid character since there's too many)
                return false; // if the username contains any invalid
            }
        }
        return true;
    }

    public void newUser(String username) throws UserExistsException, InvalidUsernameException { //registers new user if username entered is unique and valid
        if(map.containsKey(username)){
            throw new UserExistsException("Username already taken. Choose a new username or get help from usernameGenerator");
        }
        if(!(usernameValidator(username))){
            throw new InvalidUsernameException("Username must be 6-16 characters and have no spaces");
        }
        map.put(username, null);
    }

    public String usernameGenerator() throws NoMoreNamesLeftException {
        /*this would run infinitely after all 100 possible combinations are made in this program...
        since I made it with a larger (>1 Billion) possible names database in mind, I did not put a counter to limit the iterations after
        it exceeds (fruits.length * cars.length) executions of ELSE and finds all suggested names in map */
        Random random = new Random();
        int index1 = random.nextInt(fruits.length);
        int index2 = random.nextInt(cars.length);



        if(map.containsKey(fruits[index1]+cars[index2])){
            usernameGenerator();
        }
        else{

            return (fruits[index1]+cars[index2]);

        }
        throw new NoMoreNamesLeftException("Please manually select a name, the programmer was too lazy to add more fruits");
    }

    public userKDA KDA(String username, int index){ //returns KDA of entered username's latest game
        return map.get(username).peek();
    }
    public double averageKDA(String username){
        double totalKD = 0;
        int c = 0;
        for(userKDA i: map.get(username)){ //formula to calculate KD is (K+A))/D
            totalKD = totalKD+ ((i.K+i.A)/i.D);
            c++;
        }
        return totalKD/c;
    }

}
