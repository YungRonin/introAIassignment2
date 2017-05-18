package introAIassignment2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	private static String fileName;
	private static knowledgeBase kb;
	
	public static void main(String[] args){
		
		kb = new knowledgeBase();
		try {
				readFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	
	public static void readFile(String fileName) throws IOException{
		
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
			
			kb.printKB();
		}
		catch(FileNotFoundException ex){
			System.out.println("not found " + ex);
		}
	}

	private static void formulateQuerey(String readLine) {
		for(String info : readLine.split(";")){
			System.out.println(info);
		}
	}

	private static void aquireKnowledge(String readLine) {
		for(String info : readLine.split(";")){
			kb.add(readLine);
		}
	}

}
