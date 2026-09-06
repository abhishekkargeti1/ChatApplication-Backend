# 💬 Real-Time Chat Application — Backend

A scalable **Spring Boot backend for a real-time chat application**, providing REST APIs, WebSocket-based real-time communication, MongoDB persistence, Docker containerization, Jenkins CI/CD automation, and Kubernetes deployment.

The project demonstrates the complete journey from **application development to automated containerized deployment on Kubernetes**.

---

## 🚀 Project Overview

This backend provides the server-side functionality required for a real-time chat application.

The application uses:

* **Spring Boot** for backend APIs
* **Spring WebSocket** for real-time communication
* **MongoDB** for persistent data storage
* **Maven** for dependency management and build automation
* **Docker** for containerization
* **Jenkins** for CI/CD automation
* **Kubernetes** for container orchestration

The project also includes Kubernetes manifests for deploying the backend and MongoDB.

---

## 🏗️ Architecture

```text
                         ┌─────────────────────┐
                         │      Frontend       │
                         │    React + Vite     │
                         └──────────┬──────────┘
                                    │
                                    │ HTTP / WebSocket
                                    ▼
                         ┌─────────────────────┐
                         │   Spring Boot      │
                         │      Backend       │
                         │                     │
                         │  REST APIs         │
                         │  WebSocket         │
                         │  Business Logic    │
                         └──────────┬──────────┘
                                    │
                                    │ MongoDB Driver
                                    ▼
                         ┌─────────────────────┐
                         │      MongoDB        │
                         │    Persistent DB    │
                         └─────────────────────┘


                     CI/CD & Deployment Flow

 ┌──────────┐      ┌──────────┐      ┌──────────┐
 │  GitHub  │ ───► │ Jenkins  │ ───► │  Docker  │
 └──────────┘      └──────────┘      └────┬─────┘
                                          │
                                          ▼
                                   ┌──────────────┐
                                   │  Docker Hub  │
                                   └──────┬───────┘
                                          │
                                          ▼
                                   ┌──────────────┐
                                   │ Kubernetes   │
                                   │   Cluster    │
                                   └──────┬───────┘
                                          │
                            ┌─────────────┴─────────────┐
                            ▼                           ▼
                    ┌──────────────┐            ┌──────────────┐
                    │ Spring Boot  │            │   MongoDB    │
                    │ Deployment   │            │ StatefulSet  │
                    └──────────────┘            └──────────────┘
```

---

# ✨ Key Features

### 🔐 Backend APIs

Spring Boot is used to expose backend APIs for the chat application.

### 💬 Real-Time Communication

Spring WebSocket is used to support real-time communication between connected clients.

This allows messages and other real-time events to be delivered without requiring continuous HTTP polling.

### 🗄️ MongoDB Persistence

MongoDB is used as the application's NoSQL database for storing application data.

The project uses Spring Data MongoDB for database interaction.

### 🐳 Docker Containerization

The Spring Boot application is packaged into a Docker image using Eclipse Temurin Java 17.

The Docker image runs the packaged Spring Boot WAR application.

### 🔄 Jenkins CI/CD

The Jenkins pipeline automates:

1. Source code checkout
2. Maven build
3. Docker image creation
4. Docker image publishing
5. Kubernetes deployment
6. Deployment rollout verification
7. Kubernetes resource verification

### ☸️ Kubernetes Deployment

The application is deployed to Kubernetes using:

* Deployment
* Service
* StatefulSet
* Persistent storage
* Kubernetes Secret
* MongoDB Service

---

# 🛠️ Technology Stack

| Technology          | Purpose                         |
| ------------------- | ------------------------------- |
| Java 17             | Application development         |
| Spring Boot 3.5.0   | Backend framework               |
| Spring Web          | REST APIs                       |
| Spring WebSocket    | Real-time communication         |
| Spring Data MongoDB | MongoDB integration             |
| MongoDB             | NoSQL database                  |
| Maven               | Build and dependency management |
| Lombok              | Boilerplate code reduction      |
| Docker              | Containerization                |
| Docker Hub          | Container image registry        |
| Jenkins             | CI/CD automation                |
| Kubernetes          | Container orchestration         |
| Git & GitHub        | Version control                 |

