import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function LoginForm () {

  let nav = useNavigate();

  const [token, setToken]  = useState('');

  const [data, setData] = useState({
    email: '',
    password: ''
  })

  const onChangeValue = (e) => {
    setData({
      ...data,
      [e.target.name]: e.target.value
    });
  }

  async function login() {
    const option =  {
      url: '/api/auth/login',
      method: "POST",
      header: {
        "Content-Type":"application/json"
      },
      body: JSON.stringify(data)
    };
    await axios(option)
      .then(res => {
        if(res && res.status === 200) {
          alert("로그인 되었습니다.");
          setToken(res.accessToken);
          nav('/home');
        } else {
          alert("로그인 실패하였습니다.");
        }
      });
  }

  return (
    <section className="d-flex vh-100" style={{ backgroundColor: "rgb(33,37,41)" }}>
      <div className="container-fluid row justify-content-center align-content-center">
        <div className="card bg-dark" style={{borderRadius: '1rem'}}>
          <div className="card-body p-5 text-center">
            <h2 className="text-white">LOGIN</h2>
            <p className="text-white-50 mt-2 mb-5">서비스를 사용하려면 로그인을 해주세요!</p>
            <div className="mb-2">
              <form action="/login" method="POST">
                <div className="mb-3">
                  <label className="form-label text-white">Email address</label>
                  <input type="email" style={{}} className="form-control" name="email" onChange={(e) => onChangeValue(e)} />
                </div>
                <div className="mb-3">
                  <label className="form-label text-white">Password</label>
                  <input type="password" style={{}} className="form-control" name="password" onChange={(e) => onChangeValue(e)} />
                </div>
                <button type="submit" className="btn btn-primary" onClick={login}>로그인</button>
              </form>
              <br />
              <div>
                <a href="http://localhost:8080/oauth2/authorization/google">
                  <img src={`${process.env.PUBLIC_URL}/img/google3.png`} alt="google" />
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  )
}

export default LoginForm;