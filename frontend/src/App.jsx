import './App.css'

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
//import { Routes, Route, Router } from 'react-router-dom';
//import { AuthProvider, useAuth } from './hooks/useAuth'; // Update the import path

import Home from './components/home/Home';
import Register from './components/auth/Register';
import Login from './components/auth/Login';
import Logout from './components/auth/Logout';
import Dashboard from './components/Dasboard';
import MakeCall from './components/calls/MakeCall';
import ListAllCalls from './components/calls/ListAllCalls';
import CallsTable from './components/calls/CallsTable';
import Profile from './components/auth/Profile';
import CallHistory from './components/calls/CallHistory';
import AdminUI from './components/admin/AdminUI.jsx';
import Footer from './components/layout/Footer';
import NavBar from './components/layout/NavBar';
import RequireAuth from './components/auth/RequireAuth';
import AuthProvider from './components/auth/AuthProvider';


//import Secret from '../Secret';
//import ProtectedRoute from '../ProtectedRoute';




const App = () => {
  return (
    <AuthProvider>
      <main>
        <Router>
          <NavBar />
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/make-call" element={<MakeCall />} />
            <Route
							path="/make-call/"
							element={
								<RequireAuth>
                  <MakeCall />
                  <ListAllCalls />
                  <AdminUI />
                  <CallHistory />
                  <Profile />
								</RequireAuth>
							}
						/>
              <Route path="/calls" element={<ListAllCalls />} />
              <Route path="/user-calls" element={<CallsTable />} />
              <Route path="/profile" element={<Profile />} />
              <Route path="/call-history" element={<CallHistory />} />
              <Route path="/admin" element={<AdminUI />} />
              <Route path="/logout" element={<Logout />} />
            </Routes>
          </Router>
      </main>
    </AuthProvider>
      

   
  );
};

export default App;

{/*
const App = () => {
  return (
    <AuthProvider>
      <main>
        <Router>
          <NavBar />
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/make-call" element={<MakeCall />} />
            <Route
							path="/make-call/"
							element={
								<RequireAuth>
									<MakeCall />
								</RequireAuth>
							}
						/>
              <Route path="/calls" element={<ListAllCalls />} />
              <Route path="/user-calls" element={<CallsTable />} />
              <Route path="/profile" element={<Profile />} />
              <Route path="/call-history" element={<CallHistory />} />
              <Route path="/admin" element={<AdminUI />} />
              <Route path="/logout" element={<Logout />} />
            </Routes>
          </Router>
      </main>
    </AuthProvider>
      

   
  );
};

export default App;
*/}

/*
const App = () => {
  return (
    <>
 
      <Router>
        <NavBar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/make-call" element={<MakeCall />} />
            <Route path="/calls" element={<ListAllCalls />} />
            <Route path="/user-calls" element={<CallsTable />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/call-history" element={<CallHistory />} />
            <Route path="/admin" element={<AdminUI />} />
          </Routes>
      </Router>
      <Footer />
   
      
    </>
   
  );
};

export default App;
*/

/*
const App = () => {
  return (
    <>
      <main>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/make-call" element={<MakeCall />} />
          <Route path="/calls" element={<ListAllCalls />} />
        </Routes>
        </Router>
      </main>
      
    </>
  );
};


export default App;

*/
/*
const App = () => {
  return (
    <Routes>
      <Route path="/" element={<HomeComponent />} />
      <Route path="/login" element={<LoginComponent />} />
      <Route path="/register" element={<RegisterComponent />} />
      <ProtectedRoute path="/dashboard" element={<DashboardComponent />} />
      <ProtectedRoute path="/make-call" element={<CallComponent />} />
      <ProtectedRoute path="/calls" element={<ListCallsComponent />} />
    </Routes>
  );
};

export default App;
*/
/*
const App = () => {
  return (
    <AuthProvider>
        <Router>
          <Routes>
            <Route exact path='/dashboard' element={<ProtectedRoute><DashboardComponent /></ProtectedRoute>} />
            <Route exact path='/make-call' element={<ProtectedRoute><CallComponent /></ProtectedRoute>} />
            <Route exact path='/calls' element={<ProtectedRoute><ListCallsComponent /></ProtectedRoute>} />
            <Route exact path='/secret' element={<ProtectedRoute><Secret /></ProtectedRoute>} />
            <Route path="/" element={<HomeComponent />} />
            <Route path="/login" element={<LoginComponent />} />
            <Route path="/register" element={<RegisterComponent />} />
          </Routes>
        </Router>
    </AuthProvider>
  );
};

export default App;

*/

