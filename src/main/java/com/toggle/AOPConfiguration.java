package com.toggle;

import com.toggle.model.Feature;
import com.toggle.repository.FeatureRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(AOPConfiguration.class);

    @Autowired
    FeatureRepository featureRepository;

    @Around("@annotation(com.toggle.ToggleFeature)")
    public Object aspectAroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        ToggleFeature toggleFeature = methodSignature.getMethod().getAnnotation(ToggleFeature.class);

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Feature feature = featureRepository.findByName(toggleFeature.featureName());
        if (feature!= null && feature.getActive()) {
            LOGGER.info("Execution of " + className + "." + methodName + " started.");
            return proceedingJoinPoint.proceed();
        }
        LOGGER.info("Execution of " + className + "." + methodName + " " + " is suspended ");
        return null;
    }
}
