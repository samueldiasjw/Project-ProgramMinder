import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { Jumbotron, Container, Image} from "react-bootstrap";
import styles from "./Account.module.css";
import parse from "html-react-parser";

import { useSelector } from "react-redux";

import NotAccess from "../NotAccess/NotAccess"


export default function Account () {
  let { id } = useParams();
  const [user, setUser] = useState([]);
  const [threads, setThreads] = useState([]);

  const userResquestingAccount = useSelector(store => store.users);

  if (id === "myaccount"){
    id = userResquestingAccount.userId
  }

  var myHeaders = new Headers();
  myHeaders.append("Authorization", document.cookie.split("token=")[1]);
  
  var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
  };

  useEffect( () => {
    fetch(`http://localhost:8080/users/${id}`, requestOptions)
      .then((response) => {
        return response.json()
      })
      .then((data) => {
        setUser(data || [])
      })

    fetch(`http://localhost:8080/threads/user/${id}`, requestOptions)
      .then((response) => {
        return response.json()
      })
      .then((data) => {
        setThreads(data || [])
      })
  }, [id])

  return (
    <Jumbotron id="about" className={styles.Jumbotron} fluid>
      {document.cookie.split("token=")[1] ? 
      <Container>
        <div className={styles.card}>
          <h1 className={styles.heading}>
            <span className={styles.Us}>Account</span>
          </h1>
          <div className={styles.accountidentifiers}>
            <div key={user.id} className={styles.linkWithImage}>
              <div className={styles.accountImage}>
                <Image src={user.photo} alt="Logo" style={{ maxHeight: "120px", maxWidth: "120px" , alignSelf: "center", borderRadius: "50%", border: "2px solid white"  }}></Image>
              </div>
              <div className={styles.identifiersText}>
                <div className={styles.emailAndAvatar}>
                  <h3 className={styles.title}>Email - <p>{user.email}</p></h3>
                  <h3 className={styles.title}>Avatar Name - <p>{user.avatarName}</p></h3>
                </div>
                <h3 className={styles.title}>Description - <p>{user.description}</p></h3>
              </div>
            </div>
          </div>
          {threads.length > 0 ? 
            <div className={styles.allThreadsByUser}>
              <h1 className={styles.heading}>
                <span className={styles.Us}>User Threads</span>
              </h1>
                {threads.map((thread) =>{
                  return(
                    <div className={styles.accountThreads}>
                      <div key={thread.threadId} className={styles.linkWithImage}>
                        <Link className={styles.threadLink} to={`/post/${thread.threadId}`} key={thread.threadId}>{thread.title} <p>{parse(thread.post)}</p></Link>
                      </div>
                    </div>
                  )
                })}
            </div>
          : console.log("Without Threads")}
        </div>
      </Container>
        : <NotAccess/> }
      <div className={styles.lineGrade} ></div>
    </Jumbotron>
  );
}
