package introAIassignment2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class iEngine {

	private static truthMethod[] tMethods;
	
	public static void main(String[] args){
		
		tMethods = new truthMethod[3];
		tMethods[0] = new forwardChaining("fc", "Forward Chaining");
		tMethods[1] = new backwardChaining("bc", "Backward Chaining");
		tMethods[2] = new truthTable("tt", "Truth Table");
		
		if(args.length < 2){
			System.out.println("Usage: iEngine <method> <filename> /n methods : fc, bc and tt");
			System.exit(1);	
		}
		
		
		
		try {
				String method = args[0];
				truthMethod aMethod = null;
				
				for(int i = 0; i < tMethods.length; i++){
					if(tMethods[i].code.compareTo(method.toLowerCase()) == 0){
						aMethod = tMethods[i];
					}
				}
				
				if(aMethod == null){
					//No, give an error
					System.out.println("Truth method identified by " + method + " not applicable");
					System.exit(1);
				}
				
				readFile(args[1], aMethod);	
				
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
		System.exit(0);
	}
	
	public static void readFile(String fileName, truthMethod method) throws IOException{
		
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
			
			truthMethod method1 = method;
			method1.init(tell, ask);
			System.out.println(method1.execute());
		}
		catch(FileNotFoundException ex){
			System.out.println("not found " + ex);
		}
	}
}
