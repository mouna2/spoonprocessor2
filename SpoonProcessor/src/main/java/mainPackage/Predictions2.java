package mainPackage;

import java.util.Iterator;
import java.util.Map;

public class Predictions2 {

	DatabaseReading dr = new DatabaseReading(); 
	
//	public void Predict() {		
//		dr.MethodCallsHashMap.values().contains(x)
//			dr.MethodCallsHashMap.get(x).
//		 Iterator it = dr.MethodCallsHashMap.entrySet().iterator();
//		    while (it.hasNext()) {
//		        Map.Entry pair = (Map.Entry)it.next();
//		        MethodCalls x = dr.MethodCallsHashMap.get(pair.getKey());
//		        System.out.println(pair.getKey() + " = callee method ID " + x.CalleeMethod.ID+ " CALLEE METHOD METHOD NAME "+ x.CalleeMethod.methodName+" CALLER METHOD ID "+ x.CallerMethod.ID+ " CALLER METHOD METHOD NAME "+ x.CallerMethod.methodName);
//		        
//		        System.out.println("Second loop starts-------------------------------------------------------------------------------------------");
//		        Iterator it2 = dr.tracesHashMap.entrySet().iterator();
//			    while (it2.hasNext()) {
//			        Map.Entry pair2 = (Map.Entry)it2.next();
//			        Traces traces = dr.tracesHashMap.get(pair2.getKey());
//			        System.out.println(pair2.getKey() + " = pair requirement " + traces.requirement.ID+ " pair requirement name "+ traces.requirement.RequirementName+" pair method id "+ traces.method.ID+ " PAIR METHOD NAME "+ traces.method.methodName+
//			        		" gold trace"+traces.gold+ " subject trace"+traces.subject);
//			        
//			        //it2.remove();
//			    }
//		        
//		        //it.remove(); // avoids a ConcurrentModificationException
//		    }
//	}
	
	public void predict() 
	{ 	
	
		MethodCalls first = dr.MethodCallsHashMap.get(1);
		MethodTrace firstTrace = dr.tracesHashMap.get(73);
		
		if(firstTrace.method.ID==first.CalleeMethod.ID) {
			System.out.println("yes there is a trace "); 
			// 
		}
		else {
			System.out.println("no trace"); 
		}
		
		
	}
	
	
	
}
