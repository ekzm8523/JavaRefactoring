package Model;

public class ObjectManaver {
	
	private static ObjectManaver s_Instance;
	
	public static ObjectManaver getInstance() {
		if(s_Instance == null) s_Instance = new ObjectManaver();
		return s_Instance;
	}
	
}
