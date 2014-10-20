/* Everything works like in professor class which is a few more comments*/

public class Student extends Thread{
	int idStudent;
	Student(int num){
		idStudent = num;
	}
	public void run(){
		boolean isProduct=false;
		try{
				main.sTable.acquire();
				System.out.print("Student nr. "+idStudent+"comes to table\n");
				main.realQuantityStudent++;
				main.quantityStudent++;
			if((main.theoMilk>0) & (main.theoSugar>0) ){
				main.theoMilk--;;
				main.theoSugar--;
				main.quantityStudent--;
				main.sStudent.release();
			}
			else{
				isProduct=true;
				main.sTable.release();
				System.out.print("There is no products for "+idStudent+". Moving to queue\n");
			}
			main.sStudent.acquire();
			if(isProduct){
				main.sTable.acquire();
			}
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
