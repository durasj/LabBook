import Laboratory from './laboratory';

export default interface Item {
    id: number;
    name: string;
    quantity: number;
    available: boolean;
    laboratory?: Laboratory;
}
