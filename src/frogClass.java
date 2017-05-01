import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class frogClass {
	
	private int frogNumber;
	private String frogWord;
	
	public frogClass(int intArg, String stringArg){
		
		this.frogNumber = intArg;
		this.frogWord = stringArg;
	}
	
	public void readFile(String fileName) throws IOException{
		
		try{
			FileReader reader = new FileReader(fileName);
			BufferedReader input = new BufferedReader(reader);
			
			String command = input.readLine();
				while(command != null){
				if(command.equals("TELL")){
					aquireKnowledge(input.readLine());
				}
				else if(command.equals("ASK")){
					formulateQuerey(input.readLine());
				}
				command = input.readLine();
			}
		}
		catch(FileNotFoundException ex){
			System.out.println("not found " + ex);
		}
	}

	private void formulateQuerey(String readLine) {
		// TODO Auto-generated method stub
		
	}

	private void aquireKnowledge(String readLine) {
		// TODO Auto-generated method stub
		
	}

}
