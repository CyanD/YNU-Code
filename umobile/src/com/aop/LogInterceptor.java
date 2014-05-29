package com.aop;


import org.aspectj.lang.ProceedingJoinPoint;

//@Aspect
//@Component
public class LogInterceptor {
	//@Pointcut("execution(public * com.zjx.service..*.add(..))")
	public void myMethod(){};
	
	//@Before("myMethod()")
	public void before() {
		System.out.println("method before");
		return;
	}
	
	//@Around("myMethod()")
	public void aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("method around start");
		pjp.proceed();
		System.out.println("method around end");
	}
	
}
