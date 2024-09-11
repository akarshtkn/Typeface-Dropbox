import { RouteObject } from "react-router-dom";
import Login from "../pages/Login";
import Signup from "../pages/Signup";

const PublicRoutes: RouteObject[] = [
    {
        path: '/login',
        element: <Login />
    },
    {
        path: '/signup',
        element: <Signup />
    }
];

export default PublicRoutes;