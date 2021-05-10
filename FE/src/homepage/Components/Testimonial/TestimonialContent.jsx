import Img__Caty from "../../assets/people/photoCaty.jpeg";
import Img__Henrique from "../../assets/people/photoHenrique.jpeg";
import Img__Joana from "../../assets/people/photoJoana.jpeg";
import Img__Hugo from "../../assets/people/photoHugo.jpeg";


/*
 * this is not a react component
 */

const TestimonialContent = () => [
  {
    img: Img__Caty,
    name: "Catarina Pontes",
    summary: `It's hard to balance between the theorical and the pratical side of this area. 
    When you focus too much on the theorical part you'll have no experience and no idea how to perform it pratically. 
    On the other hand, when you focus too much on the pratical side you'll have lack of knowledge and, when confronted with a different situation, you won't have the skills to adapt yourself.`,
  },
  {
    img: Img__Henrique,
    name: "Henrique Resende",
    summary: `Programming opens several doors in the world of work. 
    However, not everyone is aware of whether or not they like to exercise programming for a long period of time, until the day they start working in this area.
    If you like it, don't give up, but persist`,
  },
  {
    img: Img__Joana,
    name: "Joana Bastos",
    summary: `In my opinion, many give up because programming is something that takes time to learn and it is necessary to have a certain taste and interest in the area. 
    As in all areas, some people learn faster than others. 
    When someone does not understand what they are trying to learn, it causes frustration and when there is no strong reason to continue, it can cause the person to give up.`,
  },
  {
    img: Img__Hugo,
    name: "Hugo Filipe",
    summary: `Nowadays we have so many paths that we can follow, that it can become a bit confused chosing wich one is the best for us. 
    s an ex programing student I started to dislike some of the contents that I was studiyng throught the course, but that didn't stop me from trying to find the thing that I really enjoy. 
    In your case it could be software developping, web developping, whatever it is, just make an effort to try to find the path that you really enjoy in our 
    computer world.`,
  }
];

export default TestimonialContent();
