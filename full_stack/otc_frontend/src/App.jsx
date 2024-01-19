
import './App.css'
import FooterComponent from './components/FooterComponent';
import HeaderComponet from './components/HeaderComponent';
import HomeComponent from './components/HomeComponent';
import ListCallsComponent from "./components/ListCallsComponent";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginComponent from './components/LoginComponent';
import UserComponent from './components/UserComponent';
import AdminComponent from './components/AdminComponent';
import CallComponent from './components/CallComponent';

function App() {
  

  return (
    <>
      <BrowserRouter>
        <HeaderComponet />
          <Routes>
            <Route path='/' element = {<HomeComponent />}></Route>
            <Route path='/login' element = {<LoginComponent />}></Route>
            <Route path='/user' element = {<UserComponent />}></Route>
            <Route path='/calls' element = {<AdminComponent />}></Route>
            <Route path='/make-call' element = {<CallComponent />}></Route>
          </Routes>
      
        <FooterComponent />
      </BrowserRouter>
    </>
  )
}

export default App
