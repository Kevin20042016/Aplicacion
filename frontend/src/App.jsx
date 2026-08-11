import { Routes, Route, Navigate } from 'react-router-dom';

import Login from './pages/Login';
import Registro from './pages/Registro';
import Dashboard from './pages/Dashboard';

function App() {
  return (
    <Routes>
      
      {/* Si el usuario entra a la web sin ruta (localhost:5173), lo redirigimos al login */}
      <Route path="/" element={<Navigate to="/login" />} />
      
      {/* Definimos qué componente se carga para cada texto en la URL */}
      <Route path="/login" element={<Login />} />
      <Route path="/registro" element={<Registro />} />
      <Route path="/dashboard" element={<Dashboard />} />
      
    </Routes>
  );
}

export default App;