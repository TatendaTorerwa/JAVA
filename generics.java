import java.util.ArrayList;
import java.util.List;

public class GenericsExample {
    public static void main(String[] args) {
	// Without generics
	List names = new ArrayList();
	names.add("Alice");
	names.add("Bob");
	String name = (String) names.get(0); // Explicit type casting required

	// With generics
	List<String> names = new ArrayList<>();
	names.add("Alice");
	names.add("Bob");
	String name = names.get(0); // No type casting required
    }
}
