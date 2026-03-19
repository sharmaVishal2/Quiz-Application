# Quiz_Application
A backend quiz system built using Spring Boot that allows users to practice Java and Python questions.

---

## 🧠 Features

* Add & manage questions
* Category-based quiz (Java / Python)
* Generate random quizzes
* Submit answers & get score
* REST APIs

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Maven

---

## ⚙️ Setup Instructions

1. Clone the repo

```bash
git clone https://github.com/your-username/quiz-app.git
```

2. Configure DB in `application.properties`

3. Run the project

```bash
mvn spring-boot:run
```

---

## 📌 API Endpoints

### Question APIs

* GET /questions
* POST /questions
* GET /questions/category/{category}

### Quiz APIs

* POST /quiz/generate
* POST /quiz/submit

---

## 📷 Future Enhancements

* Add frontend (React)
* User authentication (JWT)
* Leaderboard
* Timer-based quiz

---

## ⭐ Contribute

Feel free to fork and improve!

---
