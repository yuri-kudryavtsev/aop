package org.example.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclarePrecedence;

/**
 * Created on 24.01.2021
 *
 * @author Yuri Kudryavtsev
 * skype: yuri.kudryavtsev.indeed
 * email: ykudryavtsev@maxilect.com
 */

@Aspect
@DeclarePrecedence(
        "org.example.aop.aspects.WithRetriesAspect, " +
                "org.example.aop.aspects.MatcherAssertAspect, " +
                "org.example.aop.aspects.AllureLogAspect")
public class AspectsOrder {
}
