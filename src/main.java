import java.util.Random;
import java.util.concurrent.Semaphore;


public class main {
	/*
	 * Semaphores declaration
	 */
	public static Semaphore sDoctor;
	public static Semaphore sProfessor;
	public static Semaphore sStudent;
	public static Semaphore sPhDStudent;
	public static Semaphore sTable;
	/*
	 * Real value of ingredients is kept for waiters
	 */
	public static volatile int realMilk;
	public static volatile int realCoffe;
	public static volatile int realSugar;
	/*
	 * Theoretical number of ingredients includes number of ingredients which are 
	 * reserved by participants but they didn't come to table yet
	 */
	public static volatile int theoMilk;
	public static volatile int theoCoffe;
	public static volatile int theoSugar;
	
	/*
	 * A number of participants who awaits in the queue
	 */
	public static volatile int quantityDoctor;
	public static volatile int quantityProfessor;
	public static volatile int quantityStudent;
	public static volatile int quantityPhDStudent;
	
	public static volatile int realQuantityDoctor;
	public static volatile int realQuantityProfessor;
	public static volatile int realQuantityStudent;
	public static volatile int realQuantityPhDStudent;
	
	public static void main(String argv[]){
		sDoctor = new Semaphore(0);
		sProfessor = new Semaphore(0);
		sStudent = new Semaphore(0);
		sPhDStudent = new Semaphore(0);
		sTable = new Semaphore(1);
		
		quantityDoctor = 0;
		quantityProfessor = 0;
		quantityStudent = 0;
		quantityPhDStudent = 0;
		
		realQuantityDoctor=0;
		realQuantityProfessor=0;
		realQuantityStudent=0;
		realQuantityPhDStudent=0;
		
		realMilk=1;
		realCoffe=3;
		realSugar=2;
		
		theoMilk=3;
		theoCoffe=3;
		theoSugar=3;
		
		for(int i=0;i<2;i++){
			Waiter w = new Waiter(i);
			w.start();
		}
		for(int i=0;i<10;i++){			
		Professor p = new Professor(i);
		realQuantityProfessor++;
		quantityProfessor++;
		p.start();			
		}
		for(int i=0;i<10;i++){			
		Doctor d = new Doctor(i);
		realQuantityDoctor++;
		quantityDoctor++;
		d.start();			
		}
	}
	    public static void Status(String s){
		System.out.println("***********"+s+"******************");
		System.out.println("real number of professors"+main.realQuantityProfessor);
		System.out.println("real number of doctors"+main.realQuantityDoctor);
		System.out.println("theoretical number of professors"+main.quantityProfessor);
		System.out.println("theoretical number of doctors"+main.quantityDoctor);
		System.out.println("rMilk: "+main.realMilk+" ,rCoffe: "+main.realCoffe+" ,rSugar: "+realSugar);
		System.out.println("tMilk: "+main.theoMilk+" ,tCoffe: "+main.theoCoffe+" ,tSugar: "+theoSugar);
		System.out.println();
		
	}

}
