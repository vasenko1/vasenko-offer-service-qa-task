import IOffer from "../interfaces/IOffer";

const getOffers = async (): Promise<IOffer[]> => {
    const response = await fetch(`http://localhost:8080/v1/offer`, { method: 'GET', headers: { 'Content-Type': 'application/json'} });

    return await response.json();
}

const getOffer = async (offerId: string): Promise<IOffer> => {
    const response = await fetch(`http://localhost:8080/v1/offer/${offerId}`, { method: 'GET', headers: { 'Content-Type': 'application/json'} });

    return await response.json();
}

const createOffer = async (offer: IOffer): Promise<IOffer> => {
    const response = await fetch(`http://localhost:8080/v1/offer`, { method: 'PUT', body: JSON.stringify({ ...offer }), headers: { 'Content-Type': 'application/json'} });

    return await response.json();
}

const updateOffer = async (offer: IOffer): Promise<any> => {
    return await fetch(`http://localhost:8080/v1/offer/${offer.id}`, { method: 'POST', body: JSON.stringify({ name: offer.name, description: offer.description }), headers: { 'Content-Type': 'application/json'} });
}

const deleteOffer = async (offerId: string): Promise<any> => {
    return await fetch(`http://localhost:8080/v1/offer/${offerId}`, { method: 'DELETE', headers: { 'Content-Type': 'application/json'} });
}

export  {
    getOffers,
    getOffer,
    createOffer,
    updateOffer,
    deleteOffer
}