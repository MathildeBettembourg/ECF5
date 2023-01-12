import React from "react";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Card from 'react-bootstrap/Card';
import { Link, Outlet } from "react-router-dom";
import { serviceVehicules } from "../Service/ServiceVehicules";
import { serviceContrats } from "../Service/ServiceContrats";
import ListGroup from 'react-bootstrap/ListGroup';
import { service } from "../Service/Service";

export const CompDetailContrat = (props) => {
    /**
     * le useParams permet de recuperer l'id qui est l'adresse de la page et de ce fait on peut l'utiliser
     */
    const params = useParams();

    const [detailContrat, setDetailContrat] = useState({});
    const[locataire, setLocataire] = useState({})
    const[vehicule, setVehicule] = useState({})

    /**
     * le useEffect ici va, à l'initialisation du composant, 
     * recuperer les information d'un véhicule grâce à son id.
     * en return il va setter le state de detail vehicule qui sera utilisé pour l'affichage.
     */
    useEffect(() => {
        serviceContrats.seeContratsById(params.id).then((res) => setDetailContrat(res))
    }, [])
    useEffect(()=>{
       setLocataire(detailContrat.locataire)
        setVehicule(detailContrat.vehicule)
    }, [detailContrat])

    return (

         <>{detailContrat && locataire ?
        
             <Card>
                 <Card.Header>{locataire.nom} - {vehicule.marque}</Card.Header>
                 <Card.Body>
                     <blockquote className="blockquote mb-0">
                         <Card.Title>Locataire</Card.Title>
                         <hr/>
                         <Card.Title>Renseignements</Card.Title>
                         <Card.Text> Prenom : {locataire.prenom}</Card.Text>
                         <Card.Text> Prenom : {locataire.nom}</Card.Text>
                         <Card.Text> Email: {locataire.email}</Card.Text>
                         <Card.Text> Telephone : {locataire.telephone}</Card.Text>
                        <hr/>
                        <Card.Title>Vehicule Choisis</Card.Title>
                         <Card.Text> Type de véhicule : {vehicule.type}</Card.Text>
                         <Card.Text> Prix à la journée : {vehicule.prix}</Card.Text>
                         <Card.Text> Marque du véhicule : {vehicule.marque}</Card.Text>
                         <Card.Text> Prix de la location : {detailContrat.prix} €</Card.Text>
                         <Card.Text> Durée de la location : {detailContrat.duree} jours</Card.Text>
        
                     </blockquote>
                 </Card.Body>
             </Card>:<></>
         }
         </>
    )
}
export default CompDetailContrat;