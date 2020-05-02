import { ProductDTO } from './ProductDTO';

export class QueryResponse {
    list: ProductDTO[];
    totalNoOfPages: number;
    currentPageNumber: number;

}