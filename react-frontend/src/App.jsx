import { Routes, Route, useNavigate } from 'react-router-dom';
import { useContext, useEffect, useRef, useState } from 'react';
import './App.css';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';
import HomePage from './pages/HomePage';
import AboutPage from './pages/AboutPage';
import Layout from './layout/Layout';

function App() {

  const navigate = useNavigate();

  return (
    <Layout>
      <Routes>
        <Route path='/' element={<HomePage />} />
        <Route path='/about' element={<AboutPage />} />
        <Route path='/login' element={<LoginPage />} />
        <Route path='/signup' element={<SignUpPage />} />
      </Routes>
    </Layout>      
  );
}

export default App;
