import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { Jumbotron, Container, Image} from "react-bootstrap";
import styles from "../Category/Category.module.css";

import NotAccess from "../NotAccess/NotAccess";

export default function Threads() {
  const { id } = useParams();
  const [category, setCategory] = useState("");
  const [posts, setPosts] = useState([]);
  const [users, setUsers] = useState([]);

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
      document.cookie.split("token=")[1] ? setCategory(data[id - 1].name) : setCategory("");
    })

    fetch(`http://localhost:8080/threads/categoryid/${id}`, requestOptions)
      .then((response) => {
        return response.json()
      })
      .then((data) => {
        setPosts(data || [])
      })

    fetch(`http://localhost:8080/users/category/${id}`, requestOptions)
      .then((response) => {
        return response.json()
      })
      .then((data) => {
        setUsers(data || [])
      })
  }, [id] )


  return (
    <Jumbotron id="about" className={styles.Jumbotron} fluid>
      {document.cookie.split("token=")[1] ? 
      <Container>
        <div className={styles.card}>
          <h1 className={styles.heading}>
            <span className={styles.Us}>Threads {category}</span>
            <a href = {`/create/thread/${id}`} className = {styles.createButton}>
                + Create Post
            </a>
          </h1>
          <div className={styles.links}>
            {posts.map((post, index) =>{
              return(
                <div key={post.threadId} className={styles.linkWithImage}>
                  <Image src={users[index] !== undefined ? users[index].photo : ""} alt="Account Image" style={{ height: "36px", width: "36px", alignSelf: "center", marginLeft: "10px", paddingBottom: "4px", borderRadius: "50%"  }}></Image>
                  <Link className={styles.categoryLink} to={`/post/${post.threadId}`} key={post.threadId}>{post.title} <p>By: {users[index] !== undefined ? users[index].avatarName : ""}</p></Link>
                </div>
              )
            })}
          </div>
        </div>
      </Container>
        : <NotAccess/>}
      <div className={styles.lineGrade} ></div>
    </Jumbotron>
  );
}

