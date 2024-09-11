import { createBrowserRouter } from "react-router-dom";
import PrivateRoutes from "./PrivateRoutes";
import PublicRoutes from "./PublicRoutes";

const AllRoutes = [
    ...PrivateRoutes,
    ...PublicRoutes,
];

const Routes = createBrowserRouter(AllRoutes);
export default Routes;