import React, { useState } from "react";
import { Jumbotron, Container, Image, Alert } from "react-bootstrap";
import styles from "../Register/Register.module.css";
import code from "../../../commons/Assets/imagemLogo.png";
import { useHistory } from "react-router-dom";

import { useDispatch } from "react-redux";
import { addLoggedDataPerson } from "../../../actions/users"


export default function Login() {
  const dispatch = useDispatch();

  const history = useHistory();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [errorMessage, setErrorMessage] = useState("");
  const [error, setError] = useState(false);

  if (document.cookie.split("token=")[1]){
    history.push("/login/categorys")
  }

  const handleSubmit = async (evt) => {
      evt.preventDefault();

      if (email && password) {
        fetch("http://localhost:8080/api/auth/login", {
          method: "POST",
          headers: {
            "Content-type": "application/json",
          },
          body: JSON.stringify({
            email,
            password
          }),
        }).then((response) => {
            response.headers.forEach(value => {console.log(value)})
            if (response.status !== 200) {
              setError(true)
              setErrorMessage("Wrong Credentials!")
            } else {
              setError(false)
              return response.json();
            }
          })
          .then((data) => {
            dispatch(addLoggedDataPerson(data.principalDto));
            document.cookie = "token=Bearer " + data.token;
            history.push("/login/categorys");
          })
          .catch(() => {
            setError(true)
            setErrorMessage("Something Went Wrong!")
            console.error("Something went wrong on your request");
          });
      }
      
  }


  return (
    <Jumbotron id="about" className={styles.Jumbotron} fluid>
      <Container>
        <div className={styles.card}>
          <h1 className={styles.heading}>
            <span className={styles.Us}>Login</span>
            <Image src={code} alt="Logo" style={{ maxHeight: "36px", alignSelf: "center", marginLeft: "10px", paddingBottom: "4px"  }} />
          </h1>

          {error && (
                <Alert variant="danger">{errorMessage}</Alert>
              )}

          <form onSubmit={handleSubmit} className={styles.form}>
            <label>Email:</label>
              <input
                type="email"
                value={email}
                required="required"
                onChange={e => setEmail(e.target.value)}
              />

            <label>Password:</label>
              <input
                type="password"
                required="required"
                value={password}
                onChange={e => setPassword(e.target.value)}
              />

            <input className={styles.buttonSubmit} type="submit" value="Submit" />
          </form>
        </div>
      </Container>
      <div className={styles.lineGrade} ></div>
    </Jumbotron>
  );
}
