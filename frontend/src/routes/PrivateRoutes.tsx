import { Navigate, RouteObject } from "react-router-dom";
import Home from "../pages/Home";
import { ReactNode } from "react";
import { useAuth } from "../context/AuthenticationContext";

type Props = {
    children: ReactNode;
}

const ProtectedRoute: React.FC<Props> = ({ children }) => {
    const { isAuthenticated } = useAuth();

    return (
        <>
            { isAuthenticated? children : <Navigate to = { '/login'} /> }
        </>
    )
};

const PrivateRoutes: RouteObject[] = [
    {
        path: '/',
        element: <ProtectedRoute children={ <Home /> } />,
        index: true,
    }
];

export default PrivateRoutes;