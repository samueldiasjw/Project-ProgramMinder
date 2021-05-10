import React from "react";
import { Image } from "react-bootstrap";
import styles from "./Navbar.module.css";
import { Navbar, Nav } from "react-bootstrap";
import code from "../../commons/Assets/imagemLogo.png";
//import { Link } from "react-scroll";

export default function navbar() {
  function createCookie(name,value,days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }else 
      var expires = "";
      document.cookie = name+"="+value+expires+"; path=/";
    }
    
  function eraseCookie(name) {
    createCookie(name,"",-1);
  }

  function logout(){
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++)
      eraseCookie(cookies[i].split("=")[0]);
    // fetch("http://localhost:8080/api/auth/logout", {
    //   method: "POST",
    //   headers: {
    //     "Content-type": "application/json",
    //   },
    //   body: JSON.stringify({
    //     userId: 1,
    //     userName: "Samuel",
    //     avatarName: "Samuel.Mindera.School" ,
    //     userRole: "STAFF"
    //   }),
    // }).then((response) => {
    //     response.headers.forEach(value => {console.log(value)})
    //     if (response.status !== 200) {
    //       alert("ERROOO")
    //     } else {
    //       return response.json();
    //     }
    //   })
    //   .then((data) => {
    //     document.cookie = "";
    //   })
    //   .catch((error) => {
    //     alert(error);
    //   });
  }

  return (
    <Navbar fixed="top" bg="light" expand="lg" className={styles.NavBar}>
      <Navbar.Brand className={styles.Logo}>
        <a href="/" className={styles.Brand} style={{ display:"flex" }}>
          Program
          <span style={{ color: "#008dc8" }}>Minder</span>
          <Image src={code} alt="Logo" style={{ maxHeight: "39px", alignSelf: "center", marginLeft: "10px"  }} />
        </a>
      </Navbar.Brand>
      <Navbar.Toggle className={styles.Toggle} aria-controls="responsive-navbar-nav" />
      <Navbar.Collapse id="responsive-navbar-nav"  className={styles.Links}>
        <Nav className="ml-auto">
          {window.location.href === "http://0.0.0.0:3000/"  ?
            <>
              <Nav.Link href="/#home"  >Home</Nav.Link>
              <Nav.Link href="/#about" >About Us</Nav.Link>
              <Nav.Link href="/#reviews" >Why People Give Up?</Nav.Link>
              <Nav.Link href="/#contact" >Contact Us</Nav.Link>
            </>
          : 
            <>
              <Nav.Link href="/login/categorys"  >Categorys</Nav.Link>
              <Nav.Link href={`/account/myaccount`} >Account</Nav.Link>
              <Nav.Link href="/" onClick={logout} >Logout</Nav.Link>
            </>
          }
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
}
