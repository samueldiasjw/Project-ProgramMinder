import React, { useEffect, useState } from "react";
import { useParams, useHistory } from "react-router-dom";
import { Jumbotron, Container, Image} from "react-bootstrap";
import styles from "../Posts/Posts.module.css";

import { useSelector } from "react-redux";

import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

import NotAccess from "../NotAccess/NotAccess"


export default function CreateThread() {
  const { id } = useParams();
  const history = useHistory();
  const [text, setText] = useState("");
  const [title, setTitle] = useState("");

  const userResquestingAccount = useSelector(store => store.users);

  function sendThread(e) {
    e.preventDefault();

    if(title !== "" && text !==""){
      var postHeader = new Headers();
      postHeader.append("Content-Type", "application/json");
      postHeader.append("Authorization", document.cookie.split("token=")[1]);
      
      var raw = JSON.stringify({"title": title,"post": text,"userId": userResquestingAccount.userId,"categoryId":id});
      
      var postRequestOptions = {
        method: 'POST',
        headers: postHeader,
        body: raw,
        redirect: 'follow'
      };
      
      fetch("http://localhost:8080/threads", postRequestOptions)
        .then(response => response.json())
        .then(result => {
          history.push(`/post/${result.threadId}`)
        })
        .catch(error => console.log('error', error));
    }
  }

  return (
    <Jumbotron id="about" className={styles.Jumbotron} fluid>
      <Container>
        {document.cookie.split("token=")[1] ?
          <div className={styles.card}>
          <h1 className={styles.heading}>
            <span className={styles.Us}>Create Thread</span>
          </h1>
            <div className = {styles.richtext}>
              <label>Title:</label>
              <input
                type="text"
                required="required"
                value={title}
                style={{ backgroundColor: "#3b3f48", border: "1px solid #556177", borderRadius: "4px", width: "100%", marginBottom: "20px", outline: "none", color: "white" }}
                onChange={e => setTitle(e.target.value)}
              />
              <CKEditor
                      editor={ ClassicEditor }
                      data="<p>Write your reply...</p>"
                      onChange={ ( event, editor ) => {
                        const data = editor.getData();

                        setText(data)
                      } }
                      onFocus={ ( event, editor ) => {
                          const data = editor.getData();

                          if(data === "<p>Write your reply...</p>"){
                            editor.setData("");
                            setText("")
                          }
                      } }
              />
              <a href = "#" className = {styles.createButton} onClick={sendThread}>
                + Post
              </a>
            </div>
        </div> : <NotAccess/>
        }
        
      </Container>
      <div className={styles.lineGrade} ></div>
    </Jumbotron>
  );
}

