package br.com.ifreire.aspects;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PilotAspect {
	
//	@Pointcut("execution(* br.com.ifreire.f1.Main.storeSecondPilot(..))")
//	public void storeSecondPilotEntryPoint() { }
//	
//	@Before("storeSecondPilotEntryPoint()")
//	public void aspectBeforeStoreSecond(JoinPoint joinPoint) {
//		System.out.println("Second pilot will be stored! -- Aspect Before store 2nd pilot");
//	}
//	
//	@After("storeSecondPilotEntryPoint()")
//	public void aspectAfterStoreSecond(JoinPoint joinPoint) {
//		System.out.println("Second pilot stored successfully! -- Aspect After store 2nd pilot");
//	}
//	
//	@Pointcut("execution(* br.com.ifreire.f1.Main.deleteSecondPilotByName(..))")
//	public void deleteSecondPilotByNameEntryPoint() {
//		//System.out.println("defineEntryPoint");
//	}
//	
//	@Before("deleteSecondPilotByNameEntryPoint()")
//	public void aspectBeforeDeleteSecondPilotByName(JoinPoint joinPoint) {
//		System.out.println("Second pilot will be deleted! -- Aspect Before delete 2nd pilot");
//	}
//	
//	@After("deleteSecondPilotByNameEntryPoint()")
//	public void aspectAfterDeleteSecondPilotByName(JoinPoint joinPoint) {
//		System.out.println("Second pilot deleted successfully! -- Aspect After delete 2nd pilot");
//	}
}