import React, {useEffect, useState} from 'react';
import {Dialog} from 'primereact/dialog';
import {Button} from 'primereact/button';
import {ProgressSpinner} from 'primereact/progressspinner';
import {InputText} from 'primereact/inputtext';
import {InputTextarea} from 'primereact/inputtextarea';
import IOffer from '../../interfaces/IOffer';
import {getOffer, updateOffer} from '../../helpers';

interface IProps {
    onClose: () => void;
    onSave: () => void;
    offerId: string;
}

export const EditModal = (props: IProps) => {
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [offer, setOffer] = useState<IOffer | null>(null);
    const [name, setName] = useState<string>('');
    const [prize, setPrize] = useState<number>(0);
    const [description, setDescription] = useState<string>('');

    useEffect(() => {
        getOffer(props.offerId).then((offer: IOffer) => {
            setOffer(offer);
            setName(offer.name);
            setPrize(offer.prize);
            setDescription(offer.description);
            setIsLoading(false);
        });
    }, [props.offerId]);

    const onHide = () => {
        props.onClose();
    }

    const onSave = async () => {
        setIsLoading(true);
        await updateOffer({ id: props.offerId, name, prize, description });
        setIsLoading(false);
        props.onSave();
        props.onClose();
    }

    const renderFooter = () => {
        return (
            <>
                <Button label="Close" icon="pi pi-times" onClick={onHide} className="p-button-text close-button" />
                <Button label="Save" icon="pi pi-check" onClick={onSave} autoFocus className="save-button" />
            </>
        );
    }

    return (
        <Dialog id="edit-modal" header={offer?.name ? `Edit ${offer.name}` : "Edit"} visible={true} onHide={() => onHide()} footer={renderFooter()} style={{ minWidth: 480 }}>
            {isLoading ? <ProgressSpinner /> : <>
                <div className="field">
                    <label htmlFor="name" className="block">Name</label>
                    <InputText
                        id="name"
                        aria-describedby="name-help"
                        className="block"
                        style={{ width: '100%' }}
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
                </div>
                <div className="field">
                    <label htmlFor="prize" className="block">Prize</label>
                    <span className="p-input-icon-left">
                        <i>€</i>
                        <InputText
                            id="prize"
                            aria-describedby="prize-help"
                            className="block"
                            style={{ width: '100%' }}
                            value={prize}
                            type="number"
                            onChange={(e) => setPrize(Number(e.target.value))}
                        />
                    </span>
                </div>
                <div className="field">
                    <label htmlFor="description" className="block">Description</label>
                    <InputTextarea
                        id="description"
                        aria-describedby="description-help"
                        className="block"
                        style={{ width: '100%' }}
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        rows={5}
                        cols={10}
                    />
                </div>
            </>}
        </Dialog>
    )
}
