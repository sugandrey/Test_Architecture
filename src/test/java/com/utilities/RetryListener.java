package com.utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer {

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		Class<? extends IRetryAnalyzer> analyzer = annotation.getRetryAnalyzerClass();
		
		if (analyzer == null) {
			
			annotation.setRetryAnalyzer(Retry.class);
			
		}
		
	}

	
}
