import React from 'react';
import { NavLink } from 'react-router-dom';
import { dropbox, filesOutline, documentOutline, sharingOutline, documentArrowDown, trash } from '../assets';
import { PiSignatureDuotone } from "react-icons/pi";

const Sidebar: React.FC = () => {
  return (
    <div className="w-64 h-screen">
        <div className="p-8">
            <img 
                src={dropbox} 
                alt="dropbox" 
                className="object-contain w-8 h-8 filter invert brightness-100" 
            />
        </div>

        <NavLink 
            to="/home" 
            className={({ isActive }) => ` text-stone-400 flex items-center p-2 hover:bg-stone-800 hover:text-stone-50 cursor-pointer ${isActive ? 'bg-stone-800 text-stone-50 hover:bg-stone-700' : ''}`}>
            <div className="flex items-center pl-4">
                <img 
                    src={filesOutline} 
                    alt="files" 
                    className="object-contain w-5 h-5 filter invert brightness-50" 
                />
                <span className="pl-6">All Files</span>
            </div>
        </NavLink>
        
        <span 
            className={`text-stone-400 flex items-center p-2 hover:bg-stone-800 hover:text-stone-50 cursor-pointer`}>
            <div className="flex items-center pl-4">
                <img 
                    src={documentOutline} 
                    alt="files" 
                    className="object-contain w-5 h-5 filter invert brightness-50" 
                />
                <span className="pl-6">My Files</span>
            </div>
        </span>
        
        {/* All the below sidebar elements are for dummy purpose */}
        <span 
            className={`text-stone-400 flex items-center p-2 hover:bg-stone-800 hover:text-stone-50 cursor-pointer`}>
            <div className="flex items-center pl-4">
                <img 
                    src={sharingOutline} 
                    alt="files" 
                    className="object-contain w-5 h-5 filter invert brightness-50" 
                />
                <span className="pl-6">Shared</span>
            </div>
        </span>

        <span 
            className={`text-stone-400 flex items-center p-2 hover:bg-stone-800 hover:text-stone-50 cursor-pointer`}>
            <div className="flex items-center pl-4">
                <PiSignatureDuotone className="w-5 h-5 text-stone-100"/>
                <span className="pl-6">Signatures</span>
            </div>
        </span>

        <span 
            className={`text-stone-400 flex items-center p-2 hover:bg-stone-800 hover:text-stone-50 cursor-pointer`}>
            <div className="flex items-center pl-4">
                <img 
                    src={documentArrowDown} 
                    alt="files" 
                    className="object-contain w-5 h-5 filter invert brightness-50" 
                />
                <span className="pl-6">File requests</span>
            </div>
        </span>

        <span 
            className={`text-stone-400 flex items-center p-2 hover:bg-stone-800 hover:text-stone-50 cursor-pointer`}>
            <div className="flex items-center pl-4">
                <img 
                    src={trash} 
                    alt="files" 
                    className="object-contain w-5 h-5 filter invert brightness-50" 
                />
                <span className="pl-6">Deleted files</span>
            </div>
        </span>
        
    </div>
  );
};

export default Sidebar;