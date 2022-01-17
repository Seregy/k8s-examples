## Prerequisites
- `minikube` is installed
- `kubectl` is installed
- `helm` is installed

## K8s commands
- `minikube image load image_name:tag` - load local image to minikube
- `helm upgrade --install demo . --dry-run --debug` - debug helm chart
- `helm upgrade --install demo .` - install helm chart
- `helm list` - get a list of projects
- `alias k=kubectl` - alias for kubectl
- `k get po` - get all pods
- `minikube ip` - receive minikube ip for ingress
