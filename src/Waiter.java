import java.util.ArrayList;
import java.util.Random;


public class Waiter extends Thread{
	int waiterId;
	Waiter(int id){
		waiterId=id;
	}
public void run(){
	while(true){
		try {
			main.sTable.acquire();
			Refill(this);
			/*
			 * Now i'm giving theoretical products to participants 
			 */
			boolean takeProducts=true;
			do{
				System.out.println("P�tla do, kelner nr "+waiterId);
				/*
				 * checking if there are products for professor and if there are professors who needs products
				 */
				
					if((main.theoCoffe>0) & (main.theoMilk>0) & (main.theoSugar>0) & (main.quantityProfessor>0)){
						System.out.println("Sprawdzanie profesora");
						main.theoCoffe--;
						main.theoMilk--;
						main.theoSugar--;
						main.quantityProfessor--;
						main.sProfessor.release();
					}
					else if((main.theoCoffe>0) & (main.theoMilk>0) & (main.quantityDoctor>0)){
						main.theoCoffe--;
						main.theoMilk--;
						main.quantityDoctor--;
						main.sDoctor.release();
					}
					else if((main.theoCoffe>0) & (main.theoSugar>0) & (main.quantityPhDStudent>0)){
						main.theoCoffe--;
						main.theoSugar--;
						main.quantityPhDStudent--;
						main.sPhDStudent.release();
						
					}
					else if((main.theoMilk>0) & (main.theoSugar>0) & (main.quantityStudent>0)){
						main.theoMilk--;;
						main.theoSugar--;
						main.quantityStudent--;
						main.sStudent.release();
					}
					else{
						takeProducts=false;
					}	
			}while(takeProducts);
			main.Status("Waiter"+waiterId);
			main.sTable.release();
			this.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}	
}
/*
 * This method refills containers with products
 */
		void Refill(Thread t){
			ArrayList<String> products = new ArrayList<String>();
			if(main.realMilk<3)products.add("Milk");
			if(main.realSugar<3)products.add("Sugar");
			if(main.realCoffe<3)products.add("Coffe");
			if(products.isEmpty()){
				System.out.println("Containers are full, waiter is going for a smoke");
				try {
					t.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(products.size()==1){
				if(products.get(0)=="Milk"){
					main.theoMilk=main.theoMilk+(3-main.realMilk);
					main.realMilk=3;
				}
				else if(products.get(0)=="Sugar"){
					main.theoSugar=main.theoSugar+(3-main.realSugar);
					main.realSugar=3;
				}
				else{
					main.theoCoffe=main.theoCoffe+(3-main.realCoffe);
					main.realCoffe=3;
				}
			}
			else if(products.size()==2){
				Random rand = new Random();
			    int randomNum = rand.nextInt(2);
			    if(products.get(randomNum)=="Milk"){
			    	main.theoMilk=main.theoMilk+(3-main.realMilk);
			    	main.realMilk=3;
			    	}
				else if(products.get(randomNum)=="Sugar"){
					main.theoSugar=main.theoSugar+(3-main.realSugar);
					main.realSugar=3;
				}
				else{
					main.theoCoffe=main.theoCoffe+(3-main.realCoffe);
					main.realCoffe=3;
				}
			}
			else if(products.size()==3){
				Random rand = new Random();
			    int randomNum = rand.nextInt(3);
			    if(products.get(randomNum)=="Milk"){
			    	main.theoMilk=main.theoMilk+(3-main.realMilk);
			    	main.realMilk=3;
			    }
				else if(products.get(randomNum)=="Sugar"){
					main.theoSugar=main.theoSugar+(3-main.realSugar);
					main.realSugar=3;
				}
				else{
					main.theoCoffe=main.theoCoffe+(3-main.realCoffe);
					main.realCoffe=3;
				}
			}
		}
}