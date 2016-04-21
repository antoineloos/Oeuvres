<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 align="center">Bibliothèque Polytech</h1>

<div class="container">
    <c:if test="${sessionScope.userId == null}">                   
        <div class="raw login col-lg-12" >
            <div class="col-lg-2"></div>
            <div class="col-lg-7">
                <form class="form-signin form-horizontal " role="form" action="connecter.oe" method="post">
                    <div class="form-group">
                        <label class="col-md-4 control-label">Login : </label>
                        <div class="col-md-6">
                            <input type="text" name="txtLogin" class="form-control" placeholder="Saisir votre identifiant" required autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Mot de passe : </label>
                        <div class="col-md-6">
                            <input type="password" name="txtPwd"  class="form-control" placeholder="Saisir votre mot de passe" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12 col-md-offset-4 ">
                            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-log-in"></span> Valider</button>
                            <c:if test="${sessionScope.userId == null}">
                                <a href="ajouter.user"><button class="btn btn-default btn-success"><span class="glyphicon glyphicon-user"></span> S'inscrire</button></a>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-2"></div>
        </div>
    </c:if>  
    <c:if test="${sessionScope.userId != null}"> 

        <div class="raw">
            <a href="catalogue.oe" class="col-lg-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span>Oeuvres</span>
                    </div>
                    <span class="glyphicon glyphicon-list-alt accueil"></span>
                </div>
            </a>
            <a href="ajouter.oe" class="col-lg-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span>Ajouter</span>
                    </div>
                    <span class="glyphicon glyphicon-plus accueil"></span>
                </div>
            </a>
            <a href="listeReservations.res" class="col-lg-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span>Reservations</span>
                    </div>
                    <span class="glyphicon glyphicon-tags accueil"></span>
                </div>
            </a>
        </div>
    </c:if>  
</div>
