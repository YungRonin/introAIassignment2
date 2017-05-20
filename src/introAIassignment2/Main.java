package introAIassignment2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	//private static String fileName;
	private static knowledgeBase kb;
	
	public static void main(String[] args){
		
		kb = new knowledgeBase();
		try {
				readFile("asdf.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	
	public static void readFile(String fileName) throws IOException{
		
		String ask = "";
		String tell = "";
		
		try{
			FileReader reader = new FileReader(fileName);
			BufferedReader input = new BufferedReader(reader);
			
			String command = input.readLine();
				while(command != null){
				if(command.equals("TELL")){
					//aquireKnowledge(input.readLine());
					tell = input.readLine();
				}
				else if(command.equals("ASK")){
					//formulateQuerey(input.readLine());
					ask = input.readLine();
				}
				command = input.readLine();
			}
				
			input.close();
			
			//strip all spaces from ask and tell to aid parsing
			ask.replaceAll("\\s+","");
			tell = tell.replaceAll("\\s+","");
			
			forwardChaining fChain = new forwardChaining(ask, tell);
			System.out.println("result = " + fChain.execute());
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
			//kb.add(info);
		}
	}

}
