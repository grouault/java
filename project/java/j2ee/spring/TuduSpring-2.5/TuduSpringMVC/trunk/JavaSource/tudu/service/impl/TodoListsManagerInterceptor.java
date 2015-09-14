package tudu.service.impl;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TodoListsManagerInterceptor implements MethodInterceptor {

	private long numberOfCall=0;
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		numberOfCall++;
		return invocation.proceed();
	}

	public long getNumberOfCall() {
		return numberOfCall;
	}
	
	public void reset() {
		numberOfCall=0;
	}
}
