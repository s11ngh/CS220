import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

         /*
         3 data strucutres
         1. Queue - priority list      (priorityOrderQueue)
         2. Alphabetical list - sends notification in order
         3. lookup hashmap - contains everyone, selected or not 
          */

public class ManagementSys {

    Queue<Person> priorityOrderQueue = new LinkedList<>(); //initial filter by order of application recieved
    Multimap<String, Person> map = ArrayListMultimap.create();  //used for lookup of selected people by name
    PriorityQueue<Person> notificationList = new PriorityQueue<>(); //alphabetical list of people selected for evacuation


    public String firstName(String fullname) { //returns first name when a String containing full string is passed
        String[] name = fullname.split("");
        if (name.length > 1) {
            return name[0];
        }
        return fullname;
    }

    public void apply(Person name) { //adds a new application to the priority list based on first come first serve basis
        priorityOrderQueue.add(name);
    }

    public void decision(boolean a) { //True = Application accepted; False == Rejected; Person at head is removed from queue

        if (a) {

            map.put(firstName(priorityOrderQueue.peek().name), priorityOrderQueue.peek()); //add selected Person to map for lookup
            notificationList.add(priorityOrderQueue.peek()); //add selected Person to alphabetical list of names for notifications
            priorityOrderQueue.poll();     //remove the person from first step as it has been completed for their application
        } else {
            priorityOrderQueue.poll();     //remove the person from first step as it has been completed for their application
        }

    }

    public Collection<Person> lookup(String firstName) {

       return map.get(firstName);       //return addresses of people with that first name
    }

}