/*
const App = () => {
  return (
    <AuthProvider>
      <Routes>
        <Route exact path='/dashboard' element={<ProtectedRoute />}>
          <Route path="/dashboard" element={<DashboardComponent />} />
        </Route>
        <Route exact path='/make-call' element={<ProtectedRoute />}>
          <Route path="/make-call" element={<CallComponent />} />
        </Route>
        <Route exact path='/calls' element={<ProtectedRoute />}>
          <Route path="/calls" element={<ListCallsComponent />} />
        </Route>
        <Route path="/" element={<HomeComponent />} />
        <Route path="/login" element={<LoginComponent />} />
        <Route path="/register" element={<RegisterComponent />} />
      </Routes>
    </AuthProvider>
  );
};

export default App;
*/
/*

const App = () => {
  return (
    <AuthProvider>
      <Routes>
        <Route path="/" element={<HomeComponent />} />
        <Route path="/login" element={<LoginComponent />} />
        <Route path="/register" element={<RegisterComponent />} />
        <ProtectedRoute path="/dashboard" element={<DashboardComponent />} />
        <Route path="/make-call" element={<CallComponent />} />
        <Route path="/calls" element={<ListCallsComponent />} />
        {/*
        <ProtectedRoute>
          <Route path="/dashboard" element={<DashboardComponent />} />
          <Route path="/make-call" element={<CallComponent />} />
          <Route path="/calls" element={<ListCallsComponent />} />
          <Route path="/secret" element={<Secret />} />*
        </ProtectedRoute>
      </Routes>
    </AuthProvider>
  );
};

export default App;

*///}



{/*
const App = () => {
  const { user } = useAuth();

   return (
    <AuthProvider>
      <Routes>
        <Route path="/" element={<HomeComponent />} />
        <Route path="/login" element={<LoginComponent />} />
        <Route path="/register" element={<RegisterComponent />} />
        <ProtectedRoute>
          <Route path="/dashboard" element={<DashboardComponent />} />
          <Route path="/make-call" element={<CallComponent />} />
          <Route path="/calls" element={<ListCallsComponent />} />
          <Route path="/secret" element={<Secret />} />
        </ProtectedRoute>
      </Routes>
    </AuthProvider>
   );*/}//
{/*
  return (
    <AuthProvider>
      <Routes>
        <Route path="/" element={<HomeComponent />} />
        <Route path="/login" element={<LoginComponent />} />
        <Route path="/secret" element={<ProtectedRoute><Secret /></ProtectedRoute>} />
        {user && (
          <>
            <Route path="/dashboard" element={<ProtectedRoute><DashboardComponent /></ProtectedRoute>} />
            <Route path="/make-call" element={<ProtectedRoute><CallComponent /></ProtectedRoute>} />
            <Route path="/calls" element={<ProtectedRoute><ListCallsComponent /></ProtectedRoute>} />
          </>
        )}
      </Routes>
    </AuthProvider>
  );*/}
////};

//export default App;


/*
const App = () => {
   const { user } = useAuth();
  return (
     <AuthProvider>
      <Routes>
        <Route path="/" element={<HomeComponent />} />
        <Route path="/login" element={<LoginComponent />} />
        <Route path="/register" element={<RegisterComponent />} />
        <Route path="/secret" element={<ProtectedRoute><Secret /></ProtectedRoute>} />
        {user && (
          <>
            <Route path="/dashboard" element={<ProtectedRoute><DashboardComponent /></ProtectedRoute>} />
            <Route path="/make-call" element={<ProtectedRoute><CallComponent /></ProtectedRoute>} />
            <Route path="/calls" element={<ProtectedRoute><ListCallsComponent /></ProtectedRoute>} />
          </>
        )}
        {/*
        <Route path="/dashboard" element={<DashboardComponent />} />
        
        <Route path="/make-call" element={<CallComponent />} />
        <Route path="/calls" element={<ListCallsComponent />} />
    
      <Route
          path="/secret"
          element={
            <ProtectedRoute>
              <Secret />
            </ProtectedRoute>
          }
        />
      *///}
      /*
      </Routes>
    </AuthProvider>
  );
};

export default App;

*/

