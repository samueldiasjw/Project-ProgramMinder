import React from "react";
import styles from "./Banner.module.css";
import { Container, Row, Col, Jumbotron } from "react-bootstrap";

export default function Banner() {
  return (
    <Jumbotron id="home" className={styles.Jumbotron} fluid>
      <Container className={styles.container}>
        <Row>
          <Col md={6} lg={7}>
            <h1 className={styles.heading}>
            Programming is <br/> not just a world <br className={styles.align} /> It is a
              <span style={{ color: "#008dc8" }}> universe </span>
            </h1>
            <a href = "/login" className = {styles.Button}> 
              Login
            </a>
            <br className={styles.set} />
            <br className={styles.align} />
            <br className={styles.align} />
          </Col>

        </Row>
      </Container>
      <Container className={styles.containersmall} fluid>
        <h1 style={{ textAlign: "center" }} className={styles.heading}>
          Programming is <br/> not just a world <br className={styles.align} /> It is a
          <span style={{ color: "#008dc8" }}> universe </span>
        </h1>
        <br />
        <br />
        <a href = "/login" className = {styles.Button}> 
          Login
        </a>
      </Container>
    </Jumbotron>
  );
}
