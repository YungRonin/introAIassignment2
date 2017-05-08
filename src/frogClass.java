import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class frogClass {
	private static String fileName;
	private static frogClass frog;
	
	
	public frogClass(String aString){
		frogClass.fileName = aString;
	}
	
	public static void main(String[] args){
		
		frog = new frogClass("asdf");
		try {
			frog.readFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
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
				
			input.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("not found " + ex);
		}
	}

	private void formulateQuerey(String readLine) {
		for(String info : readLine.split(";")){
			System.out.println(info);
		}
	}

	private void aquireKnowledge(String readLine) {
		for(String info : readLine.split(";")){
			System.out.println(info);
		}
	}

}
