package controller;

import java.util.concurrent.Semaphore;

public class PersonController extends Thread {
	
	private int corridor;
	private int progress;
	private Semaphore mutex;

	public PersonController() {
		super();
	}

	public PersonController(Semaphore mutex,int corridor) {
		this.corridor = corridor;
		this.progress = 0;
		this.mutex = mutex;
	}
	
	@Override
	public void run() {
		try {
			while(progress < corridor) 
				walk();
			
			enterDoor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void walk() throws InterruptedException {
		progress += (int)((Math.random()*3) + 4);
		System.out.println(this + ": walked " + progress + "m/" + corridor + "m");
		sleep(1000);
	}
	
	private void enterDoor() throws InterruptedException {
		mutex.acquire();
		sleep((int)((Math.random()*2) + 1));
		System.out.println(this + ": enter the door");
		mutex.release();
	}
	
	@Override
	public String toString() {
		
		return this.getName().replace("Thread-", "Person ");
	}
	
}
