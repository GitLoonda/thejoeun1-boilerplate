import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useCookies } from 'react-cookie';
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap/dist/js/bootstrap";

function MainNavigation () {
  const nav = useNavigate();
  const [cookie, setCookie, removeCookie] = useCookies(['token']);
  const [nickname, setNickname] = useState('');

  let isLogin = false;
  if (cookie.token) {
    isLogin = true;
  }

  useEffect(() => {
    if(isLogin) {
      fetch('/api/member/me', {
        method: 'GET',
        headers: {
          "Content-Type": 'appliation/json',
          "Authorization" : 'Bearer ' + cookie.token,
        },
      }).then(res => {
        if (!(res && res.status === 200)) {
          return alert('회원 정보 조회에 실패하였습니다.');
        }
        return res.json();
      }).then(data => {
        setNickname(data.nickname);
      });
    }
  }, [isLogin]);

  const logout = () => {
    removeCookie('token');
    alert("로그아웃 하였습니다.");
    nav("/");
  }

  return (
    <header>
      <nav className='navbar navbar-expand-sm bg-body-tertiary'>
        <div className="container-fluid">
          <Link to='/' className='navbar-brand'>LOGO</Link>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className='collapse navbar-collapse justify-content-between' id='navbarSupportedContent'>
            <ul className='navbar-nav'>
              <li className='nav-item'><Link to='/' className='nav-link'>Home</Link></li>
              <li className='nav-item'><Link to='/about' className='nav-link'>About</Link></li>
            </ul>
            <ul className='navbar-nav'>
            <li className='nav-item'>{!isLogin && <Link to='/login' className='nav-link'>Login</Link>}</li>
            <li className='nav-item'>{!isLogin && <Link to='/signup' className='nav-link'>SignUp</Link>}</li>
            <li className='nav-item'>{isLogin && <Link to='/profile' className='nav-link'>{nickname}님 환영합니다.</Link>}</li>
            <li className='nav-item'>{isLogin && <button onClick={logout}>Logout</button>}</li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
  );
}

export default MainNavigation;