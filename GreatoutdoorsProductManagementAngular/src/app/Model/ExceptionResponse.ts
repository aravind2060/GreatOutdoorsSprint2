export class ExceptionResponse {
    timeStamp: string;
    status: string;
    listOfErrors: Map<string, Map<string, string>>;
}