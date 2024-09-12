import React from "react";
import { plus } from "../assets";

const Home: React.FC = () => {
    return (
        <div className="p-4">
            <div className="flex items-center justify-start bg-white rounded-lg max-w-28">
                <button className="flex flex-col gap-2 p-2">
                    <img 
                        src={plus}
                        alt="plus"
                        className="object-contain w-5 h-5"
                    />
                    <span className="font-semibold text-stone-900">Create</span>
                </button>
            </div>
        </div>
    );
};

export default Home;