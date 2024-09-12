import { Navigate, RouteObject } from "react-router-dom";
import Home from "../pages/Home";
import { ReactNode } from "react";
import { useAuth } from "../context/AuthenticationContext";
import Layout from "../components/Layout";
import Dummy from "../pages/Dummy";

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
        element: <ProtectedRoute children={ <Layout /> } />,
        children: [
            {
                path: '/home',
                element: <ProtectedRoute children={ <Home /> } />,
                index: true,
            },
            {
                path: '/dummy',
                element: <ProtectedRoute children={ <Dummy /> } />,
            }
        ]
    }
];

export default PrivateRoutes;