package view;

import java.util.concurrent.Semaphore;

import controller.PersonController;

public class Main {

	public static void main(String[] args) {
		
		PersonController[] persons = new PersonController[4];
		Semaphore mutex = new Semaphore(1);
		
		for(int i = 0; i < 4; i++)
			persons[i] = new PersonController(mutex, 200);
		
		for(PersonController person : persons)
			person.start();

	}

}
