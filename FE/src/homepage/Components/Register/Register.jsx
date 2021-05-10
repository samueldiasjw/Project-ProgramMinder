import React, { useState } from "react";
import { Jumbotron, Container, Image, Alert } from "react-bootstrap";
import styles from "./Register.module.css";
import code from "../../../commons/Assets/imagemLogo.png";

export default function Register() {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [avatarName, setAvatarName] = useState("");
  const [photo, setPhoto] = useState("");
  const [description, setDescription] = useState("");
  const [hasSubmissionSucceed, setHasSubmissionSucceed] = useState(false);
  const [error, setError] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = async (evt) => {
    evt.preventDefault();

    if (name && password && email && avatarName) {
      try {
        const resp__raw = await fetch("http://localhost:8080/users", {
          origin: "*",
          method: "POST",
          body: JSON.stringify({
            username: `${name}`,
            password: `${password}`,
            email: `${email}`,
            avatarName: `${avatarName}`,
            photo: `${photo}`,
            description: `${description}`
          }),
          headers: {
            "Content-Type": "application/json",
          },
        });


        if (!resp__raw.ok) {
          setError(true)
          setHasSubmissionSucceed(false)
          resp__raw.text().then(text => { setErrorMessage(JSON.parse(text).message) })
        } else {
          setHasSubmissionSucceed(true)
          setError(false)
          document.querySelectorAll("input").forEach(
            input => (
              input.value !== "Submit" ? input.value = "" : input.value = "Submit")
          );
          setName("");
          setPassword("");
          setEmail("");
          setAvatarName("");
          setPhoto("");
          setDescription("");
        }
      } catch (err) {
        setError(true)
        setErrorMessage("Error Creating Account")
      }
    } else {
      setError(true)
      setErrorMessage("Error Creating Account")
    }


  }


  return (
    <Jumbotron id="about" className={styles.Jumbotron} fluid>
      <Container>
        <div className={styles.card}>
          <h1 className={styles.heading}>
            <span className={styles.Us}>Register</span>
            <Image src={code} alt="Logo" style={{ maxHeight: "36px", alignSelf: "center", marginLeft: "10px", paddingBottom: "4px" }} />
          </h1>
          {hasSubmissionSucceed && (
            <Alert variant="success">
              Successfully
            </Alert>
          )}

          {error && (
            <Alert variant="danger">{errorMessage}</Alert>
          )}
          <form onSubmit={handleSubmit} className={styles.form}>
            <label>Username:</label>
            <input
              type="text"
              required="required"
              value={name}
              onChange={e => setName(e.target.value)}
            />

            <label>Password:</label>
            <input
              type="password"
              required="required"
              value={password}
              onChange={e => setPassword(e.target.value)}
            />

            <label>Email:</label>
            <input
              type="email"
              value={email}
              required="required"
              onChange={e => setEmail(e.target.value)}
            />

            <label>
              Avatar Name:
              </label>
            <input
              type="text"
              required="required"
              value={avatarName}
              onChange={e => setAvatarName(e.target.value)}
            />

            <label>Photo:</label>
            <input
              type="text"
              value={photo}
              onChange={e => setPhoto(e.target.value)}
            />

            <input id="upload" type="file" accept="image/*"
              onChange={(event) => {
                var myHeaders = new Headers();
                myHeaders.append("Authorization", "Client-ID eae95290510293a");
                
                var formdata = new FormData();
                formdata.append("image", event.target.files[0]);
                
                var requestOptions = {
                  method: 'POST',
                  headers: myHeaders,
                  body: formdata,
                  redirect: 'follow'
                };
                
                fetch("https://api.imgur.com/3/image", requestOptions)
                  .then(response => response.json())
                  .then(result => setPhoto(result.data.link))
                  .catch(error => console.log('error', error));
              }}
            />

            <label>Description:</label>
            <textarea
              className={styles.description}
              type="text"
              value={description}
              onChange={e => setDescription(e.target.value)}
            />

            <input className={styles.buttonSubmit} type="submit" value="Submit" />
          </form>
        </div>
      </Container>
      <div className={styles.lineGrade} ></div>
    </Jumbotron>
  );
}
