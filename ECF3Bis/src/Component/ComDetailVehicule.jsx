import React from "react";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { service } from "../Service/Service";
import Card from 'react-bootstrap/Card';
import { Link, Outlet } from "react-router-dom";
import { serviceVehicules } from "../Service/ServiceVehicules";

export const CompDetailVehicule = (props) => {
    /**
     * le useParams permet de recuperer l'id qui est l'adresse de la page et de ce fait on peut l'utiliser
     */
    const params3 = useParams();

    const [detailVehicule, setDetailVehicule] = useState({});

    /**
     * le useEffect ici va, à l'initialisation du composant, 
     * recuperer les information d'un véhicule grâce à son id.
     * en return il va setter le state de detail vehicule qui sera utilisé pour l'affichage.
     */
    useEffect(() => {
        serviceVehicules.seeVehiculesById(params3.id).then((res) => setDetailVehicule((res)))
    }, [])

    return (
        <>
            <Card>
                <Card.Header>{detailVehicule.type}</Card.Header>
                <Card.Body>
                    <blockquote className="blockquote mb-0">
                        <Card.Title>Locataire</Card.Title>
                        {detailVehicule.disponibilite?
                        <Card.Text>Disponibilité : Disponible</Card.Text>:<Card.Text>Disponibilite: Louée</Card.Text>}
                        <Card.Text>Immatriculation : {detailVehicule.immatriculation}</Card.Text>
                        <Card.Text> Etat : {detailVehicule.etat}</Card.Text>
                        <Card.Text>Prix : {detailVehicule.prix}</Card.Text>
                        <Card.Text>Modele :{detailVehicule.modele}</Card.Text>
                        <Card.Text>Marque:{detailVehicule.marque}</Card.Text>
                    </blockquote>
                </Card.Body>
            </Card>
        </>
    )
  }





export default CompDetailVehicule;

