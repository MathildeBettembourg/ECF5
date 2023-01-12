export class ServiceContrats{
    /**
     * METHODE recuperation contrats sur le serveur
     * @returns contrats
     */
        async seeContrats(){
            return await fetch('http://localhost:8080/locations')
            .then((res)=>(res.json()))
        }
    
        /**
         * Methode Ajout contrats sur serveur 
         * @param {*} contratNew type contrats
         * @returns le json
         */
        async addContrats(contratNew){
            return await fetch('http://localhost:8080/locations', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(contratNew)})
                    .then((res)=>(res.json()))
        }
    /**
     * Methode delete by id contrats
     * @param {*} id 
     * @returns le json
     */
        async deleteByIdContrats(id){
            return await fetch(`http://localhost:8080/locations/${id}`, 
            { method: 'DELETE' })
            .then((res) => ((res.json())));
        }
    /**
     * METHODE recuperation contrats sur le serveur
     * @returns contrats par id
     */
        async seeContratsById(id){
            return await fetch(`http://localhost:8080/locations/${id}`)
            .then((res)=>(res.json()))
        }
    
        /**Methode pour modifier les contrats PUT 
         * 
         */
         async modifierContrats(contrat, id){
            return await fetch(`http://localhost:8080/locations/${id}`, 
            {method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(contrat)
        }).then((res)=>(res.json()))
    }
    }
    export const serviceContrats = Object (new ServiceContrats());