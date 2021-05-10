import React from "react";
import { Container, Jumbotron, Row, Col } from "react-bootstrap";
import styles from "./Footer.module.css";
import { FaLinkedinIn, FaInstagram, FaTwitter, FaPhoneAlt, FaMapMarkedAlt } from "react-icons/fa";
import { BiCopyright } from "react-icons/bi"
import { GrMail } from "react-icons/gr";

function Footer() {
  return (
    <Jumbotron className = {styles.Jumbotron}>
        <Container className = {styles.Container}>
            <Row className = {styles.Top1}>
                <Col className = {styles.Column}>
                <h5 className = {styles.Border}> Offices </h5>
                    <Row>
                        <Col md = {10}>
                            <p> <FaMapMarkedAlt /> Rua das Francesinhas </p>
                        </Col>
                    </Row>
                    <Row>
                        <Col md = {10}>
                            <p> <FaMapMarkedAlt /> Rua das Batatas </p>
                        </Col>
                    </Row>
                </Col>
                <hr className = {styles.line2}/>
                <Col className = {styles.Column}>
                    <h5 className = {styles.Border}> Contacts </h5>
                    <Row>
                        <Col md = {10}>
                            <p> <FaPhoneAlt /> 937912345 </p>
                        </Col>
                    </Row>
                    <Row>
                        <Col md = {10}>
                            <p> <GrMail /> samuel@gmail.com</p>
                        </Col>
                    </Row>               
                </Col>
                <hr className = {styles.line2}/>
                <Col className = {styles.Column}>
                    <h5 className = {styles.Border}> Socials </h5>
                    <Row>
                        <Col>
                            <a href = "#" className = {styles.Shift}> <FaLinkedinIn /> </a>
                            <a href = "#" className = {styles.Shift}> <FaInstagram /> </a>
                            <a href = "#" className = {styles.Shift}> <FaTwitter /> </a>
                        </Col>  
                    </Row>
                    <Row>
                        <Col>
                            <p> <BiCopyright /> Copyright Samuel Dias</p>
                        </Col> 
                    </Row>
                </Col>
            </Row>
            <Row className = {styles.Top2}>
                <Col>
                    <h5 style ={{textDecoration: "underline"}}> Socials </h5>
                    <p> 
                        <a href = "#" className = {styles.Shift}> <FaLinkedinIn /> </a>
                        <a href = "#" className = {styles.Shift}> <FaInstagram /> </a>
                        <a href = "#" className = {styles.Shift}> <FaTwitter /> </a>
                    </p>
                </Col>
                <Col className = {styles.Map}>
                    <h5 style = {{textDecoration: "underline"}}> Offices </h5>
                    <Row>
                        <Col md = {10}>
                            <p> <FaMapMarkedAlt /> Rua das Francesinhas </p>
                        </Col>
                    </Row>
                    <Row>
                        <Col md = {10}>
                            <p> <FaMapMarkedAlt /> Rua das Batatas </p>
                        </Col>
                    </Row>
                </Col>
                <Col className = {styles.Map}>
                    <h5 className = {styles.Border}> Contacts </h5>
                    <Row>
                        <Col md = {10}>
                            <p> <FaPhoneAlt /> 937912345 </p>
                        </Col>
                    </Row>
                    <Row>
                        <Col md = {10}>
                            <p> <GrMail /> samuel@gmail.com</p>
                        </Col>
                    </Row>               
                </Col>
            </Row>
        </Container>
    </Jumbotron>
  );
}

export default Footer;