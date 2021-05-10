INSERT INTO users (users.`user_id`, users.`username`, users.`encrypted_password`, users.`email`, users.`avatar_name`, users.`photo`, users.`description`, users.`role`) VALUES (1,"Samuel", "$2a$12$geWR49mkGimn3eqiXmMoxeBL1732Fjk2Ukqn5LyMFtHMn0a.1SEGW","samuel83a@gmail.com", "Samuel.Mindera.School", "https://i.imgur.com/IDXDdJg.png", "Hello guys I am eighteen years old and I am studying in Mindera School.", "STAFF");
INSERT INTO users (users.`user_id`, users.`username`, users.`encrypted_password`, users.`email`, users.`avatar_name`, users.`photo`, users.`description`, users.`role`) VALUES (2,"Rafael", "$2a$12$geWR49mkGimn3eqiXmMoxeBL1732Fjk2Ukqn5LyMFtHMn0a.1SEGW","rafael@gmail.com", "Rafa.Mentor.Mindera", "https://i.imgur.com/J22ghXh.jpg", "I am a teachear in Mindera School and I have the best pupilo in the world.", "USER");
INSERT INTO users (users.`user_id`, users.`username`, users.`encrypted_password`, users.`email`, users.`avatar_name`, users.`photo`, users.`description`, users.`role`) VALUES (3,"JoaoFaustino", "$2a$12$geWR49mkGimn3eqiXmMoxeBL1732Fjk2Ukqn5LyMFtHMn0a.1SEGW","faustino@gmail.com", "Joao.Faustino.Mindera", "https://i.imgur.com/QVyjj8t.jpg", "I like potatoes", "USER");



INSERT INTO category_threads (category_threads.`category_id`, category_threads.`name`, category_threads.`description`) VALUES (1, "College", "Things about school");
INSERT INTO category_threads (category_threads.`category_id`, category_threads.`name`, category_threads.`description`) VALUES (2, "Information", "About computing area in general");
INSERT INTO category_threads (category_threads.`category_id`, category_threads.`name`, category_threads.`description`) VALUES (3, "Projects", "You have a good ideia and want to Share");
INSERT INTO category_threads (category_threads.`category_id`, category_threads.`name`, category_threads.`description`) VALUES (4, "Help", "Need help with your projects");



INSERT INTO threads (threads.`thread_id`, threads.`user_id`, threads.`category_id`, threads.`post`, threads.`title`) VALUES (1, 3, 1, "Hello guys I want to learn somethings about programming and potatoes. Do you know where can i do this?", "What is the best school to learn to program?");
INSERT INTO threads (threads.`thread_id`, threads.`user_id`, threads.`category_id`, threads.`post`, threads.`title`) VALUES (2, 1, 2, "I wanna know your opinion. Let the war begin", "Iphone 11 is worth it");
INSERT INTO threads (threads.`thread_id`, threads.`user_id`, threads.`category_id`, threads.`post`, threads.`title`) VALUES (3, 1, 3, "We believe there will be an added value on this 5 months course that will allow people to find new opportunities on the job market and get back the investment they have made on a short time frame. The monthly fee will be 600€ which makes a total of 3000€", "MindSwap");
INSERT INTO threads (threads.`thread_id`, threads.`user_id`, threads.`category_id`, threads.`post`, threads.`title`) VALUES (4, 1, 4, "I am looking for a good tutorial about it...", "Need help with React Router DOM");
INSERT INTO threads (threads.`thread_id`, threads.`user_id`, threads.`category_id`, threads.`post`, threads.`title`) VALUES (5, 1, 1, "I wanna know what you think about Instituto Superior de Engenharia do Porto", "What you think about ISEP?");
INSERT INTO threads (threads.`thread_id`, threads.`user_id`, threads.`category_id`, threads.`post`, threads.`title`) VALUES (6, 3, 1, "What countrys do you recommend? What i need to do? What documents do i need?", "Study Outside of Country?");



INSERT INTO posts (posts.`post_id`, posts.`post`, posts.`user_id`, posts.`thread_id`) VALUES (1, "Man you really need to try Mindera School", 1, 1);
INSERT INTO posts (posts.`post_id`, posts.`post`, posts.`user_id`, posts.`thread_id`) VALUES (2, "I agreee", 2, 1);
