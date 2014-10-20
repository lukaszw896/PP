
public class Professor extends Thread{
	int idProfessor;
	Professor(int num){
		idProfessor = num;
	}
	/*
	 * Professor is coming to the table. After that he checks whether there are needed products or not. If so then 
	 * he makes his coffee and go away. If not sTable.release() and wait in Professor queue. After acquiring he checks
	 * if he was in queue or not. 	
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		boolean isProduct=false;
		try{		
			main.sTable.acquire();
			System.out.print("Professor nr. "+idProfessor+"comes to table\n");
			/*Checking if there are products for professor, if so then take */
				main.realQuantityProfessor++;
				main.quantityProfessor++;
			if((main.theoCoffe>0) & (main.theoMilk>0) & (main.theoSugar>0)){
				main.theoCoffe--;
				main.theoMilk--;
				main.theoSugar--;
				main.quantityProfessor--;
				main.sProfessor.release();
			}
			/*
			 * If there is no products for professor than he is releasing table semaphore and waits for resources from semaphore 
			 */
			else{
				isProduct=true;
				main.sTable.release();
				System.out.print("There is no products for "+idProfessor+". Moving to queue\n");
			}
			
			/*
			 * If there are all products needed by professor than professor gets his coffe and go away (  */
			main.sProfessor.acquire();
			if(isProduct){
				main.sTable.acquire();
			}
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
