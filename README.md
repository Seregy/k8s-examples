# k8s-examples

 Sample microservice application running on Kubernetes

### Components:

- [calculation-service](calculate-service) - user-facing service for converting values between currencies
- [rate-provider](rate-service-mock) - service for providing exchange rates

## Building and deploying services

Provided configurations for deployment resources make use of already built images on Docker Hub and may be optionally adjusted to use locally built images instead.

### Prerequisites

1. [Minikube](https://minikube.sigs.k8s.io/docs/start) for setting up local Kubernetes cluster
2. [Kubectl](https://kubernetes.io/docs/tasks/tools/#kubectl) CLI for interacting with the cluster
3. (Optional) [Docker](https://www.docker.com/) for building local images of the services

### (Optional) Building service images

1. Build [calculation-service](calculate-service) image by switching into the service directory and executing:

    ```
    ./mvnw clean verify
    
    docker build -t calculation-service .
    ```

   To build the Java application from sources and create a Docker image with `calculation-service` tag

2. Building [rate-service](rate-service-mock) image by switching into the service directory and creating a Docker image with `rate-service` tag:

    ```
    docker build -t rate-service .
    ```

### Deploying services locally via Minikube

1. Start the cluster:

    ```
    minikube start
    ```

2. (Optional) Load locally built images using `minikube image load <image-name>`:

    ```
    minikube image load calculation-service
    minikube image load rate-service
    ```

3. (Optional) Adjust the deployments to use locally built images by:
    - replacing existing value for `image:` in [calculation-service deployment file](k8s/calculation-service/deployment.yaml) with `image: calculation-service`
    - replacing existing value for `image:` in [rate-provider deployment file](k8s/rate-provider/deployment.yaml) with `image: rate-service`
4. Create deployment, service and ingress resources for calculation service:

    ```
    kubectl apply -f k8s/calculation-service/deployment.yaml
    kubectl apply -f k8s/calculation-service/service.yaml
    kubectl apply -f k8s/calculation-service/ingress.yaml
    ```

5. Create deployment and service resources for rate service:

    ```
    kubectl apply -f k8s/rate-provider/deployment.yaml
    kubectl apply -f k8s/rate-provider/service.yaml
    ```

6. Enable NGINX Ingress controller:
  
    ```
    minikube addons enable ingress
    ```

7. Expose Ingress to the host OS using tunnel:

    ```
    minikube tunnel
    ```

8. Access the application [locally using /calculation path](http://127.0.0.1/calculation/)
