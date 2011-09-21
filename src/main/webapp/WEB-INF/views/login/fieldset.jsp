<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://amateras.sf.jp/functions" prefix="f"%>
<%@ page session="true"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<fieldset>
    <div class="clearfix">
        <label for="j_username">Username : </label>
        <div class="input">
            <input name="j_username" id="j_username">
        </div>
    </div>
    <div class="clearfix">
        <label for="j_password">Password : </label>
        <div class="input">
            <input name="j_password" id="j_password" type="password">
        </div>
    </div>
    <div class="clearfix">
        <label for="_spring_security_remember_me">Remember me : </label>
        <div class="input">
            <input name="_spring_security_remember_me" id="_spring_security_remember_me" type="checkbox">
        </div>
    </div>
    <div class="clearfix">
        <div class="input">
             <input type="submit" class="btn primary" value="LOGIN"> 
             <input type="reset" class="btn" value="RESET">
         </div>
    </div>
</fieldset>