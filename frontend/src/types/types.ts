export type ApiError = {
    status:number;
    message:string;
};

export type User = {
    id: number;
    name: string;
    email: string;
}

export type LoginRequest = {
    email: string;
    password: string;
}