/*

function App() {
  return (
    <AuthProvider>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginComponent />} />
        <Route
          path="/secret"
          element={
            <ProtectedRoute>
              <Secret />
            </ProtectedRoute>
          }
        />
      </Routes>
    </AuthProvider>
  );
}
*/
/*
function App() {
  // State to track user authentication status
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  // Function to update authentication status after successful login
  const handleLoginSuccess = () => {
    setIsAuthenticated(true);
  };

  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<HomeComponent />} />
          <Route path="/login" element={<LoginComponent onLoginSuccess={handleLoginSuccess} />} />
          {/* ProtectedRoute for dashboard accessible only if authenticated *///}
          /*
          <ProtectedRoute path="/dashboard" element={isAuthenticated ? <DashboardComponent /> : <Navigate to="/login" />} />
          {/* ProtectedRoute for secret accessible only if authenticated *///}
          /*
          <Route path="/secret" element={<ProtectedRoute><Secret /></ProtectedRoute>} />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
*/
/*
function App() {
  // State to track user authentication status
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  // Function to update authentication status after successful login
  const handleLoginSuccess = () => {
    setIsAuthenticated(true);
  };
/*
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<HomeComponent />} />
          <Route path="/login" element={<LoginComponent onLoginSuccess={handleLoginSuccess} />} />
          {/* ProtectedRoute for dashboard accessible only if authenticated *///}
          
/*
{
  isAuthenticated ? (
            <Route path="/dashboard" element={<DashboardComponent />} />
          ) : (
            <Navigate to="/login" />
          )}
          {/* ProtectedRoute for secret accessible only if authenticated *///}
  /*        
<Route
            path="/secret"
            element={
              <ProtectedRoute>
                <Secret />
              </ProtectedRoute>
            }
          />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
* /

/*
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import LoginComponent from './components/LoginComponent';
import DashboardComponent from './components/DasboardComponent';


import RegisterComponent from './components/RegisterComponent';
import HomeComponent from './components/HomeComponent';
*/
/*
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import DashboardComponent from './components/DasboardComponent';//
import HomeComponent from './components/HomeComponent';
import LoginComponent from './components/LoginComponent';
import ListCallsComponent from './components/ListCallsComponent';
import CallComponent from './components/CallComponent';
import { AuthProvider } from './hooks/useAuth'; // Update the import path
import { ProtectedRoute } from "../ProtectedRoute";
import { Secret } from "../Secret";

/*
function App() {
  return (
    <AuthProvider>
      <Routes>
        <Route path="/" element={<HomeComponent />} />
        <Route path="/login" element={<LoginComponent />} />
        <Route path="/dashboard" element={<DashboardComponent />} />
        <Route
          path="/secret"
          element={
            <ProtectedRoute>
              <Secret />
            </ProtectedRoute>
          }
        />
      </Routes>
    </AuthProvider>
  );
}

export default App;

*/
/*
function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<HomeComponent />} />
          <Route path="/login" element={<LoginComponent />} />
          <Route path="/dashboard" element={<DashboardComponent />} />
          <Route
          path="/secret"
          element={
            <ProtectedRoute>
              <Secret />
            </ProtectedRoute>
          }
        />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
*/
/*
function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<HomeComponent />} />
          <Route path="/login" element={<LoginComponent />} />
          <Route path="/dashboard" element={<DashboardComponent />} />
          <Route
            path="/secret"
            element={
              <ProtectedRoute>
                <Secret />
              </ProtectedRoute>
            }
          />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
*/