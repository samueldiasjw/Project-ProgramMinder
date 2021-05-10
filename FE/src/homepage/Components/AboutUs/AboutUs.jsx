import React from "react";
import { Jumbotron, Container, Image, Row, Col } from "react-bootstrap";
import styles from "./AboutUs.module.css";
import AboutUsImg from "../../assets/AboutUsImg.png";

export default function AboutUs({ id }) {
  return (
    <Jumbotron id="about" className={styles.Jumbotron} fluid>
      <Container>
        <h1 className={styles.heading}>
          <span className={styles.About}>About</span>
          <span className={styles.Us}>Us</span>
        </h1>
        <Row>
          <div className={styles.aboutussection}>
            <Col md={"auto"} lg={7}>
              <Image className = {styles.AboutImage} src={AboutUsImg} fluid />
            </Col>
            <Col md={"auto"} lg={5}>
              <p className={styles.description}>
                We are a community that has an ambition for the world of technology. 
                People who seek to help others to understand how giant this world is and which aspect you can best fit into.
                Certainly because you got here, you already know something about computers. 
                Hopefully you will stay with us and enter or stay in this area after what you will see here.
                
                <br />
                <br />
                
                What Are you waiting for?

              </p>
              <br />
              <a href = "/Register" className = {styles.knowMore}>
                Register
              </a>
            </Col>
          </div>
        </Row>
      </Container>
    </Jumbotron>
  );
}
