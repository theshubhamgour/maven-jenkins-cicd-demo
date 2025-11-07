# 🚀 Maven Jenkins CI/CD Demo

A simple **Java Maven project** integrated with **Jenkins CI/CD** and **DockerHub** for demonstrating a full DevOps pipeline — from code build and testing to Docker image deployment.

🎥 **Watch the complete tutorial here:**  
👉 [YouTube: #theshubhamgour](https://www.youtube.com/@theshubhamgour)

---

## 🧱 Project Overview

This project demonstrates:
- ✅ Building a Java Maven application  
- ✅ Running JUnit tests  
- ✅ Packaging as an executable JAR  
- ✅ Building and pushing Docker images to DockerHub  
- ✅ Automating everything with Jenkins Pipeline  

Perfect for **DevOps, Release, and Cloud Engineers** who want to learn real-world CI/CD practices.

---

## 📁 Project Structure

```
maven-jenkins-cicd-demo/
├── src/
│   ├── main/java/com/example/demo/Application.java
│   └── test/java/com/example/demo/ApplicationTests.java
├── pom.xml
├── Dockerfile
└── Jenkinsfile
```

---

## ⚙️ Tech Stack

| Tool | Purpose |
|------|----------|
| **Java 17** | Core programming language |
| **Maven** | Build automation and dependency management |
| **JUnit 4** | Unit testing framework |
| **Docker** | Containerization |
| **Jenkins** | CI/CD automation |
| **GitHub** | Source control and SCM integration |

---

## 🧩 Pipeline Stages

The Jenkins pipeline includes:
1. **Checkout** – Pull code from GitHub  
2. **Clean Workspace** – Remove old builds  
3. **Build** – Compile Java source files  
4. **Unit Tests** – Run JUnit tests and publish results  
5. **Package** – Create executable JAR  
6. **Static Code Analysis (Optional)** – Example quality check  
7. **Docker Build** – Create Docker image  
8. **Docker Push** – Push image to DockerHub  
9. **Deploy** – Run the container for testing  
10. **Post-Deployment Check** – Validate app is live  
11. **Cleanup** – Stop and remove temporary containers/images  

---

## 🐳 Docker Setup

Build and run manually:

```bash
docker build -t theshubhamgour/maven-demo:latest .
docker run --rm -p 8080:8080 theshubhamgour/maven-demo:latest
```

Output:
```
Hello Jenkins!
```

---

## 🧰 Run Locally

```bash
mvn clean package
java -jar target/demo-1.0-SNAPSHOT.jar
```

Expected output:
```
Hello Jenkins!
```

---

## 🧑‍💻 Jenkins Setup

1. Push code to GitHub  
2. Create a **Jenkins Pipeline job** using the provided `Jenkinsfile`  
3. Add **DockerHub credentials** in Jenkins → `dockerhub-login`  
4. Build the pipeline → Jenkins handles everything end-to-end  

---

## 🔐 Credentials Required

| ID | Type | Description |
|----|------|-------------|
| `dockerhub-login` | Username/Password | For DockerHub authentication |

---

## 📹 YouTube Tutorial

🎬 Learn the entire setup, from Maven to Jenkins pipeline, in this detailed walkthrough:  
➡️ [YouTube: #theshubhamgour](https://www.youtube.com/@theshubhamgour)

---

## 🧾 License
This project is licensed under the **MIT License** — free to use, modify, and share.

---

> 💡 Created with ❤️ by **Shubham Gour**  
> For DevOps tutorials, follow [#theshubhamgour](https://www.youtube.com/@theshubhamgour)
