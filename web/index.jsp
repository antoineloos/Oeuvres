
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="lib/css/appStyles.css" rel="stylesheet" type="text/css"/>
        <link href="lib/jquery-ui/jquery-ui.css" rel="stylesheet" type="text/css"/>        
        <script src="lib/jquery/jquery-2.1.3.min.js" type="text/javascript"></script> 
        <script src="lib/bootstrap/js/bootstrap.js" type="text/javascript"></script>
        <script src="lib/bootstrap/js/ui-bootstrap-tpls.js" type="text/javascript"></script>
        <script src="lib/jquery-ui/jquery-ui.js" type="text/javascript"></script>

        <script>console.log("${sessionScope.css}");</script>
        <c:if test="${sessionScope.css != null }">
            <link href="${sessionScope.css}" rel="stylesheet" type="text/css"/>
        </c:if>
        <c:if test="${sessionScope.css == null }">
            <link href="lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        </c:if>    

        <title>Gestion des oeuvres</title>
    </head>
    <body class="body">  


        <div class="container">
            <c:import url="/menu.jsp"/>
            <c:if test="${erreurR != null}">
                <c:import url="/erreur.jsp"/>
            </c:if>
            <div> 
                <c:set var="url" scope="application" value="/home.jsp"/>
                <c:if test="${pageR != null && sessionScope.userId != null}">
                    <c:set var="url" scope="application" value="${pageR}"/>
                </c:if>
                <c:import url="${url}"/>

            </div>
        </div>
        <form class="form-horizontal " role="form" method="post" id="fixe">
            <button type="submit" class="btn btn-default btn-default" formaction="dark.style">
                <span class=" glyphicon glyphicon-circle-arrow-left" ></span>
            </button>
            <button type="submit" class="btn btn-default btn-default" formaction="ligth.style">
                <span class="glyphicon glyphicon-circle-arrow-right"></span>
            </button>
        </form>
    </body>
</html>
