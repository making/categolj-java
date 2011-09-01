<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://amateras.sf.jp/functions" prefix="f"%>
<%@ page session="true"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:import url="/WEB-INF/views/layout/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="login" />
    <c:param name="content">
        <form method="POST"
            action="<c:url value="/j_spring_security_check" />"
            name="login-form">
            <ul>
                <c:if test="${not empty param.error}">
                    <li><font color="red"> Login error. <br>
                            Reason :
                            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                    </font>
                    </li>
                </c:if>
                <li><label for="field-name" class="desc">USER
                        NAME</label>
                    <div>
                        <input name="j_username" type="text"
                            id="field-name" class="field text medium" />
                    </div></li>
                <li><label for="field-password" class="desc">PASSWORD</label>
                    <div>
                        <input name="j_password" type="password"
                            id="field-password"
                            class="field text medium" />
                    </div></li>
                <li><label for="field-remember" class="desc">REMEMBER
                        ME</label>
                    <div>
                        <input name="_spring_security_remember_me"
                            type="checkbox" id="field-password"
                            class="field text medium" />
                    </div></li>
                <li class="buttons"><input name="submit"
                    type="submit" value="Login" /> <input type="reset"
                    value="Reset" /></li>
            </ul>
        </form>
    </c:param>
</c:import>
