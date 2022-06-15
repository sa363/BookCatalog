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
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.itfb.bookcatalog.interfaces.AuditLogRepository;
import ru.itfb.bookcatalog.model.AuditLog;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private AuditLogRepository auditLogRepository;

    public LoggingAspect(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Before("execution(* ru.itfb.bookcatalog.controller..*(..))")
    public void profileAllMethods(JoinPoint joinPoin) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoin.getSignature();
        Object[] args = joinPoin.getArgs();
        String[] parametersName = methodSignature.getParameterNames();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i< args.length; i++) {
            stringBuilder.append(parametersName[i]);
            stringBuilder.append(":");
            stringBuilder.append(args[i].toString());
        }



        AuditLog auditLog = new AuditLog();
        auditLog.setMethod(methodSignature.getName());
        auditLog.setObject(stringBuilder.toString());
        auditLogRepository.save(auditLog);

        log.info(methodSignature.getName());
        log.info(joinPoin.getArgs()[0].toString());

    }
}