The project's Maven configuration currently specifies Java 17, Spring Boot 3.5.0, Spring Web, Spring WebSocket, Spring Data MongoDB, Lombok and testing dependencies.

---

# 📂 Project Structure

```text
ChatApplication-Backend/
│
├── .mvn/
│   └── wrapper/
│
├── k8s/
│   ├── chatapp-backend-deployment.yml
│   ├── chatapp-backend-service.yml
│   ├── database-service.yml
│   ├── database-statefulset.yml
│   ├── mongodb-volume.yml
│   └── secret-config.yml
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── chatapp/
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.yml
│   │       └── application-prod.yml
│   │
│   └── test/
│
├── Dockerfile
├── Jenkinsfile
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md
```

The repository contains dedicated Kubernetes manifests for the backend deployment/service, MongoDB StatefulSet/service, persistent storage and application configuration.

---

# ⚙️ Prerequisites

Install the following before running the project locally:

* Java 17
* Maven
* MongoDB
* Git

For containerized deployment:

* Docker
* Docker Hub account

For Kubernetes deployment:

* Kubernetes cluster
* `kubectl`
* Jenkins
* Docker access from the Jenkins agent

---

# 💻 Running Locally

## 1. Clone the Repository

```bash
git clone https://github.com/abhishekkargeti1/ChatApplication-Backend.git

cd ChatApplication-Backend
```

---

## 2. Configure MongoDB

Make sure MongoDB is running locally or provide the appropriate MongoDB connection configuration through the application configuration files.

The project contains environment-specific configuration:

```text
src/main/resources/
├── application.properties
├── application-dev.yml
└── application-prod.yml
```

Update the MongoDB configuration according to your environment.

---

## 3. Build the Application

Using Maven:

```bash
mvn clean package
```

Or using the Maven wrapper:

```bash
./mvnw clean package
```

On Windows:

```cmd
mvnw.cmd clean package
```

The build generates:

```text
target/ChatApplicationProject4-0.0.1-SNAPSHOT.war
```

---

## 4. Run the Application

You can run the packaged application using:

```bash
java -jar target/ChatApplicationProject4-0.0.1-SNAPSHOT.war
```

The Dockerfile also uses this generated WAR as the application artifact.

---

# 🐳 Docker

## Build Docker Image

First build the application:

```bash
mvn clean package -DskipTests
```

Then create the Docker image:

```bash
docker build -t chatapp-backend .
```

---

## Run Docker Container

```bash
docker run -d \
  --name chatapp-backend \
  -p 8080:8080 \
  chatapp-backend
```

Check the container:

```bash
docker ps
```

View logs:

```bash
docker logs -f chatapp-backend
```

Stop the container:

```bash
docker stop chatapp-backend
```

Remove the container:

```bash
docker rm chatapp-backend
```

---

# 🐳 Dockerfile

The application uses Java 17 through the Eclipse Temurin runtime image.

The current Dockerfile:

```dockerfile
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/ChatApplicationProject4-0.0.1-SNAPSHOT.war app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.war"]
```

The container exposes port `8080` and starts the Spring Boot WAR using Java.

---

# 🔄 CI/CD Pipeline

The project contains a Jenkins pipeline defined in:

```text
Jenkinsfile
```

The pipeline automates application delivery from GitHub to Kubernetes.

---

## Jenkins Pipeline

```text
                    GitHub
                       │
                       ▼
                ┌────────────┐
                │   Jenkins  │
                └─────┬──────┘
                      │
                      ▼
              ┌───────────────┐
              │ Code Checkout │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Maven Build   │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Docker Build  │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │   Docker Hub  │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │  Kubernetes   │
              │   Deployment  │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Rollout Check │
              └───────────────┘
```

---

# 🧩 Jenkins Pipeline Stages

## 1. Code Cloning

Jenkins checks out the `main` branch from GitHub.

```text
GitHub
   ↓
Jenkins Agent
```

---

## 2. Build JAR

The application is built using:

```bash
mvn clean package -DskipTests
```

The resulting WAR file is then used to create the Docker image.

---

## 3. Build Docker Image

The pipeline creates a Docker image using the Jenkins build number:

```text
abhishekkargeti/chatapp-backend-image:<BUILD_NUMBER>
```

For example:

```text
abhishekkargeti/chatapp-backend-image:15
```

Using the Jenkins build number gives each image a unique version instead of relying only on the `latest` tag.

