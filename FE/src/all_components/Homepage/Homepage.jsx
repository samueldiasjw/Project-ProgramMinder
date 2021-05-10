import React, { Component } from "react";
import Banner from "../../homepage/Components/Banner/Banner";
import AboutUs from "../../homepage/Components/AboutUs/AboutUs";
import Testimonial from "../../homepage/Components/Testimonial/Testimonial";
import QuestionsForm from "../../homepage/Components/QuestionsForm/QuestionsForm";

export default class Homepage extends Component {
  render() {
    return (
      <div>
        <Banner />
        <AboutUs />
        <Testimonial />
        <QuestionsForm />
      </div>
    );
  }
}
