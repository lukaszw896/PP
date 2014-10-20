
public class PhDStudent extends Thread{
	int idPhDStudent;
	PhDStudent(int num){
		idPhDStudent = num;
	}
	public void run(){
		boolean isProduct=false;
		try{	
				main.sTable.acquire();
				System.out.print("PhDStudent nr. "+idPhDStudent+"comes to table\n");
				main.realQuantityPhDStudent++;
				main.quantityPhDStudent++;
			if((main.theoCoffe>0) & (main.theoSugar>0)){
				main.theoCoffe--;
				main.theoSugar--;
				main.quantityPhDStudent--;
				main.sPhDStudent.release();	
			}
			else{
				isProduct=true;
				main.sTable.release();
				System.out.print("There is no products for "+idPhDStudent+". Moving to queue\n");
			}
			main.sPhDStudent.acquire();
			if(isProduct){
				main.sTable.acquire();
			}
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