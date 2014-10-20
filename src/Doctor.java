/* Everything works like in professor class which is a few more comments*/
public class Doctor extends Thread{
	int idDoctor;
	Doctor(int num){
		idDoctor = num;
	}
	public void run(){
		boolean isProduct=false;
		try{
				main.sTable.acquire();
				System.out.print("Doctor nr. "+idDoctor+"comes to table\n");
				main.realQuantityDoctor++;
				main.quantityDoctor++;
			/*Checking if there are products for doctor, if so then release and acquire */
			if((main.theoCoffe>0) & (main.theoMilk>0)){
				main.theoCoffe--;
				main.theoMilk--;
				main.quantityDoctor--;
				main.sDoctor.release();
			}
			else{
				isProduct=true;
				main.sTable.release();
				System.out.print("There is no products for "+idDoctor+". Moving to queue\n");
			}
			/**/
			main.sDoctor.acquire();
			if(isProduct){
				main.sTable.acquire();
			}
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
