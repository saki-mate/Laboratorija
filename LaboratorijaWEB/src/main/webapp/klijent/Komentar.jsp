<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<style>
body {
	background: -webkit-linear-gradient(to right, #64b3f4, #c2e59c); 
	background: linear-gradient(to right, #64b3f4, #c2e59c);
	text-align: center
}
input {
	width: 380px;
	height: 40px;
	font-size: 18px;
	text-align: center;
}

select {
	width: 380px;
	height: 40px;
	font-size: 18px;
}
.topnav {
	overflow: hidden;
	background-color: #f3f3f3;
	text-align: center;
}

.topnav a {
	float: left;
	color: #666;
	text-align: center;
	padding: 20px 24px;
	text-decoration: none;
	font-size: 18px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #04AA6D;
	color: white;
}
.centar{
  	border: 1px solid black;
 	text-align: center;
 	margin-left:auto; 
    margin-right:auto;
}

.centar tr, .centar td{
  border: 1px solid black;
  padding: 8px;
}

.centar tr:hover {background-color: #ddd;}

#tabela {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: black;
  color: white;
}

</style>
<meta charset="UTF-8">
<title>Pocetna stranica</title>
</head>
<body > 

<div class = "sekDeo">
	<security:authorize access="isAuthenticated()">
    		Logovan: <security:authentication property="principal.username" />
    		<th><a href="<c:url value="logout" />">Logout</a></th>
	</security:authorize>
	
	<div class = "topnav">
	<table>
		<tr>
		<th><a href="/Laboratorija/index.jsp">Pocetna</a></th>
		
		
		
		<security:authorize access="!isAuthenticated()">
			<th><a href="/Laboratorija/login.jsp">Ulogujte se</a></th>
			<th><a href ="/Laboratorija/klijent/registracijaKlijent.jsp">Registrujte se</a></th>
		</security:authorize>
		
		<security:authorize access="hasRole('laborant')">
			<th><a href="/Laboratorija/laborantController/sveKategorije">Dodaj analizu</a></th>
			<th><a href="/Laboratorija/laborantController/sveAnalizeL">Promeni cenu analize</a></th> 
			<th><a href ="/Laboratorija/laborantController/sveUloge">Registrujte osobu</a></th>
			<th><a href="/Laboratorija/laborantController/sviKlijenti">Vidi zakazane analize</a></th>
			<th><a href="/Laboratorija/laborantController/sviKomentari">Vidi sve komentare</a></th>
		
		</security:authorize>
		<security:authorize access="hasRole('klijent')">
			<th><a href="/Laboratorija/klijentController/sveAnalize">Zakazivanje analize</a></th>
			<th><a href="/Laboratorija/klijentController/istorijaAnaliza">Pregledaj zakazane analize</a></th>
			<th><a href="/Laboratorija/klijent/Komentar.jsp">Dodavanje komentara</a></th>
			<th><a href="/Laboratorija/klijentController/SvislobodniTermini">Vidi sve slobodne termine</a></th> 
			<th><a href="/Laboratorija/klijentController/sveAnalizePDF">Sve analize PDF</a></th>
		</security:authorize>
		
		<th><a href="/Laboratorija/Kontakt.jsp">Kontakt</a></th>
		</tr>
		
	</table>	
		<c:url var="loginUrl" value="/logout" />
			<form hidden action="${loginUrl }" method="post">
				<input type="submit" value="Logout">
			</form>
</div>	
</div>
<br><br>

<br><br>
<form action = "/Laboratorija/klijentController/kome">
	Unesite komentar: <input type = "text" name = "komentar">
	<input type = "submit" value = "Izaberi rezervaciju">
</form>

</body>
</html>