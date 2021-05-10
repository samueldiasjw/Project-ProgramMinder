import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Jumbotron, Container, Image} from "react-bootstrap";
import styles from "./Category.module.css";

import NotAccess from "../NotAccess/NotAccess"


export default function Category() {
  const [categorys, setCategorys] = useState([]);

  var myHeaders = new Headers();
  myHeaders.append("Authorization", document.cookie.split("token=")[1]);
  
  var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
  };

  useEffect( () => {
    fetch("http://localhost:8080/categorys/", requestOptions)
      .then((response) => {
        return response.json()
      })
      .then((data) => {
        setCategorys(data || [])
      })
  }, [])

  return (
    <Jumbotron id="about" className={styles.Jumbotron} fluid>
      {document.cookie.split("token=")[1] ? 
      <Container>
        <div className={styles.card}>
          <h1 className={styles.heading}>
            <span className={styles.Us}>Main Category</span>
          </h1>
          <div className={styles.links}>
            {categorys.map((category) =>{
              return(
                <div key={category.categoryId} className={styles.linkWithImage}>
                  <Image src="https://i.imgur.com/yU6MkEg.png" alt="Logo" style={{ maxHeight: "36px", alignSelf: "center", marginLeft: "10px", paddingBottom: "4px"  }}></Image>
                  <Link className={styles.categoryLink} to={`/category/${category.categoryId}`} key={category.categoryId}>{category.name} <p>({category.description})</p></Link>
                </div>
              )
            })}
          </div>
        </div>
      </Container>
        : <NotAccess/> }
      <div className={styles.lineGrade} ></div>
    </Jumbotron>
  );
}
