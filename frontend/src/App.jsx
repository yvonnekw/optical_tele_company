
import './App.css'
import FooterComponent from './components/FooterComponent';
import HeaderComponet from './components/HeaderComponent';
import HomeComponent from './components/HomeComponent';
import ListCallsComponent from "./components/ListCallsComponent";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginComponent from './components/LoginComponent';
//import UserComponent from './components/UserComponent';
//import AdminComponent from './components/AdminUIComponent';
import CallComponent from './components/CallComponent';
import RegisterComponent from './components/RegisterComponent';
import DashboardComponent from './components/DasboardComponent';

function App() {
  

  return (
    <>
      <BrowserRouter>
        <HeaderComponet />
          <Routes>
            <Route path='/' element = {<HomeComponent />}></Route>
            <Route path='/login' element = {<LoginComponent />}></Route>
            <Route path='/dashboard' element = {<DashboardComponent />}></Route>
            <Route path='/calls' element = {<ListCallsComponent />}></Route>
            <Route path='/make-call' element = {<CallComponent />}></Route>
            <Route path='/register' element = {<RegisterComponent />}></Route>
          </Routes>
      
        <FooterComponent />
      </BrowserRouter>
    </>
  )
}

export default App
