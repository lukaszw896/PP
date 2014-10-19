
public class Professor extends Thread{
	int idProfessor;
	Professor(int num){
		idProfessor = num;
	}
	public void run(){
		try{
			System.out.print("Professor nr. "+idProfessor+"comes to table\n");
			main.sProfessor.acquire();
			main.sTable.acquire();
			main.realMilk--;
			main.realSugar--;
			main.realCoffe--;
			main.realQuantityProfessor--;
			main.Status("Professor"+idProfessor);
			main.sTable.release();
			System.out.print("Professor nr. "+idProfessor+"leaves queue\n");
		}
		catch(InterruptedException e){
			e.getStackTrace();
		}
	}

}
