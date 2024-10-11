package logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable{
	private static final  Model instance = new Model();
	private final Map<Integer,User> model;
	public static Model getInstance() {
		return instance;
	}
	private Model() {
		
		model = new HashMap<>();
		model.put(1, new User("Alice", "Smith", 55000));
		model.put(2, new User("Bob", "Johnson", 65000));
		model.put(3, new User("Charlie", "Williams", 75000));
		model.put(4, new User("Diana", "Brown", 45000));
		model.put(5, new User("Ethan", "Jones", 80000));
		model.put(6, new User("Fiona", "Garcia", 72000));
		model.put(7, new User("George", "Martinez", 60000));
		model.put(8, new User("Hannah", "Davis", 49000));
		model.put(9, new User("Ivan", "Rodriguez", 52000));
		model.put(10, new User("Jack", "Wilson", 87000));


	}
	public void add(User user, int id) {
		model.put(id,user);
	}
	public void del(int id) {
		model.remove(id);
	}
	
	public Map<Integer,User> getFromList(){
		return model;
	}
}
