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
                        <div class="col-md-5 col-md-offset-5">
                            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-log-in"></span> Valider</button>
                            <button type="submit" class="btn btn-default btn-success" formaction="ajouterUser.oe"><span class="glyphicon glyphicon-user"></span> S'inscrire</button>

                            <a href="ajouterUser.oe">S'inscrire</a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-2"></div>
        </div>
    </c:if>  
    <c:if test="${sessionScope.userId != null}"> 

        <div class="raw">
            <a href="catalogue.oe" class="col-lg-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span>Oeuvres</span>
                    </div>
                    <div class="panel-content">  
                        <img src="./ressources/oeuvres.svg" height="100" width="100"> 
                    </div>
                </div>
            </a>
            <a href="ajouter.oe" class="col-lg-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span>Ajouter</span>
                    </div>
                    <div class="panel-content">  
                        <img src="./ressources/addoeuvre.svg" height="100" width="100"> 
                    </div>
                </div>
            </a>
            <a href="listeReservations.res" class="col-lg-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span>Reservations</span>
                    </div>
                    <div class="panel-content">  
                        <img src="./ressources/cart.svg" height="100" width="100"> 
                    </div>
                </div>
            </a>
        </div>
    </c:if>  


    <!--  <div id="menu" >
          <a href="Accueil " class="col-lg-6">
              <div class="panel panel-primary">
                  <div class="panel-heading">
                      <span>Oeuvres</span>
                  </div>
                  <div class="panel-content">  
                      <span class="glyphicon glyphicon-circle-arrow-right pull-right"></span></div>
              </div>
          </a>
          <a href="TousRemboursements">
              <div class="panel panel-primary">
                  <div class="panel-heading">
                      <span class="glyphicon glyphicon-piggy-bank"></span>
                  </div>
                  <div class="panel-content"> Ajouter
                      <span class="glyphicon glyphicon-circle-arrow-right pull-right"></span></div>
              </div>
          </a>
          <a href="Contrat">
              <div class="panel panel-primary">
                  <div class="panel-heading">
                      <span class="glyphicon glyphicon-list-alt"></span>
                  </div>
                  <div class="panel-content"> Réservations
                      <span class="glyphicon glyphicon-circle-arrow-right pull-right"></span></div>
              </div>
          </a>
          <a href="StatistiquesUser">
              <div class="panel panel-primary">
                  <div class="panel-heading">
                      <span class="glyphicon glyphicon-stats"></span>
                  </div>
                  <div class="panel-content"> Profil
                      <span class="glyphicon glyphicon-circle-arrow-right pull-right"></span></div>
              </div>
      </div>-->
</div>