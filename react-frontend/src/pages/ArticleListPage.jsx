import ArticleListForm from "../components/ArticleListForm";

import '../App.scss'
import styled, { css } from 'styled-components';

const Button = styled.button`
  background: transparent;
  ${props => props.dark && css`
    background: black;
    color: white;
  `}
`;

const Button1 = styled.button`
  background: black;
  border: none;
  color: white;
  outline: none;

  // sample1
  ${props => props.add && css`
        background: green;
        color: white;
  `};
  ${props => props.delete && css`
      background: purple;
      color: white;
  `};

  // sample2
  // ${(props) => {
  //   if (props.add) {
  //       return 'background: green;'
  //   } else if (props.delete) {
  //       return 'background: red;'
  //   } else {
  //       return 'background: black;'
  //   }
}}
`;
const Parent = styled.div`
  display: flex;
  // display: inline;
`;

const ChlidBoxOne = styled.div`
  background-color: pink;
  width: 100px;
  height: 100px;
`;

const ChlidBoxTwo = styled.div`
  background-color: black;
  width: 100px;
  height: 100px;
  color: yellow;
`;

const ArticleListPage = () => {
  return (
    <>
      <div style={{ display: "flex" }}>
        <div style={{ backgroundColor: "pink", width: 100, height: 100 }}>1234</div>
        <div style={{ backgroundColor: "yellow", width: 100, height: 100 }}>456</div>
      </div>
      <Parent>
        <ChlidBoxOne>블로그기사1</ChlidBoxOne>
        <ChlidBoxTwo>블로그기사2</ChlidBoxTwo>
      </Parent>
      <Button>Normal</Button>
      <Button dark>Dark</Button>
      <Button1 delete>Delete</Button1>
      <Button1 add>Add</Button1>
    </>
  );
};

export default ArticleListPage;