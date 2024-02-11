import React from 'react';
import { createRoot } from 'react-dom/client'; // Import createRoot from 'react-dom/client'
import App from './App';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Router, Routes, Route} from 'react-router-dom';
import { AuthProvider } from './hooks/useAuth'; // Import your AuthProvider component
import { BrowserRouter } from 'react-router-dom';

/*
createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Router>
      <AuthProvider>
        <Routes>
          <Route element={<App />} />
        </Routes>
      </AuthProvider>
    </Router>
  </React.StrictMode>
);
*/

createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <AuthProvider>
        <App />
      </AuthProvider>
    </BrowserRouter>
  </React.StrictMode>
);



/*
import React from 'react';
import { createRoot } from 'react-dom'; // Import createRoot from 'react-dom' instead of ReactDOM
import App from './App';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter } from 'react-router-dom';
import { AuthProvider } from './hooks/useAuth'; // Import your AuthProvider component

createRoot(document.getElementById('root')).render( // Use createRoot instead of ReactDOM.render
  <React.StrictMode>
    <BrowserRouter>
      <AuthProvider>
        <App />
      </AuthProvider>
    </BrowserRouter>
  </React.StrictMode>
);
*/
/*
import React from 'react';
import ReactDOM from 'react-dom'; // Correct import
//import ReactDOM from 'react-dom/client'
import App from './App';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter } from 'react-router-dom';
import { AuthProvider } from './hooks/useAuth'; // Import your AuthProvider component

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <AuthProvider>
        <App />
      </AuthProvider>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);
*/

/*

import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import { BrowserRouter } from 'react-router-dom';
import { AuthProvider } from './hooks/useAuth'; // Import your AuthProvider component

ReactDOM.render(
  <React.StrictMode>
    <AuthProvider>
      <App />
    </AuthProvider>
  </React.StrictMode>,
  document.getElementById('root')
);*/
/*
ReactDOM.createRoot(document.getElementById('root')).render(
   <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
)

*/
