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
<style type="text/css">
body {
	background: -webkit-linear-gradient(to right, #64b3f4, #c2e59c); 
	background: linear-gradient(to right, #64b3f4, #c2e59c);
	text-align: center
}

.topnav {
	overflow: hidden;
	background-color: #f3f3f3;
	
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
	.adresaLab{
		background-color: rgb(250, 240, 230, 0.3);
		top: 200px;
		display: inline-block;
		width: 18%;
		
	}
	
	ul.kontakt{
		list-style-type: none;
	}
	
	.lokacija1{
		width: 50%;
		height: 50%;
		display: inline-block;
	}
</style>
<meta charset="UTF-8">
<title>Kontakt</title>
</head>
<body>

<!-- ---------------------------------------------------------------------------------------------------------- -->
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
<!-- ---------------------------------------------------------------------------------------------------------- -->
<br><br><br>
<div>
</div>
<div class = "adresaLab">
	<ul class = "kontakt">
		<li><h1>Laboratorija1 <strong>SaraMED</strong></h1></li>
		<li>Trg Dositeja Obradovica 3</li>
		<li>21101 Novi Sad</li>
		<li>info@pmf.uns.ac.rs</li>
		<!--<li><img class = "lokacija1" src = "${pageContext.request.contextPath}/slike/kontakt1.jpg"></img></li> -->
		<li><strong>Radno vreme</strong>
		<li>pon-pet: 07-14h</li>
		<li>sub-ned: 08-13h</li>
	</ul>
</div>

<div class = "adresaLab">
	<ul class = "kontakt">
		<li><h1>Laboratorija2 <strong>SaraMED</strong></h1></li>
		<li>Trg Dositeja Obradovica 3</li>
		<li>21101 Novi Sad</li>
		<li>info@pmf.uns.ac.rs</li>
		<!--<li><img class = "lokacija1" src = "${pageContext.request.contextPath}/slike/kontakt2.jpg"></img></li> -->
		<li><strong>Radno vreme</strong>
		<li>pon-pet: 07-14h</li>
		<li>sub-ned: 08-13h</li>
	</ul>
</div>
<div class = "adresaLab">
	<ul class = "kontakt">
		<li><h1>Laboratorija3 <strong>SaraMED</strong></h1></li>
		<li>Trg Dositeja Obradovica 3</li>
		<li>21101 Novi Sad</li>
		<li>info@pmf.uns.ac.rs</li>
		<!--<li><img class = "lokacija1" src = "${pageContext.request.contextPath}/slike/kontakt3.jpg"></img></li> -->
		<li><strong>Radno vreme</strong>
		<li>pon-pet: 07-14h</li>
		<li>sub-ned: 08-13h</li>
	</ul>
</div>

<div class = "adresaLab">
	<ul class = "kontakt">
		<li><h1>Laboratorija4 <strong>SaraMED</strong></h1></li>
		<li>Trg Dositeja Obradovica 3</li>
		<li>21101 Novi Sad</li>
		<li>info@pmf.uns.ac.rs</li>
		<!--<li><img class = "lokacija1" src = "${pageContext.request.contextPath}/slike/kontakt4.jpg"></img></li> -->
		<li><strong>Radno vreme</strong>
		<li>pon-pet: 07-14h</li>
		<li>sub-ned: 08-13h</li>
	</ul>
</div>
<div class = "adresaLab">
	<ul class = "kontakt">
		<li><h1>Laboratorija5 <strong>SaraMED</strong></h1></li>
		<li>Trg Dositeja Obradovica 3</li>
		<li>21101 Novi Sad</li>
		<li>info@pmf.uns.ac.rs</li>
		<!-- <li><img class = "lokacija1" src = "${pageContext.request.contextPath}/slike/kontakt4.jpg"></img></li> -->
		<li><strong>Radno vreme</strong>
		<li>pon-pet: 07-14h</li>
		<li>sub-ned: 08-13h</li>
	</ul>
</div>
</body>
</html>