---

## 4. Push Docker Image

Jenkins authenticates with Docker Hub using Jenkins Credentials.

The configured credential ID is:

```text
DockerCred
```

The pipeline then pushes:

```text
abhishekkargeti/chatapp-backend-image:<BUILD_NUMBER>
```

to Docker Hub.

---

## 5. Kubernetes Deployment

Jenkins applies the Kubernetes deployment configuration and updates the deployment to use the newly created Docker image.

```bash
kubectl set image deployment/chat-app-deployment \
chat-app-server=abhishekkargeti/chatapp-backend-image:<BUILD_NUMBER> \
-n production-namespace
```

The pipeline then waits for the deployment rollout:

```bash
kubectl rollout status deployment/chat-app-deployment \
-n production-namespace
```

This ensures Jenkins does not simply fire the deployment command and exit; it waits for Kubernetes to report rollout status.

---

## 6. Verify Deployment

The pipeline verifies the Kubernetes environment using:

```bash
kubectl get nodes

kubectl get pods -n production-namespace -o wide

kubectl get svc -n production-namespace

kubectl get deployment -n production-namespace
```

This provides basic deployment verification after each pipeline execution.

---

# ☸️ Kubernetes Deployment

The repository contains a dedicated `k8s` directory:

```text
k8s/
├── chatapp-backend-deployment.yml
├── chatapp-backend-service.yml
├── database-service.yml
├── database-statefulset.yml
├── mongodb-volume.yml
└── secret-config.yml
```

These manifests provide the Kubernetes resources required for the backend and MongoDB deployment.

---

# 📦 Kubernetes Components

## Backend Deployment

The backend application runs as a Kubernetes Deployment.

```text
Deployment
    │
    ├── Pod
    │    └── Spring Boot Backend
    │
    ├── Pod
    │    └── Spring Boot Backend
    │
    └── Pod
         └── Spring Boot Backend
```

Using multiple replicas allows Kubernetes to maintain multiple application instances.

---

## Backend Service

The backend is exposed internally through a Kubernetes Service.

```text
Frontend
    │
    ▼
Backend Service
    │
    ├── Backend Pod
    ├── Backend Pod
    └── Backend Pod
```

The Service provides stable networking to the backend Pods.

---

# 🍃 MongoDB on Kubernetes

MongoDB is deployed using a Kubernetes StatefulSet.

The repository contains:

```text
database-statefulset.yml
database-service.yml
mongodb-volume.yml
```

The StatefulSet provides stable identity and storage characteristics suitable for a stateful database workload.

Persistent storage is configured through the MongoDB volume manifest.

---

# 🔐 Kubernetes Secrets

The project contains:

```text
k8s/secret-config.yml
```

This configuration is intended to provide sensitive configuration to Kubernetes workloads.

For production environments, avoid committing actual passwords, tokens or other credentials into a public GitHub repository.

Use:

* Kubernetes Secrets
* Jenkins Credentials
* AWS Secrets Manager
* HashiCorp Vault
* External Secrets Operator

instead.

---

# 🌎 Application Profiles

The project contains separate configuration files:

```text
application.properties
application-dev.yml
application-prod.yml
```

This allows configuration to be separated by environment.

Example environments:

```text
Development
    ↓
application-dev.yml

Production
    ↓
application-prod.yml
```

This approach makes it easier to use different database and application configurations across environments.

---

# 🧪 Testing

The project includes Spring Boot testing dependencies.

Run tests using:

```bash
mvn test
```

Or:

```bash
./mvnw test
```

For CI/CD, tests should ideally execute before Docker image creation.

Recommended pipeline:

```text
Checkout
   ↓
Compile
   ↓
Unit Tests
   ↓
Package
   ↓
Docker Build
   ↓
Security Scan
   ↓
Docker Push
   ↓
Kubernetes Deployment
   ↓
Rollout Verification
```

---

# 🔐 Security Considerations

Do not commit sensitive information to GitHub.

Sensitive information includes:

* MongoDB passwords
* JWT secrets
* API keys
* Docker Hub passwords
* Cloud credentials
* Kubernetes credentials

Instead, use:

```text
Jenkins Credentials
        │
        ▼
Environment Variables
        │
        ▼
Application / Deployment
```

For Kubernetes:

