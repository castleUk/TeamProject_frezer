import React, { useCallback, useState } from 'react';
// template
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import Form from 'react-bootstrap/Form';
// icon
import { AiFillDelete } from "react-icons/ai";
import { BiMinus } from "react-icons/bi";
import { BsPlus } from "react-icons/bs";
// component

const Item = ({filterItemList, onFreezerItemAdd, hide}) => {
  const [count, setCount]  = useState(1);
  const [storage, setStorage] = useState("");
  const [expDate, setExpDate] = useState();
  const [regDate, setRegDate] = useState();


  
  const itemName = filterItemList.map(it=>it.name)


  console.log("itemName" + itemName)
  console.log("storage" + storage)


  const countPlusHandler = (e) => {
    e.preventDefault();
    setCount(count +1)
  }

  const countMinusHandler = (e) => {
    e.preventDefault();
    setCount(count -1)
  }

  const submitHandler = useCallback(
    (e) => {
      e.preventDefault();
      onFreezerItemAdd(itemName, count, storage, expDate, regDate);
    },
    [itemName, count, storage, expDate, regDate, onFreezerItemAdd]

  );

  const storageHandler = (e) => {
    e.preventDefault();
    setStorage(e.target.value);
  }

  const expDateHandler = (e) => {
    e.preventDefault();
    setExpDate(e.target.value);
  }
  console.log("유통기한" + expDate)
  const regDateHandler = (e) => {
    e.preventDefault();
    setRegDate(e.target.value);
  }
  console.log("등록일" + regDate)


  return(
    <div className='ingr'>
      <div className='ingr-img'>
        {/* <ItemImg /> */}
      </div>
      <div className='ingr-content'>
      {filterItemList.map((it) => {
          return (
              <div>
                이름 : {it.name}
              </div>
          );
        })}
        <div className='ingr-form'>
          <Form onSubmit={submitHandler}>
            <Form.Group className="mb-3 form-group" controlId="">
              <Form.Label>수량</Form.Label>
              <span className='count-text'>{count}</span>
              <ButtonGroup>
                <Button variant="outline-secondary" onClick={countPlusHandler}><BsPlus className="icon"/></Button>
                <Button variant="outline-secondary" onClick={countMinusHandler}><BiMinus className="icon" /></Button>
              </ButtonGroup>
            </Form.Group>
            <Form.Group className="mb-3 form-group" controlId="">
              <Form.Label>저장소</Form.Label>
              <Form.Select  name="" onChange={storageHandler} value={storage}>
                <option value="냉장">냉장</option>
                <option value="냉동">냉동</option>
                <option value="실온">실온</option>
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3 form-group" controlId="">
              <Form.Label>등록일</Form.Label>
              <Form.Control type="date" name=""  onChange={regDateHandler}/>
            </Form.Group>
            <Form.Group className="mb-3 form-group" controlId="">
              <Form.Label>유통기한</Form.Label>
              <Form.Control type="date"  name="" onChange={expDateHandler}/>
            </Form.Group>
            <Button variant="primary" type="submit" onClick={hide}>
            등록
          </Button>
          </Form>
        </div>
      </div>
    </div>
  );
}

export default Item;