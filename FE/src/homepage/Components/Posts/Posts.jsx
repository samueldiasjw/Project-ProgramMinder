import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { Jumbotron, Container, Image} from "react-bootstrap";
import styles from "./Posts.module.css";

import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import parse from "html-react-parser";

import { useSelector } from "react-redux";

import NotAccess from "../NotAccess/NotAccess"


export default function Posts() {
  const { id } = useParams();
  const [posts, setPosts] = useState([]);
  const [thread, setThread] = useState([]);
  const [users, setUsers] = useState([]);
  const [text, setText] = useState("");


  const [editorDeTexto, setEditorDeTexto] = useState("");

  const user = useSelector(store => store.users);
  


  var myHeaders = new Headers();
  myHeaders.append("Authorization", document.cookie.split("token=")[1]);

  var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
  };


  useEffect( () => {
    fetch(`http://localhost:8080/posts/${id}`, requestOptions)
    .then((response) => {
      return response.json()
    })
    .then((data) => {
      setPosts(data || [])
    }) 
    .catch ((error) =>{
        console.log("ERRRORRR")
    })

    fetch(`http://localhost:8080/threads/${id}`, requestOptions)
        .then((response) => {
          return response.json()
        })
        .then((data) => {
          setThread(data || [])
        })

    fetch(`http://localhost:8080/users/thread/${id}`, requestOptions)
        .then((response) => {
          return response.json()
        })
        .then((data) => {
          setUsers(data || [])
        })
    
  }, [id])

  function sendPost(e) {
    e.preventDefault();


    var postHeader = new Headers();
    postHeader.append("Content-Type", "application/json");
    postHeader.append("Authorization", document.cookie.split("token=")[1]);
    
    var raw = JSON.stringify({"post": text,"userId":user.userId,"threadId":id});
    
    var postRequestOptions = {
      method: 'POST',
      headers: postHeader,
      body: raw,
      redirect: 'follow'
    };
    
    fetch("http://localhost:8080/posts", postRequestOptions)
      .then(response => response.text())
      .then(result => {
        editorDeTexto.setData("");
        setText("")
        fetch(`http://localhost:8080/posts/${id}`, requestOptions)
          .then((response) => {
            return response.json()
          })
          .then((data) => {
            setPosts(data || [])
          }) 
          .catch ((error) =>{
              console.log("ERRRORRR")
          })

        fetch(`http://localhost:8080/threads/${id}`, requestOptions)
          .then((response) => {
            return response.json()
          })
          .then((data) => {
            setThread(data || [])
          })
  
        fetch(`http://localhost:8080/users/${id}`, requestOptions)
          .then((response) => {
            return response.json()
          })
          .then((data) => {
            setUsers(data || [])
          })
      })
      .catch(error => console.log('error', error));
  }

  function quote(myString){
    editorDeTexto.setData("<blockquote><p>" + myString + "</p></blockquote><p>&nbsp;</p>");
    setText("<blockquote><p>" + myString + "</p></blockquote><p>&nbsp;</p>");
  }

  return (
    <Jumbotron id="about" className={styles.Jumbotron} fluid>
      <Container>
        {document.cookie.split("token=")[1] ?
          <div className={styles.card}>
          <h1 className={styles.heading}>
            <span key={thread.threadId} className={styles.Us}>{thread.title}</span>
          </h1>
                <div key={thread.threadId} className={styles.cardPost}>
                  <div className={styles.userInformation}>
                    <Image src={users[0] !== undefined ? users[0].photo : ""} alt="Account Image" className={styles.image}></Image>
                    {users[0] !== undefined ? <p className={styles.userInfo}> <Link className={styles.link} to={`/account/${users[0].userId}`}>{users[0].avatarName}</Link> <br/> <span className={styles.tagRole + ' ' + styles[users[0].role]}>{users[0].role}</span> </p> : ""}
                  </div>
                  <div>
                    <p className={styles.postText}>{parse("" + thread.post)}</p>
                  </div>
                  <div>
                    <a href = "#reply" className = {styles.Reply} onClick={() => quote("<b>" + users[0].avatarName + " say:</b><p>" + thread.post + "</p>")}>
                      &#8220;Reply
                    </a>
                  </div>
                </div>

            {posts.length > 0 ? posts.map((post, index) =>{
              {index= index + 1}
              return(
                <div key={post.postId} className={styles.cardPost}>
                  <div className={styles.userInformation}>
                    <Image src={users[index] !== undefined ? users[index].photo : ""} alt="Account Image" className={styles.image}></Image>
                    {users[index] !== undefined ? <p className={styles.userInfo}><Link className={styles.link} to={`/account/${users[index].userId}`}>{users[index].avatarName}</Link> <br/> <span className={styles.tagRole + ' ' + styles[users[index].role]}>{users[index].role}</span> </p> : ""}
                  </div>
                  <div>
                    <p className={styles.postText}>{parse(post.post)}</p>
                  </div>
                  <div>
                    <a href = "#reply" className = {styles.Reply} onClick={() => quote("<b>" + users[index].avatarName + " say:</b><p>" + post.post + "</p>")}>
                      &#8220;Reply
                    </a>
                  </div>
                </div>
              )
            }):console.log("DONT HAVE MORE POSTS")
            }

            <a name="reply"></a>

            <div className = {styles.richtext}>
              <CKEditor
                      editor={ ClassicEditor }
                      data="<p>Write your reply...</p>"
                      onReady={ editor => {
                        console.log(editor.ui.view.toolbar);
                        setEditorDeTexto(editor);
                      }}
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
              <a href = "#" className = {styles.createButton} onClick={sendPost}>
                + Post Reply
              </a>
            </div>
        </div> : <NotAccess/>
        }
        
      </Container>
      <div className={styles.lineGrade} ></div>
    </Jumbotron>
  );
}

