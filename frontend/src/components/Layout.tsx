import { Outlet } from "react-router-dom";
import Sidebar from "./Sidebar";
import Topbar from "./Topbar";

const Layout = () => {
    return (
        <div className="grid grid-cols-[auto_1fr] grid-rows-[auto_1fr] h-screen">
            <div className="row-span-2 bg-stone-950"><Sidebar /></div>
            <div className="bg-stone-900"><Topbar /></div>
            <div className="bg-stone-900"><Outlet /></div>
        </div>
    );
};

export default Layout;