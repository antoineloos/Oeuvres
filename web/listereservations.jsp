<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div  class="col-md-12">
    <h1 align='center'>Liste des réservations</h1>             
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <td>Titre</td>
                <td>Date</td>
                <td>Statut</td>
                <td>Prénom adhérent</td>
                <td>Nom adhérent</td>
                <td> </td>
                <td> </td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="reservation" items="${lstReservationsR}">
                <tr>
                    <td>${reservation.oeuvre.titre}</td>
                    <td><fmt:formatDate value="${reservation.date_reservation}" type="date" pattern="yyyy-MM-dd"/></td>
                    <td>${reservation.statut}</td>
                    <td>${reservation.adherent.prenom_adherent}</td>
                    <td>${reservation.adherent.nom_adherent}</td>
                    <c:set var="statut" scope="session" value="${reservation.statut}"/>
                    <c:if test="${statut=='attente'}">
                    <td><a class="btn btn-primary" href="confirmerReservation.res?id=${reservation.id_oeuvre}&dateres='<fmt:formatDate value="${reservation.date_reservation}" type="date" pattern="yyyy-MM-dd"/>'">Confirmer</a></td>
                    </c:if>
                    <td><a class="btn btn-primary" href="supprimerReservation.res?id=${reservation.id_oeuvre}&dateres='<fmt:formatDate value="${reservation.date_reservation}" type="date" pattern="yyyy-MM-dd"/>'">Supprimer</a></td>                    
                </tr>
            </c:forEach>                    
        </tbody>
    </table>              
</div>