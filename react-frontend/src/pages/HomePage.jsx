import React from "react";
import '../App.css'

const HomePage = () => {
  let isLogin = false;
  // if (cookie.token) {
  //   isLogin = true;
  // }
  if(window.localStorage.length !== 0) {
    isLogin = true;
  }

  return (
    <>
      {isLogin && <div>로그인 상태입니다. 로그아웃을 잊지 마세요.</div>}
      {!isLogin && <div>로그인 전입니다. 로그인을 해주세요.</div>}
    </>
  )
};

export default HomePage;