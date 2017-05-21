package introAIassignment2;

public abstract class truthMethod {

		public String code;				//the code used to identify the method at the command line
		public String longName;			//the actual name of the method.
		protected String tell;
		protected String ask;
		protected knowledgeBase kb;
		
		public truthMethod(String code, String longName){
			
			this.code = code;
			this.longName = longName;
		}
		
		public abstract void init(String tell, String ask);
		public abstract String execute();
}

