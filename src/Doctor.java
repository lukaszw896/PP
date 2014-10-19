
public class Doctor extends Thread{
	int idDoctor;
	Doctor(int num){
		idDoctor = num;
	}
	public void run(){
		try{
			System.out.print("Doctor nr. "+idDoctor+"comes to table\n");
			main.sDoctor.acquire();
			main.sTable.acquire();
			main.realMilk--;
			main.realCoffe--;
			main.realQuantityDoctor--;
			main.Status("Doctor"+idDoctor);
			main.sTable.release();
			System.out.print("Doctor nr. "+idDoctor+"leaves queue\n");
		}
		catch(InterruptedException e){
			e.getStackTrace();
		}
	}


}
