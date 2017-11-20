import java.util.ArrayList;
import java.util.List;

public class TestStatic {
	private static final TestStatic testStatic = new TestStatic();
	private TestStatic(){}
	public static TestStatic getTestStatic(){
		return testStatic;
	}
	private static List<String> str = new ArrayList();
	static{
		str.add("a");
		str.add("b");
		System.out.println("The str---->"+str);
	}
	public static void main(String[] args) {
		
	}
	public static List<String> getStr() {
		return str;
	}
}
