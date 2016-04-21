<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 align="center">Inscription</h1>

<div  class="container">
    <div class="raw inscription col-lg-12" >
            <div class="col-lg-2"></div>
            <div class="col-lg-7">
                <form class="form-signin form-horizontal " role="form" action="inscription.user" method="post">
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
                        <label class="col-md-4 control-label">Confirmer mot de passe : </label>
                        <div class="col-md-6">
                            <input type="password" name="txtConfPwd"  class="form-control" placeholder="Confirmer votre mot de passe" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12 col-md-offset-5 ">
                            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-log-in"></span> Valider</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-2"></div>
        </div>
</div>
