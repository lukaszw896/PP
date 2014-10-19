
public class Student extends Thread{
	int idStudent;
	Student(int num){
		idStudent = num;
	}
	public void run(){
		try{
			System.out.print("PhDStudent nr. "+idStudent+"comes to table\n");
			main.sStudent.acquire();
			main.sTable.acquire();
			main.realSugar--;
			main.realMilk--;
			main.realQuantityStudent--;
			main.Status("Student"+idStudent);
			main.sTable.release();
			System.out.print("Student nr. "+idStudent+"leaves queue\n");
		}
		catch(InterruptedException e){
			e.getStackTrace();
		}
	}

}
