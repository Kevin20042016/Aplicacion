import { Link } from 'react-router-dom';

function Login() {
  return (
    // Contenedor principal: Ocupa toda la pantalla, centra el contenido y tiene un fondo oscuro
    <div className="min-h-screen flex items-center justify-center bg-zinc-950 p-4 font-sans">
      
      {/* Tarjeta de Login: Efecto cristal, bordes sutiles, sombra flotante */}
      <div className="max-w-md w-full bg-zinc-900/50 backdrop-blur-xl border border-zinc-800 rounded-2xl p-8 shadow-2xl">
        
        {/* Cabecera */}
        <div className="text-center mb-8">
          <h1 className="text-3xl font-bold text-white tracking-tight mb-2">
            Bienvenido de nuevo
          </h1>
          <p className="text-zinc-400 text-sm">
            Introduce tus credenciales para acceder a tu panel
          </p>
        </div>

        {/* Formulario Visual (Aún no conectado al backend) */}
        <form className="space-y-6" onSubmit={(e) => e.preventDefault()}>
          
          {/* Campo Correo */}
          <div>
            <label className="block text-sm font-medium text-zinc-300 mb-2">
              Correo electrónico
            </label>
            <input 
              type="email" 
              placeholder="tu@correo.com"
              className="w-full px-4 py-3 bg-zinc-950 border border-zinc-800 rounded-xl text-white focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500 transition-colors duration-200"
            />
          </div>

          {/* Campo Contraseña */}
          <div>
            <label className="block text-sm font-medium text-zinc-300 mb-2">
              Contraseña
            </label>
            <input 
              type="password" 
              placeholder="••••••••"
              className="w-full px-4 py-3 bg-zinc-950 border border-zinc-800 rounded-xl text-white focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500 transition-colors duration-200"
            />
          </div>

          {/* Botón de Iniciar Sesión con animación hover y active */}
          <button 
            type="submit"
            className="w-full bg-indigo-600 hover:bg-indigo-500 text-white font-semibold py-3 px-4 rounded-xl transition-all duration-300 transform hover:-translate-y-0.5 active:translate-y-0 shadow-lg shadow-indigo-600/30"
          >
            Iniciar Sesión
          </button>
        </form>

        {/* Separador y enlace al Registro */}
        <div className="mt-8 pt-6 border-t border-zinc-800 text-center">
          <p className="text-zinc-400 text-sm">
            ¿No tienes una cuenta física?{' '}
            <Link 
              to="/registro" 
              className="text-indigo-400 hover:text-indigo-300 font-medium transition-colors"
            >
              Regístrate aquí
            </Link>
          </p>
        </div>

      </div>
    </div>
  );
}

export default Login;