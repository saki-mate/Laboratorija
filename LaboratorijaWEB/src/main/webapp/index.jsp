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
.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
}
.slova{
	font-size: 20px;
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
<div class ="slova">
<p>
Danas zapošljavamo 68 radnika od kojih su 85% sa stažom u SaraLabu dužim od 5 godina.</p>
<p>
Trajno poverenje i dobri međuljudski odnosi su doveli do toga da je<br>
 laboratorija „SaraLab“ sa svim svojim pripadajućim laboratorijama bila<br> 
 i ostala vodeća privatna medicinska laboratorija u Vojvodini.<br>
</p><h3>U LABORATORIJI JE ZAPOSLENO:</h3>
<p>
12
lekara i farmaceuta sa visokom stručnom spremom, među kojima dva doktora <br>
i jedan magistar medicinskih nauka, od kojih pet specijalista, kao i jedan molekularni biolog.<br>
</p>
<p>42
sa srednjom medicinskom školom, prvenstveno laboratorijskih tehničara.</p><p>
14
zaposlenih nemedicinskih struka svih nivoa stručnosti.</p>
<p>
Ukupna radna površina svih laboratorija koje se nalaze u gradovima: <br>
Novi Sad na 5 lokacija, Veternik, Zrenjanin, Sombor, Subotica, Vrbas, <br>
Apatin, Kikinda, Žabalj, Kanjiža je preko 1200 m².
</p>

</div>

	
</body>
</html>