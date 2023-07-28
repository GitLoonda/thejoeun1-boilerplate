import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

function SignUpForm () {

  let nav = useNavigate();
  
  const [data, setData] = useState({
    email: '',
    password: '',
    nickname: ''
  });

  const onChangeValue = (e) => {
    setData({
      ...data,
      [e.target.name]: e.target.value
    });
  }

  const signUp = async (e) => {
    e.preventDefault();
    await fetch('/api/auth/signup', {
      method: "POST",
      headers: {
        "Content-Type":"application/json",
      },
      body: JSON.stringify(data)
    })
    .then(res => {
      console.log(res);
      if(res && res.status === 200) {
        alert("회원가입 완료되었습니다.");
        nav('/login');
      } else {
        return alert("회원가입 실패하였습니다.");
      }
    });
  }


  return (
    <section className="d-flex vh-100" style={{ backgroundColor: "rgb(33,37,41)" }}>
      <div className="container-fluid row justify-content-center align-content-center">
        <div className="card bg-dark" style={{borderRadius: '1rem'}}>
          <div className="card-body p-5 text-center">
            <h2 className="text-white">회원가입</h2>
            <p className="text-white-50 mt-2 mb-5">서비스 사용을 위해서 회원가입을 해주세요</p>
            <div className="mb-2">
              <form onSubmit={signUp}>
                <div className="mb-3">
                  <label className="form-label text-white">Email address</label>
                  <input type="email" className="form-control" name="email" onChange={(e) => onChangeValue(e)} />
                </div>
                <div className="mb-3">
                  <label className="form-label text-white">Password</label>
                  <input type="password" className="form-control" name="password" onChange={(e) => onChangeValue(e)} />
                </div>
                <div className="mb-3">
                  <label className="form-label text-white">Nickname</label>
                  <input type="text" className="form-control" name="nickname" onChange={(e) => onChangeValue(e)} />
                </div>
                <button type="submit" className="btn btn-primary">회원가입</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default SignUpForm;