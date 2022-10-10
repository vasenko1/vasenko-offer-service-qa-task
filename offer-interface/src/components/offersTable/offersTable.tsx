import React, {useEffect, useState} from 'react';
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {Button} from 'primereact/button';
import InfoModal from '../infoModal';
import EditModal from '../editModal';
import CreateModal from '../createModal';
import IOffer from '../../interfaces/IOffer';
import {deleteOffer, getOffers} from '../../helpers';

export const OffersTable = () => {
    const [offers, setOffers] = useState<IOffer[]>([]);
    const [activeOfferId, setActiveOfferId] = useState<string>('');
    const [isInfoModalOpen, setIsInfoModalOpen] = useState<boolean>(false);
    const [isEditModalOpen, setIsEditModalOpen] = useState<boolean>(false);
    const [isCreateModalOpen, setIsCreateModalOpen] = useState<boolean>(false);

    const fetchOffers = async () => {
        await getOffers().then(data => setOffers(data));
    }

    useEffect(() => {
        fetchOffers();
    }, []);

    const deleteOfferById = async (offerId: string) => {
        await deleteOffer(offerId);
        setOffers(offers.filter(offer => offer.id !== offerId));
    }

    const infoBodyTemplate = (rowData: any) => {
        return (
            <>
                <Button
                    icon="pi pi-info-circle"
                    label="Info"
                    onClick={() => {
                        setActiveOfferId(rowData.id);
                        setIsInfoModalOpen(true);
                    }}
                    className="p-button-outlined offer-inf0-button"
                    style={{ marginRight: 8 }}
                />
                <Button
                    icon="pi pi-pencil"
                    label="Edit"
                    onClick={() => {
                        setActiveOfferId(rowData.id);
                        setIsEditModalOpen(true);
                    }}
                    className="p-button-outlined edit-offer-button"
                    style={{ marginRight: 8 }}
                />
                <Button
                    icon="pi pi-trash"
                    label="Delete"
                    onClick={() => {
                        deleteOfferById(rowData.id);
                    }}
                    className="p-button-outlined p-button-danger delete-offer-button"
                />
            </>
        );
    }

    return (
        <div>
            <Button
                icon="pi pi-plus"
                label="Create offer"
                onClick={() => setIsCreateModalOpen(true)}
                className="p-button-info add-offer-button"
            />
            <div className="card">
                <DataTable className="offers-table" value={offers} stripedRows responsiveLayout="scroll">
                    <Column field="name" header="Name" style={{ width: '100%' }} />
                    <Column field="actions" header="Actions" body={infoBodyTemplate} style={{ whiteSpace: 'nowrap' }} />
                </DataTable>
            </div>
            {isInfoModalOpen && <InfoModal offerId={activeOfferId} onClose={() => setIsInfoModalOpen(false)} />}
            {isEditModalOpen && <EditModal offerId={activeOfferId} onClose={() => setIsEditModalOpen(false)} onSave={async () => await fetchOffers()} />}
            {isCreateModalOpen && <CreateModal onClose={() => setIsCreateModalOpen(false)} onSave={async () => await fetchOffers()} />}
        </div>
    );
}
