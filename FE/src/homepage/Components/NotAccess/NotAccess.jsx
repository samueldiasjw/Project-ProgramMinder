import React from "react";
import { Jumbotron, Container, Image} from "react-bootstrap";
import styles from "../Register/Register.module.css";
import { Link } from "react-router-dom";


export default function NotAccess() {
  return (
    <Jumbotron id="about" className={styles.Jumbotron} fluid>
      <Container className={styles.NotAccess}>
          <div className={styles.cardForbbiden}>
            <h1 className={styles.heading}>
              <Image src="https://i.imgur.com/JX6k564.png" alt="Logo" style={{ maxHeight: "180px", marginBottom: "25px" }}></Image><br/>
              <span className={styles.NotAccessText}>You Don't Have Acess! Need to <Link className={styles.LogginButton} to={"/login"}> Login First</Link></span>
            </h1>
          </div>
      </Container>
    </Jumbotron>
  );
}

