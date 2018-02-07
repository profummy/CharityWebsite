<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/views/PageDirectives.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Projects</title>
<link href="../css/Manager.css" rel="stylesheet" media="all">
</head>
<body>
<h3 class="hello">Hello ${username }</h3>



 <div align="center">
       
            <caption> <h2>List of all Projects - Click on a project to edit it.</h2></caption>
            
           
            <c:forEach items="${projectList}" var="p">
                
                
                <a href="processManageProjects?id=${p.id}">${p.name}</a>
                
                   <!--  <th>${p.id}</th>
                    <th>${p.name}</th>
                    <th>${p.location}</th> -->
                  
                    
                    
                
            </c:forEach>
            
            
         <!--       <table border="1" cellpadding="5">
            <caption> <h2>List of all Donations</h2></caption>
            <tr>
                <th>Donation Id</th>
                <th>Donation Amount</th>
                <th>Date and Time of Donation</th>
                <th>Project Id</th>
                <th>Project Name</th>
            
            </tr>
            <c:forEach items="${donationList}" var="o">
                <tr>
                    <th>${o.id}</th>
                    <th>${o.amount}</th>
                    <th>${o.dateTime}</th>
                    <th>${o.project.id}</th>
                    <th>${o.project.name}</th>
                </tr>
            </c:forEach> -->
            
            
        
    </div>




<a href="managerHome"> Go back to Home </a> </br>
<a class="button" href="addProject"> Add Project</a>${message9}</br>
<a class="button" href="removeProject"> Remove Project</a>${message10}</br>


</body>
</html>