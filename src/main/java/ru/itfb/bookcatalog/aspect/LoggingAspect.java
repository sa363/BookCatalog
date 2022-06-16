/*
 *
 *  * Copyright 2002-2022 the Sergey Anisimov <s.anisimov@gmail.com>
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 *
 */

package ru.itfb.bookcatalog.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.itfb.bookcatalog.interfaces.AuditLogRepository;
import ru.itfb.bookcatalog.model.AuditLog;
import ru.itfb.bookcatalog.type.MethodType;

import javax.transaction.Transactional;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final AuditLogRepository auditLogRepository;

    public LoggingAspect(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Before("execution(* ru.itfb.bookcatalog.controller..*(..))")
    @Transactional
    public void profileAllMethodsBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        /*
        Arrays.stream(methodSignature.getParameterNames())
                .forEach(s -> log.debug("arg name: " + s));

        Arrays.stream(methodSignature.getParameterTypes())
                .forEach(s -> log.debug("arg type: " + s));

        Arrays.stream(joinPoint.getArgs())
                .forEach(o -> log.debug("arg value: " + o.toString()));
        */
        AuditLog auditLog = new AuditLog();
        auditLog.setMethod(methodSignature.getName());
        auditLog.setMethodType(MethodType.START);
        auditLogRepository.save(auditLog);
    }

    @AfterReturning("execution(* ru.itfb.bookcatalog.controller..*(..))")
    @Transactional
    public void profileAllMethodsAfterReturning(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AuditLog auditLog = new AuditLog();
        auditLog.setMethod(methodSignature.getName());
        auditLog.setMethodType(MethodType.END);
        auditLogRepository.save(auditLog);
    }

    @AfterThrowing("execution(* ru.itfb.bookcatalog.controller..*(..))")
    @Transactional
    public void profileAllMethodsAfterThrowing(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Arrays.stream(methodSignature.getExceptionTypes())
                .forEach(aClass -> log.info("exception type: " + aClass));

        AuditLog auditLog = new AuditLog();
        auditLog.setMethod(methodSignature.getName());
        auditLog.setMethodType(MethodType.ERROR);
        auditLogRepository.save(auditLog);
    }
}
