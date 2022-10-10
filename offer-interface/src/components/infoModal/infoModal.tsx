import React, {useEffect, useState} from 'react';
import {Dialog} from 'primereact/dialog';
import {ProgressSpinner} from 'primereact/progressspinner';
import IOffer from '../../interfaces/IOffer';
import {getOffer} from '../../helpers';

interface IProps {
    onClose: () => void;
    offerId: string;
}

export const InfoModal = (props: IProps) => {
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [offer, setOffer] = useState<IOffer | null>(null);

    useEffect(() => {
        getOffer(props.offerId).then((offer: IOffer) => {
            setOffer(offer);
            setIsLoading(false);
        });
    }, [props.offerId]);

    const onHide = () => {
        props.onClose();
    }

    return (
        <Dialog id="info-modal" header={offer?.name || "Info"} visible={true} onHide={() => onHide()} style={{ minWidth: 480 }}>
            {isLoading ? <ProgressSpinner /> : <>
                <label className="block">Prize:</label>
                <p>{offer?.prize} €</p>
                <label className="block">Description:</label>
                <p>{offer?.description}</p>
            </>}
        </Dialog>
    )
}
