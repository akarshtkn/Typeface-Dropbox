import React from 'react';

const Loader: React.FC = () => {
    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
            <div className="w-12 h-12 border-8 border-gray-300 rounded-full border-t-blue-500 animate-spin"></div>
        </div>
    );
};

export default Loader;