```text
Kubernetes Secret
        │
        ▼
Backend Pod
```

---

# 📊 CI/CD Workflow

The complete deployment workflow is:

```text
Developer
    │
    │ git push
    ▼
GitHub
    │
    │ webhook / Jenkins trigger
    ▼
Jenkins
    │
    ├── Checkout
    │
    ├── Maven Build
    │
    ├── Docker Build
    │
    ├── Docker Hub Push
    │
    ├── kubectl apply
    │
    ├── Update Docker Image
    │
    ├── Rollout Status
    │
    └── Deployment Verification
    │
    ▼
Kubernetes Cluster
    │
    ├── Spring Boot Backend
    │
    └── MongoDB
```

---

# 📋 Jenkins Pipeline Summary

| Stage                 | Responsibility                               |
| --------------------- | -------------------------------------------- |
| Code Cloning          | Clone source code from GitHub                |
| Building-JAR          | Build Spring Boot application                |
| Building-Docker-Image | Create Docker image                          |
| Testing               | Testing stage                                |
| Pushing-Docker-Image  | Push image to Docker Hub                     |
| Deployment            | Deploy/update application in Kubernetes      |
| Verify Deployment     | Verify nodes, Pods, Services and Deployments |

The current Jenkinsfile implements these stages and uses a Jenkins build number for Docker image versioning.

---

# 🎯 DevOps Concepts Demonstrated

This project demonstrates practical experience with:

* Git
* GitHub
* Maven
* Spring Boot
* Docker
* Docker Hub
* Jenkins
* Jenkins Pipeline
* CI/CD
* Kubernetes
* Kubernetes Deployment
* Kubernetes Service
* Kubernetes StatefulSet
* Kubernetes Persistent Storage
* Kubernetes Secrets
* MongoDB
* Containerized application deployment
* Rolling deployment
* Deployment verification

---

# 📈 Future Improvements

The project can be further improved by adding:

* [ ] Automated unit/integration tests in Jenkins
* [ ] SonarQube code-quality analysis
* [ ] Trivy Docker image vulnerability scanning
* [ ] Docker image cleanup policy
* [ ] Jenkins webhook from GitHub
* [ ] Jenkins build notifications
* [ ] Kubernetes readiness probes
* [ ] Kubernetes liveness probes
* [ ] Kubernetes resource requests and limits
* [ ] Horizontal Pod Autoscaler
* [ ] Network Policies
* [ ] Ingress configuration
* [ ] TLS/HTTPS
* [ ] Centralized logging
* [ ] Prometheus monitoring
* [ ] Grafana dashboards
* [ ] Argo CD GitOps deployment
* [ ] Helm charts
* [ ] Separate Dev/Staging/Production namespaces

---

# 🚀 Production Deployment Roadmap

A future production architecture could look like:

```text
                       GitHub
                          │
                          ▼
                      Jenkins
                          │
              ┌───────────┴───────────┐
              │                       │
              ▼                       ▼
          Maven Build           Security Scan
              │                       │
              └───────────┬───────────┘
                          ▼
                    Docker Build
                          │
                          ▼
                      Docker Hub
                          │
                          ▼
                      Argo CD
                          │
                          ▼
                 Kubernetes Cluster
                          │
              ┌───────────┴───────────┐
              │                       │
              ▼                       ▼
       Spring Boot Pods           MongoDB
              │
              ▼
          Kubernetes
           Service
              │
              ▼
            Ingress
              │
              ▼
           Frontend
```

This would provide a stronger **CI/CD + GitOps + Kubernetes** architecture.

---

# 📌 Related Repository

Frontend repository:

```text
ChatApplication-Frontend
```

The frontend communicates with this backend through REST APIs and WebSocket connections.

---

# 👨‍💻 Author

**Abhishek Kargeti**

GitHub:

https://github.com/abhishekkargeti1

---

# ⭐ Project Objective

The objective of this project is to build a real-time chat application backend while gaining practical experience with modern backend development and DevOps practices.

The project combines:

```text
Spring Boot
     +
MongoDB
     +
WebSocket
     +
Docker
     +
Jenkins
     +
Kubernetes
```

to create an automated workflow from **source code → build → container → registry → Kubernetes deployment**.

---

## 📚 Repository

Source code:

https://github.com/abhishekkargeti1/ChatApplication-Backend
