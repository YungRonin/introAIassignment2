import java.util.HashMap;
import java.util.Map;

public class knowledgeBase {
	
	private Map<Boolean, String> sentances;
	
	public knowledgeBase(){
		this.sentances = new HashMap<Boolean, String>();
	}
	
	public void add(String args){
		
		if(args.contains("=>")){
			
		}
		else{
			sentances.put(true, args);
		}
	}
}
