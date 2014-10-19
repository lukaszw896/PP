import java.util.Random;
import java.util.concurrent.Semaphore;


public class main{
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
	 * A number of participants who awaits in the queue and still didn't get products
	 */
	public static volatile int quantityDoctor;
	public static volatile int quantityProfessor;
	public static volatile int quantityStudent;
	public static volatile int quantityPhDStudent;
	/*
	 * A number of participants who awaits in the queue but their products are already reserved
	 */
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
		
		realMilk=3;
		realCoffe=3;
		realSugar=3;
		
		theoMilk=3;
		theoCoffe=3;
		theoSugar=3;
		
		/*
		 * variables keeping id of each new participant
		 */
		
		int idCounterStudent = 1;
		int idCounterProfessor=1;
		int idCounterDoctor=1;
		int idCounterPhDStudent=1;
		
		for(int i=0;i<2;i++){
			Waiter w = new Waiter(i);
			w.start();
		}
		for(int i=0;i<20;i++){
			Random rand = new Random();
			int randomNum = rand.nextInt(4);
			if(randomNum==0){
				Professor p = new Professor(idCounterProfessor);
				realQuantityProfessor++;
				quantityProfessor++;
				idCounterProfessor++;
				p.start();	
			}
			else if(randomNum==1){
				Doctor d = new Doctor(idCounterDoctor);
				realQuantityDoctor++;
				quantityDoctor++;
				idCounterDoctor++;
				d.start();	
			}
			else if(randomNum==2){
				PhDStudent phd = new PhDStudent(idCounterPhDStudent);
				realQuantityPhDStudent++;
				quantityPhDStudent++;
				idCounterPhDStudent++;
				phd.start();	
			}
			else{
				Student s = new Student(idCounterStudent);
				realQuantityStudent++;
				quantityStudent++;
				idCounterStudent++;
				s.start();
			}	
				int randtime = rand.nextInt(500)+700;
				try {
					Thread.sleep(randtime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
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
