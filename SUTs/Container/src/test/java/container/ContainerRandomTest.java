package container;


import org.junit.Test;
import java.util.Random;

public class ContainerRandomTest {
	private static final int MAX_VALUE=10;
	private static final int NUM_TESTS=10;
    public static int RandInt(Random random){
    	int n = random.nextInt(MAX_VALUE);// get a random number between 0 (inclusive) and  MAX_VALUE=10 (exclusive)
        return (int) n;
        }
    public static String RandMethod(Random random){
        String[] methodArray = new String[] {"put","get","remove","size"};//

    	int n = random.nextInt(4);// get a random number between 0 (inclusive) and  4 (exclusive)
    	            
        return methodArray[n] ; // return the method name 
        }
	@Test 
	public void test() {
		System.out.println("Start testing...");
		for (int k = 0; k < 100; k++) {

			Container c = new Container();
			long randomseed = System.currentTimeMillis();
			Random random = new Random(randomseed);

			for (int i = 0; i < NUM_TESTS; i++) {
				String methodName = ContainerRandomTest.RandMethod(random);
		
					int n = ContainerRandomTest.RandInt(random);

				   if (methodName.equals("put")){
						int r=c.put(n);
					}
					else if (methodName.equals("get")){
						int r=c.get(n);
					}
					else if (methodName.equals("remove")){
					//	c.printContainer();
						int r=c.remove(n);
					}
					else if (methodName.equals("size")){
						int r=c.size();
					}
		//				c.printContainer();
			}
		} //for k
		
		System.out.println("done!");
		
		
	}
	

}
