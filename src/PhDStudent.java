
public class PhDStudent extends Thread{
	int idPhDStudent;
	PhDStudent(int num){
		idPhDStudent = num;
	}
	public void run(){
		try{
			System.out.print("PhDStudent nr. "+idPhDStudent+"comes to table\n");
			main.sPhDStudent.acquire();
			main.sTable.acquire();
			main.realSugar--;
			main.realCoffe--;
			main.realQuantityPhDStudent--;
			main.Status("PhDStudent"+idPhDStudent);
			main.sTable.release();
			System.out.print("PhDStudent nr. "+idPhDStudent+"leaves queue\n");
		}
		catch(InterruptedException e){
			e.getStackTrace();
		}
	}

}