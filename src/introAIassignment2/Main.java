package introAIassignment2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	//private static String fileName;
	private static knowledgeBase kb;
	
	public static void main(String[] args){
		
		try {
				readFile("lkjh.txt");
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
					tell = input.readLine();
					//strip all spaces from ask and tell to aid parsing
					tell = tell.replaceAll("\\s+","");
				}
				else if(command.equals("ASK")){
					ask = input.readLine();
					//strip all spaces from ask and tell to aid parsing
					ask = ask.replaceAll("\\s+","");
					
				}
				command = input.readLine();
			}
				
			input.close();
			
			forwardChaining fChain = new forwardChaining(ask, tell);
			backwardChaining bChain = new backwardChaining(ask, tell);
			System.out.println("forward chain : " + fChain.execute());
			fChain.printKb();
			System.out.println("backward chain : " + bChain.execute());
			bChain.printKb();
		}
		catch(FileNotFoundException ex){
			System.out.println("not found " + ex);
		}
	}
}
