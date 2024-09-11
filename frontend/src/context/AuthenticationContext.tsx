import { createContext, useState, ReactNode, Dispatch, SetStateAction, useContext } from "react";
import { User } from "../types/types";

// Define the structure of the Auth context
interface AuthContextType {
    user: User | null; // Allow user to be null initially
    setUser: Dispatch<SetStateAction<User | null>>; // Allow setting user to null
    isAuthenticated: boolean;
    login: (userData: User) => void; // Accept user data on login
    logout: () => void;
}

type Props = { children: ReactNode };

// Create the Auth context
const AuthContext = createContext<AuthContextType | undefined>(undefined);

// AuthProvider component
const AuthProvider: React.FC<Props> = ({ children }) => {
    const [user, setUser] = useState<User | null>(null); // Initialize user as null
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    // Login function that sets the user and authentication state
    const login = (userData: User) => {
        setUser(userData);
        setIsAuthenticated(true);
    };

    // Logout function that clears the user and authentication state
    const logout = () => {
        setUser(null);
        setIsAuthenticated(false);
    };

    return (
        <AuthContext.Provider value={{ user, setUser, isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

// Custom hook for using the Auth context
const useAuth = () => {
    const context = useContext(AuthContext);
    if (context === undefined) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};

export { AuthProvider, useAuth };