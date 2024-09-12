import React from 'react';
import { bell, desktop, grid, questionMark, userInvite } from '../assets';
import { useAuth } from '../context/AuthenticationContext';

const Topbar: React.FC = () => {
    const { user } = useAuth();

    return (
        <div className="flex items-center justify-between px-4 py-6">
            <div className="text-2xl font-semibold text-stone-50">Dropbox</div>

            <div className="flex justify-center gap-3">
                <div className="flex items-center justify-center p-2 cursor-pointer hover:bg-stone-800 hover:rounded-lg">
                    <img 
                        src={userInvite} 
                        alt="user-invite" 
                        className="object-contain w-5 h-5 filter invert brightness-50" 
                    />
                </div>
                <div className="flex items-center justify-center p-2 cursor-pointer hover:bg-stone-800 hover:rounded-lg">
                    <img 
                        src={grid} 
                        alt="grid" 
                        className="object-contain w-5 h-5 filter invert brightness-50" 
                    />
                </div>
                <div className="flex items-center justify-center p-2 cursor-pointer hover:bg-stone-800 hover:rounded-lg">
                    <img 
                        src={desktop} 
                        alt="desktop" 
                        className="object-contain w-5 h-5 filter invert brightness-50" 
                    />
                </div>
                <div className="flex items-center justify-center p-2 cursor-pointer hover:bg-stone-800 hover:rounded-lg">
                    <img 
                        src={questionMark} 
                        alt="question-mark" 
                        className="object-contain w-5 h-5 filter invert brightness-50" 
                    />
                </div>
                <div className="flex items-center justify-center p-2 cursor-pointer hover:bg-stone-800 hover:rounded-lg">
                    <img 
                        src={bell} 
                        alt="bell" 
                        className="object-contain w-5 h-5 filter invert brightness-50" 
                    />
                </div>
                <div className="flex items-center justify-center p-2 cursor-pointer hover:bg-stone-800 hover:rounded-lg">
                    <div className="flex items-center justify-center p-1 rounded-full bg-blue-950">
                        <span className="flex items-center justify-center w-5 h-5 text-xs text-white">
                            {user?.name.charAt(0). toUpperCase()}
                        </span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Topbar;